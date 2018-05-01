package algorithmes;

import metier.Graphe;
import metier.Lieu;
import metier.Solution;

import java.util.List;
import java.util.Random;

public class MethodesUtiles {

    public boolean quantiteRespectee(Solution solution, Graphe graphe) {

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

    /**
     * Permet de generer une valeur aléatoire
     * @param valeurExcept : Liste des valeurs à ne pas generer par la méthode
     * @return
     */
    public static int randomValeur(List<Integer> valeurExcept, int size){
        int rnd;
        Random rand = new Random();

        do {
            rnd = rand.nextInt(size - 1) + 1;
        }while (valeurExcept.contains(rnd));

        return rnd;
    }

    /**
     * Permet de generer une valeur aléatoire entre 0 et size
     * @param valeurExcept
     * @param size
     * @return
     */
    public static int randomVal(List<Integer> valeurExcept, int size){
        int rnd;
        Random rand = new Random();

        do {
            rnd = rand.nextInt(size);
        }while (valeurExcept.contains(rnd));

        return rnd;
    }

    public static double randomDouble(double rangeMin, double rangeMax){
        Random r = new Random();
        double randomValue = rangeMin  + (rangeMax - rangeMin) * r.nextDouble();
        return randomValue;

    }

}
