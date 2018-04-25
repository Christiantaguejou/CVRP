package main;

import Interface.InterfaceGraphique;
import algorithmes.Recuit;
import metier.Graphe;
import metier.Solution;

import javax.swing.*;

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
        Solution solution = recuit.run();

        // Run the GUI codes on the Event-Dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfaceGraphique(solution, graphe); // Let the constructor do the job
            }
        });

    }
}
