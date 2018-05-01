package main;

import Interface.InterfaceGraphique;
import Interface.Menu;
import algorithmes.Genetique;
import algorithmes.MethodesUtiles;
import algorithmes.Recuit;
import metier.Graphe;
import metier.Solution;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by tardy on 07/03/2018.
 */
public class MainTest implements ActionListener {

    private static Menu menu;

    public static void main(String[] args) {

        menu = new  Menu();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String file = menu.selectedRadioButton.getText();
        double temperatureInitiale = Double.parseDouble(menu.jtfTemp.getText());
        double probabilite = Double.parseDouble(menu.jtfProba.getText());
        int nombreIterationAvantChangementTemp = Integer.parseInt(menu.jtfNbIteAvtChgtTemp.getText());
        int nombreIterationRecuit = Integer.parseInt(menu.jtfNbIterationRecuit.getText());
        double mu = Double.parseDouble(menu.jtfMu.getText());
        int nombreIterationGen = Integer.parseInt(menu.jtfNbIterationGen.getText());
        int nombrePopulation = Integer.parseInt(menu.jtfNbPopulation.getText());


        Graphe graphe = new Graphe("./data/"+ file + ".txt");

        Genetique gen = new Genetique(graphe, nombreIterationGen, nombrePopulation);
        Recuit recuit = new Recuit(
                graphe,
                temperatureInitiale,
                probabilite,
                nombreIterationAvantChangementTemp,
                nombreIterationRecuit,
                mu
        );
        Solution solutionGen = gen.algoGen();
        System.out.println(solutionGen);

        Solution solutionRecuit = recuit.run();

        // Run the GUI codes on the Event-Dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfaceGraphique(solutionRecuit, graphe,"algorithme recuit simule");
                new InterfaceGraphique(solutionGen, graphe,"algorithme Genetique");
            }

        });
    }

}
