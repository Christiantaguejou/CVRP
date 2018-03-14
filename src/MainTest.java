import java.util.ArrayList;
import java.util.List;

/**
 * Created by tardy on 07/03/2018.
 */
public class MainTest {

    public static void main(String[] args) {
        Graphe graphe = new Graphe("./data/data01.txt");

        Lieu lieu1 = graphe.getLieux().get(0);
        Lieu lieu2 = graphe.getLieux().get(1);
        System.out.println(lieu1.toString());
        System.out.println(lieu2.toString());
        System.out.println(graphe.calculDistance(lieu1,lieu2));
    }
}
