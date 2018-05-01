package Interface;

import metier.Graphe;
import metier.Lieu;
import metier.Solution;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by tardy on 25/04/2018.
 */
public class InterfaceGraphique extends JFrame{
    // Define constants
    public static final int CANVAS_WIDTH  = 640;
    public static final int CANVAS_HEIGHT = 480;

    // Declare an instance of the drawing canvas,
    // which is an inner class called DrawCanvas extending javax.swing.JPanel.
    private DrawCanvas canvas;
    private Solution solution;
    private double coutSolution;
    private Graphe graphe;
    private String title;

    // Constructor to set up the GUI components and event handlers
    public InterfaceGraphique(Solution solution, Graphe graphe, String title,double coutSolution) {
        this.solution = solution;
        this.graphe = graphe;
        this.title = title;
        this.coutSolution = coutSolution;
        JPanel jPanel = new JPanel();

        canvas = new DrawCanvas();    // Construct the drawing canvas
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        JLabel label1 = new JLabel("Coût de cette solution: " + coutSolution);
        JLabel label2 = new JLabel("Chemin: " + solution);

        jPanel.setLayout(new GridLayout(2,1));
        jPanel.add(label1);
        jPanel.add(label2);
        

        // Set the Drawing JPanel as the JFrame's content-pane
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(1,2));
        cp.add(canvas);
        cp.add(jPanel);


        setSize(1500,1500);
        setTitle("Graphe solution " + this.title);  // "super" JFrame sets the title
        setVisible(true);    // "super" JFrame show
    }

    /**
     * Define inner class DrawCanvas, which is a JPanel used for custom drawing.
     */
    private class DrawCanvas extends JPanel {
        // Override paintComponent to perform your own painting
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.WHITE);
            g.setColor(Color.BLUE);

            ArrayList<Lieu> lieux = new ArrayList<>();
            for (Integer integer : solution.getListeSolution()) {
                lieux.add(graphe.getLieux().get(integer));
            }

            Iterator iterator = lieux.iterator();
            Lieu lieu = null;
            lieu = (Lieu) iterator.next();
            while (iterator.hasNext()) {
                if (iterator.hasNext()) {
                    Lieu lieu2 = (Lieu) iterator.next();
                    if (lieu.getId() == 0 && lieu2.getId() == 0) {
                        Random rand = new Random();
                        Color color = new Color(rand.nextInt(0xFFFFFF));
                        g.setColor(color);
                    }
                    
                    g.drawLine(lieu.getCoordonnees().getX()*7,
                            lieu.getCoordonnees().getY()*7,
                            lieu2.getCoordonnees().getX()*7,
                            lieu2.getCoordonnees().getY()*7
                    );
                    lieu = lieu2;
                    g.drawString("" + lieu.getId(),lieu.getCoordonnees().getX()*7 + 5,lieu.getCoordonnees().getY()*7 + 5);
                }
            }

        }
    }

}
