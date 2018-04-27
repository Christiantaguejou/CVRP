package main;

import Interface.InterfaceGraphique;
import algorithmes.Genetique;
import algorithmes.Recuit;
import metier.Graphe;
import metier.Solution;

import javax.swing.*;
import java.util.Random;

/**
 * Created by tardy on 07/03/2018.
 */
public class MainTest {

    public static void main(String[] args) {
        Graphe graphe = new Graphe("./data/data01.txt");

        Genetique gen = new Genetique(graphe);
        gen.algoGen();
       /*double temperatureInitiale = 40;
        double probabilite = 0.5;
        int nombreIterationAvantChangementTemp = 60;
        int nombreIteration = 10;
        double mu = 0.5;

        Recuit recuit = new Recuit(
                graphe,
                temperatureInitiale,
                probabilite,
                nombreIterationAvantChangementTemp,
                nombreIteration,
                mu
        );
        Solution solution = recuit.run();

        // Run the GUI codes on the Event-Dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfaceGraphique(solution, graphe); // Let the constructor do the job
            }
        });*/

    }
}
