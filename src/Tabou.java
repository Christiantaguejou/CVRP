import java.util.ArrayList;
import java.util.List;

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

    public float calculSolution() {
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

    public List retourneVoisinage() {

        return null;
    }

    public boolean quantiteRespectee() {

        return false;
    }

}
