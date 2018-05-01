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
    private double temperatureInitiale;
    private double probabilite;
    private int nombreIterationAvantChangementTemp;
    private int nombreIteration;
    private double mu;

    public static void main(String[] args) {

        menu = new  Menu();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("couou");
        System.out.println(e.getModifiers());
        System.out.println();

        Graphe graphe = new Graphe("./data/data01.txt");

        Genetique gen = new Genetique(graphe, 100, 10000);
        Solution solutionGen = gen.algoGen();
        System.out.println(solutionGen.getListeSolution());

       //double temperatureInitiale = 40;

        //System.out.println(graphe.solutionGlouton());
       /* double temperatureInitiale = 165;
        double probabilite = 0.5;
        int nombreIterationAvantChangementTemp = 60;
        int nombreIteration = 10;
        double mu = 0.5;

        temperatureInitiale = Double.parseDouble(menu.jtfTemp.getText());
        probabilite = Double.parseDouble(menu.jtfProba.getText());
        nombreIterationAvantChangementTemp = Integer.parseInt(menu.jtfNbIteAvtChgtTemp.getText());
        nombreIteration = Integer.parseInt(menu.jtfNbIteration.getText());
        mu = Double.parseDouble(menu.jtfMu.getText());

        Recuit recuit = new Recuit(
                graphe,
                temperatureInitiale,
                probabilite,
                nombreIterationAvantChangementTemp,
                nombreIteration,
                mu
        );
        Solution solutionRecuit = recuit.run();

        // Run the GUI codes on the Event-Dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //new InterfaceGraphique(solutionGen, graphe,"algorithme genetique"); // Let the constructor do the job
                new InterfaceGraphique(solutionRecuit, graphe,"algorithme recuit simule"); // Let the constructor do the job
            }

        });*/
    }

}
