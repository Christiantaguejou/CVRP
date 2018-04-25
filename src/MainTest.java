import java.util.ArrayList;
import java.util.List;

/**
 * Created by tardy on 07/03/2018.
 */
public class MainTest {

    public static void main(String[] args) {
        Graphe graphe = new Graphe("./data/data01.txt");

//       Tabou tabou = new Tabou(graphe,20);
//        System.out.println(tabou.getSolution());
//        System.out.println(tabou.quantiteRespectee(tabou.getSolution().getListeSolution()));

        double temperatureInitiale = 30;
        double probabilite = 0.8;
        int nombreIterationAvantChangementTemp = 2;
        int nombreIteration = 100000000;
        double mu = 0.99;

        System.out.println("coucou");
        Recuit recuit = new Recuit(
                graphe,
                temperatureInitiale,
                probabilite,
                nombreIterationAvantChangementTemp,
                nombreIteration,
                mu
        );
        recuit.run();
    }
}
