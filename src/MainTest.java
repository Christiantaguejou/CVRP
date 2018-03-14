import java.util.ArrayList;
import java.util.List;

/**
 * Created by tardy on 07/03/2018.
 */
public class MainTest {

    public static void main(String[] args) {
        Graphe graphe = new Graphe("./data/data01.txt");

       Tabou tabou = new Tabou(graphe,20);
        System.out.println(tabou.getSolution());
        System.out.println(tabou.quantiteRespectee(tabou.getSolution().getListeSolution()));
    }
}
