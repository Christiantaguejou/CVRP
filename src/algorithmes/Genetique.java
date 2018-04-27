package algorithmes;

import metier.Graphe;
import metier.Lieu;
import metier.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Genetique {

    private static int NBR_ITERATION = 1;
    private int NBR_SOLUTION = 2;
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
            population.add(new Solution(getLieuRandom()));
            System.out.println(population.get(i).getListeRoute());
        }

        for(int i = 0; i < NBR_ITERATION; i++){
            List<Double> fitness = new ArrayList<>();
            List<Double> probaListe = new ArrayList<>();
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
            System.out.println(probaListe);

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
            System.out.println(m1+" : "+m2);

            //Croisement relou entre population.get(m1) et population.get(m2)

        }
    }

    private Solution croisement(Solution pere, Solution mere){
        //Prendre une "route" de chaque solution pere et mere distinct
        //Et completer la nouvelle solution par les sommets restants
        List<Integer> itineraire = new ArrayList<>();
        Solution fille = new Solution(itineraire);
            //Trouver deux routes disjoinctes
        List<Integer> routePere = new ArrayList<>();
        List<Integer> routeMere = new ArrayList<>();

        for(int i = 0; i < pere.getListeRoute().size(); i++){
            routePere = pere.getListeRoute().get(i);
            for(int j = 0; j < mere.getListeRoute().size(); j++){
                routeMere = mere.getListeRoute().get(j);

            }
        }

        return fille;
    }

    public double distanceTotal(Solution sol){
        double distanceTotal = 0;

        for(int i = 0; i <= m_lieux.size()-2; i++){
            distanceTotal += Graphe.calculDistance(m_lieux.get(sol.getListeSolution().get(i)),
                                                   m_lieux.get(sol.getListeSolution().get(i+1)));
        }

        return distanceTotal;
    }

    private List<Integer> getLieuRandom(){
        int iter = 0;
        int capacite = 0;
        List<Integer> valeurExcept = new ArrayList<>();
        List<Integer> itineraire = new ArrayList<>();
        itineraire.add(0);
       // Random rand = new Random();

        for(int i = 0; i < m_lieux.size(); i++){
            do{
               // int rnd =  rand.nextInt(m_lieux.size() - 1)+1;
                if(valeurExcept.size() == 31)
                    break;
                int rnd = randomValeur(valeurExcept);
                valeurExcept.add(rnd);
               // System.out.println(rnd);
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

