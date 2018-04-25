import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.floor;
import static java.lang.Math.random;

/**
 * Created by tardy on 14/03/2018.
 */
public class Tabou {
    private Graphe graphe;
    private Solution solution;
    private List<Integer> listeTabou;

    public Tabou(Graphe graphe, int capactite) {
        this.graphe = graphe;
        this.listeTabou = new ArrayList<>(capactite);
        this.solution = this.graphe.solutionGlouton();
    }

    public Graphe getGraphe() {
        return graphe;
    }

    public void setGraphe(Graphe graphe) {
        this.graphe = graphe;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    public float calculSolution(Solution solution) {
        float resultat = 0;
        Integer idTemp = 0;
        for ( Integer id : solution.getListeSolution()) {
            Lieu lieu1, lieu2;
            lieu1 = graphe.getLieux().get(idTemp);
            lieu2 = graphe.getLieux().get(id);
            resultat += graphe.calculDistance(lieu1,lieu2);
        }
        return resultat;
    }

    public HashMap<Solution, int[]> retourneVoisinage(int nombreDeVoisin) {

        HashMap<Solution, int[]> voisinage = new HashMap<>();
        int i = 0;
        do {
            List<Integer> solution = new ArrayList<>();
            solution.addAll(this.solution.getListeSolution());
            int randomInt1, randomInt2;
            do {
                randomInt1 = (int) (random() * solution.size());
                randomInt2 = (int) (random() * solution.size());
            } while ((solution.get(randomInt1).equals(0) || solution.get(randomInt2).equals(0)));

            Integer id1 = solution.get(randomInt1);
            Integer id2 = solution.get(randomInt2);

            solution.set(randomInt1, id2);
            solution.set(randomInt2, id1);
            i++;
            Solution sol = new Solution(solution);
            if (!voisinage.keySet().contains(sol)) {
                voisinage.put(sol, new int[]{randomInt1, randomInt2});
            } else {
                i--;
            }
        } while ( i<= nombreDeVoisin);

        return voisinage;
    }

    public boolean quantiteRespectee(List<Integer> voisinage) {

        int capacite = 0;
        for (Integer id : voisinage) {
            if (id == 0) {
                if (capacite >100) {
                    return false;
                }
                capacite = 0;
            } else {
                Lieu lieu = graphe.getLieux().get(id);
                capacite += lieu.getQuantite();
            }
        }
        return true;
    }

    public void execute(int nombreIteration, int nombreDeVoisin) {
        HashMap<Solution,int[]> voisinage = this.retourneVoisinage( nombreDeVoisin);
        List<Integer> listeSol = new ArrayList<>();
        List<Integer> listeTabou = new ArrayList<>();

        listeSol.addAll(this.solution.getListeSolution());
        Solution solutionActuelle = new Solution(listeSol);
        float coutSolutionActuelle = this.calculSolution(solutionActuelle);
        int i = 0;

        while (i <= nombreIteration) {

            Solution meilleureSolution = retourneMeilleureSolution(voisinage);
            if (calculSolution(meilleureSolution) >= coutSolutionActuelle) {
                listeTabou.add(1);
            }
            i++;
            voisinage =retourneVoisinage(nombreDeVoisin);
        }
    }

    public Solution retourneMeilleureSolution(HashMap<Solution, int[]> listeSolutions) {
        Iterator iterator = listeSolutions.entrySet().iterator();
        Solution solutionMeilleure = (Solution) iterator.next();
        float coutMeilleur = calculSolution(solutionMeilleure);

        while (iterator.hasNext()) {
            Solution solTemp = (Solution) iterator.next();
            float cout = calculSolution(solTemp);
            if (cout <= coutMeilleur) {
                solutionMeilleure = solTemp;
                coutMeilleur = cout;
            }
        }
        return solutionMeilleure;
    }
}
