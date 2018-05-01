package algorithmes;

import metier.Graphe;
import metier.Lieu;
import metier.Solution;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.random;

/**
 * Created by tardy on 24/04/2018.
 */
public class Recuit {

    private Graphe graphe;
    private Solution solutionXi;
    private Solution solutionXip1;
    private Solution solutionMin;
    private double fMin;
    private double temperature;
    private double probabilite;
    private int nombreIterationAvantChangementTemp;
    private int nombreIteration;
    private double mu;

    public Recuit(Graphe graphe,
                  double temperatureInitiale,
                  double probabilite,
                  int nombreIterationAvantChangementTemp,
                  int nombreIteration,
                  double mu) {

        this.graphe = graphe;
        this.solutionXi = this.graphe.solutionGlouton();
        this.solutionXip1 = null;
        this.solutionMin = solutionXi;
        this.fMin = this.calculSolution(solutionMin);
        this.temperature = temperatureInitiale;
        this.probabilite = probabilite;
        this.nombreIterationAvantChangementTemp = nombreIterationAvantChangementTemp;
        this.nombreIteration = nombreIteration;
        this.mu = mu;
    }

    public Solution run() {
        int n1 = nombreIteration;
        int n2 = nombreIterationAvantChangementTemp;

        for (int k = 0;temperature >= 0.001; k++) {
            for (int l = 1; l <= n2; l++) {
                Solution solutionY = choisirSolution(solutionXi);
                double deltaF = calculSolution(solutionY) - calculSolution(solutionXi);
                if (deltaF <= 0) {
                    solutionXip1 = solutionY;
                    if (calculSolution(solutionXip1) < fMin) {
                        fMin = calculSolution(solutionXip1);
                        this.solutionMin = solutionXip1;
                    }
                } else {
                    double p = random();
                    double pI = Math.exp(-deltaF/temperature);
                    if (p <= pI)
                        this.solutionXip1 = solutionY;
                    else
                        this.solutionXip1 = solutionXi;
                }
                solutionXi = solutionXip1;
            }
            temperature = mu * temperature;
        }

        return solutionMin;


    }

    private Solution choisirSolution(Solution solution) {
        Solution solutionResultat;
        do {
            List<Integer> solutionTemp = new ArrayList<>();
            solutionTemp.addAll(solution.getListeSolution());
            int randomInt1, randomInt2;
            do {
                randomInt1 = (int) (random() * solutionTemp.size());
                randomInt2 = (int) (random() * solutionTemp.size());
            } while ((solutionTemp.get(randomInt1).equals(0) || solutionTemp.get(randomInt2).equals(0)));

            Integer id1 = solutionTemp.get(randomInt1);
            Integer id2 = solutionTemp.get(randomInt2);

            solutionTemp.set(randomInt1, id2);
            solutionTemp.set(randomInt2, id1);

            solutionResultat = new Solution(solutionTemp);
        } while (!quantiteRespectee(solutionResultat));
        return solutionResultat;
    }

    public double calculSolution(Solution solution) {
        double resultat = 0;
        Integer idTemp = 0, id = 0;
        Iterator iterator = solution.getListeSolution().iterator();

        while (iterator.hasNext()) {
            id = (Integer) iterator.next();
            if (iterator.hasNext()) {
                idTemp = (Integer) iterator.next();
                Lieu lieu1, lieu2;
                lieu1 = graphe.getLieux().get(id);
                lieu2 = graphe.getLieux().get(idTemp);
                resultat += graphe.calculDistance(lieu1, lieu2);
            }
        }
        System.out.println(resultat);
        return resultat;
    }

    public boolean quantiteRespectee(Solution solution) {

        int capacite = 0;
        for (Integer id : solution.getListeSolution()) {
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


}
