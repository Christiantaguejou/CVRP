package algorithmes;

import metier.Graphe;
import metier.Lieu;
import metier.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Genetique {

    private static int NBR_ITERATION = 1;
    private int NBR_SOLUTION = 50;
    List<Lieu> m_lieux;
    List<Solution> population;

    Graphe graphe;
    public Genetique(Graphe graphe){
        this.graphe = graphe;
        m_lieux = graphe.getLieux();
    }

    public void algoGen(){

        population = new ArrayList<>();

        for(int i = 0; i < NBR_SOLUTION; i++){
            population.add(new Solution(getLieuRandom(new ArrayList<>())));
        }
        for(int i = 0; i < NBR_ITERATION; i++){
            List<Double> fitness = new ArrayList<>();
            List<Double> probaListe = new ArrayList<>();
            Solution fille;
            double fitnessTotal = 0;
            double probaTotale = 0;

            //Calcul du fitness
            for(int j = 0; j < population.size(); j++){
                fitnessTotal += distanceTotal(population.get(j));
                fitness.add(distanceTotal(population.get(j)));
            }

            //Probabilité de chaque solution
            probaListe.addAll(fitness);
            for(int j = 0; j < population.size(); j++){
                probaListe.set(j, fitness.get(j)/fitnessTotal);
            }
            for(int j = 0; j < probaListe.size(); j++){
                probaListe.set(j, 1-probaListe.get(j));
                probaTotale += probaListe.get(j);
            }
            for(int j = 0; j < population.size(); j++){
                probaListe.set(j, probaListe.get(j)/probaTotale);
            }

            //Determination des meilleurs solutions
            int m1 = 0, m2 = 0;
            double max1 = 0, max2 = 0;
            for(int j = 0; j < population.size(); j++){

                if(max1 < probaListe.get(j)) {
                    max2 = max1;
                    m2 = m1;
                    max1 = probaListe.get(j);
                    m1 = j;
                }
                else if(max2 < probaListe.get(j)) {
                    max2 = probaListe.get(j);
                    m2 = j;
                }
            }
            fille = croisement(population.get(m1), population.get(m2));

            List<Integer> sommetsPresents = new ArrayList<>();
            for(int j = 0; j < fille.getListeSolution().size(); j++) {
                if(fille.getListeSolution().get(j) != 0)
                    sommetsPresents.add(fille.getListeSolution().get(j));
            }

            List<Integer> newSommets;
            newSommets = getLieuRandom(sommetsPresents);

            for(int j = 0; j < newSommets.size(); j++)
                fille.getListeSolution().add(newSommets.get(j));
            System.out.println("Fille: "+fille.getListeRoute());

            mutation(fille);

        }
    }

    /**
     * La mutation consiste d'échanger deux sommets de deux trajets différents,
     * en respectant la contrainte de capacite <=100
     * @param fille
     */
    private void mutation(Solution fille){
        List<ArrayList<Integer>> routeFille = fille.getListeRoute();
        List<Integer> newRoute1 = new ArrayList<>();
        List<Integer> newRoute2 = new ArrayList<>();
        int newCapacite1 = 0;
        int newCapacite2 = 0;
        int i = 0;
        mutationReussi:
        for(i = 0; i < routeFille.size() - 1; i++){
            ArrayList<Integer> route1 = routeFille.get(i);
            ArrayList<Integer> route2 = routeFille.get(i+1);

            for(int j = 0; j < route1.size(); j++){
                for(int k = 0; k < route2.size(); k++){
                    newRoute2.addAll(route2);
                    newRoute2.set(k, route1.get(j));
                    newRoute1.addAll(route1);
                    newRoute1.set(j, route2.get(k));

                    newCapacite1 = capaciteRoute(newRoute1);
                    newCapacite2 = capaciteRoute(newRoute2);

                    if(newCapacite1 <= 100 && newCapacite2 <=100)
                        break mutationReussi;
                    else{
                        newRoute1.clear();
                        newRoute2.clear();
                        newCapacite1 = 0;
                        newCapacite2 = 0;
                    }

                }
            }
        }
        System.out.println(i+" : "+newCapacite1 + " : "+newRoute1);
        System.out.println(i+1+" : "+newCapacite2 + " : "+newRoute2);

        routeFille.set(i, (ArrayList<Integer>) newRoute1);
        routeFille.set(i+1, (ArrayList<Integer>) newRoute2);
        fille.setListeRoute(routeFille);

        System.out.println(fille.getListeRoute());
        System.out.println(fille);
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
     * @param sol : Solution dont le cout sera calculé
     * @return
     */
    private double distanceTotal(Solution sol){
        double distanceTotal = 0;

        for(int i = 0; i <= m_lieux.size()-2; i++){
            distanceTotal += Graphe.calculDistance(m_lieux.get(sol.getListeSolution().get(i)),
                                                   m_lieux.get(sol.getListeSolution().get(i+1)));
        }

        return distanceTotal;
    }

    /**
     * Genere une liste de sommet en respectant les consignes
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

                int rnd = randomValeur(valeurExcept);
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

    /**
     * Permet de generer une valeur aléatoire
     * @param valeurExcept : Liste des valeurs à ne pas generer par la méthode
     * @return
     */
    private int randomValeur(List<Integer> valeurExcept){
        int rnd;
        Random rand = new Random();
        // System.out.println("test4");
        do {
            rnd = rand.nextInt(m_lieux.size() - 1) + 1;
        }while (valeurExcept.contains(rnd));

        return rnd;
    }
    //Population: Générer aléatoirement k solutions; ces solutions formeront la population

    /*For (nbr d'iteration)
        initialisation de la population

        For(i = 0 jusqu'à k)
            n = une solution de la pop
            n' = une autre solution
            n* = croisement entre n et n'
            appliquer une mutation sur n*
            ajouter n* à nouvelle_population
        population = nouvelle_population
      retourner le noeud n qui à la fitness la plus élevée
    */
}

