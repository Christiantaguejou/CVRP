package algorithmes;

import metier.Graphe;
import metier.Lieu;
import metier.Solution;

import java.util.*;

public class Genetique {

    private static int NBR_ITERATION = 100000;
    private int NBR_POPULATION = 150;
    List<Lieu> m_lieux;
    List<Solution> population;
    Graphe graphe;

    public Genetique(Graphe graphe){
        this.graphe = graphe;
        m_lieux = graphe.getLieux();
    }

    public Solution algoGen(){

        population = new ArrayList<>();

        for(int i = 0; i < NBR_POPULATION; i++){
            population.add(new Solution(getLieuRandom(new ArrayList<>())));
        }

        for(int i = 0; i < NBR_ITERATION; i++){
            //On trie les populations
            Collections.sort(population, (s1, s2) -> {
                if (distanceTotal(s1 )> distanceTotal(s2))
                    return 1;
                if (distanceTotal(s1) < distanceTotal(s2))
                    return -1;
                return 0;
            });

            List<Double> fitness = new ArrayList<>();
            List<Double> probaListe = new ArrayList<>();
            Solution fille;
            List<Integer> sommetsPresents ;

            double fitnessTotal = 0;
            double probaTotale = 0;

            //I - Reproduction
            for(int j = 0; j < population.size(); j++){
                fitnessTotal += distanceTotal(population.get(j));
                fitness.add(distanceTotal(population.get(j)));
            }

            probaListe.addAll(fitness);
            for(int j = 0; j < population.size(); j++){
                double proba = fitness.get(j) / fitnessTotal;
                probaListe.set(j, 1 - proba);
                probaTotale += 1 - proba;
            }

            for(int j = 0; j < population.size(); j++){
                if( j > 0)
                    probaListe.set(j, probaListe.get(j-1) + probaListe.get(j)/probaTotale);
                else
                    probaListe.set(j, probaListe.get(j)/probaTotale);
            }


            int m1 = 0, m2 = 0;
            int k = 0;
            parentTrouver:
            for(int j = 0; j < population.size(); j++){
                double rnd = MethodesUtiles.randomDouble(0,1);

                for(double proba : probaListe){
                    if(proba > rnd){
                        if( k > 0) {
                            m2 = j;
                            break parentTrouver;
                        }
                        else if(k == 0){
                            m1 = j;
                            k++;
                        }
                    }
                 }
            }

            /*List<Solution> newPopulation = new ArrayList<>();
            for(int j = 0; j < population.size(); j++){
                int index = 0;
                double rnd = MethodesUtiles.randomDouble(0,1);
                for(double proba : probaListe){
                    newPopulation.add(population.get(index));
                }
                index++;
            }*/


            fille = croisement(population.get(m1), population.get(m2));

            sommetsPresents = new ArrayList<>();
            for(int j = 0; j < fille.getListeSolution().size(); j++) {
                if(fille.getListeSolution().get(j) != 0)
                    sommetsPresents.add(fille.getListeSolution().get(j));
            }

            List<Integer> newSommets;
            newSommets = getLieuRandom(sommetsPresents);

            for(int j = 0; j < newSommets.size(); j++)
                fille.getListeSolution().add(newSommets.get(j));

            fille = mutation(fille);

            population.add(fille);


            if (i % 1000 == 0) {
                System.out.println();
                Solution sol;
                sol = population.get(0);
                for (Solution solution : population) {
                    if (distanceTotal(sol) > distanceTotal(solution)) {
                        sol = solution;
                    }
                }
                System.out.print("Meilleur solution de la population courante : " + distanceTotal(sol));
                System.out.println(" Génération : " + i);
            }
        }

        return bestSolution(population);
    }

    private Solution bestSolution(List<Solution> population){
        double  fitness, minFitness = Integer.MAX_VALUE;
        Solution s = new Solution();

        for(int i = 0 ; i < population.size(); i++){
            fitness = distanceTotal(population.get(i));
            if(fitness < minFitness){
                s = population.get(i);
                minFitness = fitness;
            }
            //System.out.println(population.get(i).getListeRoute()+ " : "+fitness);
        }
        System.out.println("Best Solution: "+s.getListeRoute()+" - "+minFitness);

        return s.listeToSolution(s.getListeRoute());
    }

    private  Map<Solution, Double> sortSolution(List<Solution> population, List<Double> probaListe){
        Map<Solution, Double> popProba = new HashMap<>();
        List<Solution> pop;

        for(int i = 0; i < population.size(); i++)
            popProba.put(population.get(i), probaListe.get(i));

        List<Map.Entry<Solution, Double>> list =
                new LinkedList<Map.Entry<Solution, Double>>(popProba.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Solution, Double>>() {
            public int compare(Map.Entry<Solution, Double> o1,
                               Map.Entry<Solution, Double> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<Solution, Double> sortedMap = new LinkedHashMap<Solution, Double>();
        for (Map.Entry<Solution, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        pop = new ArrayList<Solution>(sortedMap.keySet());
        /*Iterator it = sortedMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }*/
        return sortedMap;

    }

    /**
     * La mutation consiste d'échanger deux sommets de deux trajets différents,
     * en respectant la contrainte de capacite <=100
     * @param fille
     */
    private Solution mutation(Solution fille){
        List<ArrayList<Integer>> routeFille = fille.getListeRoute();
        List<Integer> newRoute1 = new ArrayList<>();
        List<Integer> newRoute2 = new ArrayList<>();
        int newCapacite1;
        int newCapacite2;
        boolean mutation = false;

        int i, j , k;
        int rnd1, rnd2;
        List<Integer> exceptRoute1 = new ArrayList<>();
        List<Integer> exceptRoute2 = new ArrayList<>();


        mutationReussi:
        for(i = 0; i < routeFille.size() - 1; i++){
            //rnd1 = MethodesUtiles.randomVal(exceptRoute1, routeFille.size());
            exceptRoute1.add(i);
            rnd2 = MethodesUtiles.randomVal(exceptRoute2, routeFille.size());

            ArrayList<Integer> route1 = routeFille.get(i);
            ArrayList<Integer> route2 = routeFille.get(rnd2);

            for( j = 0; j < route1.size(); j++){
                int r1;
                List<Integer> exceptNewRoute1 = new ArrayList<>();
                r1 = MethodesUtiles.randomVal(exceptNewRoute1, route1.size());

                for( k = 0; k < route2.size(); k++){
                    int r2;
                    List<Integer> exceptNewRoute2 = new ArrayList<>();
                    r2 = MethodesUtiles.randomVal(exceptNewRoute2, route2.size());

                    newRoute2.addAll(route2);
                    newRoute1.addAll(route1);
                    newRoute2.set(r2, route1.get(r1));
                    newRoute1.set(r1, route2.get(r2));

                    newCapacite1 = capaciteRoute(newRoute1);
                    newCapacite2 = capaciteRoute(newRoute2);

                    if(newCapacite1 <= 100 && newCapacite2 <=100) {
                        mutation = true;
                        routeFille.set(i, (ArrayList<Integer>) newRoute1);
                        routeFille.set(rnd2, (ArrayList<Integer>) newRoute2);
                        break mutationReussi;
                    }
                    else{
                        newRoute1.clear();
                        newRoute2.clear();
                    }
                    exceptNewRoute2.add(r2);
                }
                exceptNewRoute1.add(r1);
            }
            //exceptRoute1.add(i);
            exceptRoute2.add(rnd2);
        }

        if(!mutation){

        }

        // System.out.println(i);
        /*routeFille.set(i, (ArrayList<Integer>) newRoute1);
        routeFille.set(i+1, (ArrayList<Integer>) newRoute2);*/
        fille.setListeRoute(routeFille);

        return fille;
    }

    /**
     * Calcul la capacite d'un vehicule pour un trajet
     * @param route
     * @return
     */
    private int capaciteRoute(List<Integer> route){
        int capacite = 0;
            for(int i = 0; i < route.size(); i++)
                capacite += m_lieux.get(route.get(i)).getQuantite();
        return capacite;
    }

    /**
     * Permet d'effectuer le croisement de l'algo Genetique
     * @param pere
     * @param mere
     * @return
     */
    private Solution croisement(Solution pere, Solution mere){
        pere = sortRoute(pere);
        mere = sortRoute(mere);
        List<Integer> routefille = new ArrayList<>();
        Solution fille = new Solution(routefille);
        List<Integer> routePere;
        List<Integer> routeMere;

        routefille.add(0);
        finIteration:
        for(int i = 0; i < pere.getListeRoute().size(); i++){
            routePere = pere.getListeRoute().get(i);

            for(int j = 0; j < mere.getListeRoute().size(); j++){
                routeMere = mere.getListeRoute().get(j);

                for(int k = 0; k < routeMere.size(); k++) {
                    if(routePere.contains(routeMere.get(k)))
                        break;
                    if(k == routeMere.size()-1){
                        routefille.addAll(routePere);
                        routefille.add(0);
                        routefille.addAll(routeMere);
                        break finIteration;
                    }
                }

            }
        }
        return fille;
    }

    /**
     * Calcul du cout total d'une solution
     * @param sol : Solution dont le coût sera calculé
     * @return
     */
    public double distanceTotal(Solution sol){
        double distanceTotal = 0;

        for(int i = 0; i <= m_lieux.size()-2; i++){
            distanceTotal += Graphe.calculDistance(m_lieux.get(sol.getListeSolution().get(i)),
                                                   m_lieux.get(sol.getListeSolution().get(i+1)));
        }

        return distanceTotal;
    }

    /**
     * Calcul de la distance d'une seule route
     * @param route
     * @return
     */
    private double distanceRoute(List<Integer> route){
        double distance = 0.0;

        for(int i = 0; i < route.size() -2; i++){
            distance += Graphe.calculDistance(m_lieux.get(route.get(i)), m_lieux.get(route.get(i+1)));
        }

        return  distance;
    }

    private Solution sortRoute(Solution sol){
        HashMap<List<Integer>, Double> mapSolution = new HashMap<>();
        Solution sortSolution;
        List<List<Integer>> liste = new ArrayList<>();

        for(int i = 0 ; i < sol.getListeRoute().size()-1; i++){
            mapSolution.put(sol.getListeRoute().get(i), distanceRoute(sol.getListeRoute().get(i)));
        }

        List<Map.Entry<List<Integer>, Double>> list =
                new LinkedList<Map.Entry<List<Integer>, Double>>(mapSolution.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<List<Integer>, Double>>() {
            public int compare(Map.Entry<List<Integer>, Double> o1,
                               Map.Entry<List<Integer>, Double> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<List<Integer>, Double> sortedMap = new LinkedHashMap<List<Integer>, Double>();
        for (Map.Entry<List<Integer>, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        liste.addAll(sortedMap.keySet());
        List<Integer> listeNewSol = new ArrayList<>();
        listeNewSol.add(0);
        for(int i = 0; i<liste.size(); i++){
            for(int j = 0; j < liste.get(i).size(); j++){
                listeNewSol.add(liste.get(i).get(j));
            }
            listeNewSol.add(0);
        }

        sortSolution = new Solution(listeNewSol);
        return sortSolution;
    }

    /**
     * Genere une liste de sommet aléatoire en respectant les consignes
     * @param valeurExcept : Liste des Sommets à ne pas ajouter car ils sont déjà presents
     * @return
     */
    private List<Integer> getLieuRandom(List<Integer> valeurExcept){
        int iter = 0;
        int capacite = 0;
        List<Integer> itineraire = new ArrayList<>();

        itineraire.add(0);
        for(int i = 0; i < m_lieux.size(); i++){
            do{
                if(valeurExcept.size() == m_lieux.size()-1)
                    break;

                int rnd = MethodesUtiles.randomValeur(valeurExcept, m_lieux.size());
                valeurExcept.add(rnd);
                capacite +=  m_lieux.get(rnd).getQuantite();

                if(capacite <= Graphe.CAPACITE_MAX  && !itineraire.contains(rnd)){
                    itineraire.add(rnd);
                    if(capacite == 100) {
                        itineraire.add(0);
                        capacite = 0;
                    }
                }
                else if(capacite > Graphe.CAPACITE_MAX && !itineraire.contains(rnd)){
                    capacite = 0;
                    if(iter <= m_lieux.size() - i){
                        itineraire.add(0);
                        capacite = m_lieux.get(rnd).getQuantite();
                        iter = 0;
                        itineraire.add(rnd);
                    }
                    else
                        iter +=1;
                }
                else if(i == m_lieux.size() - 1 && !itineraire.contains(rnd)){
                    itineraire.add(rnd);
                }
            }while (capacite <100);
        }
        itineraire.add(0);

        return itineraire;
    }




}

