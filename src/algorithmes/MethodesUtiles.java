package algorithmes;

import metier.Graphe;
import metier.Lieu;
import metier.Solution;

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

}
