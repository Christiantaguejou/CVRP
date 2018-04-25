package main;

import algorithmes.Recuit;
import metier.Graphe;

/**
 * Created by tardy on 07/03/2018.
 */
public class MainTest {

    public static void main(String[] args) {
        Graphe graphe = new Graphe("./data/data01.txt");

        double temperatureInitiale = 30;
        double probabilite = 0.8;
        int nombreIterationAvantChangementTemp = 2;
        int nombreIteration = 100000000;
        double mu = 0.99;

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
