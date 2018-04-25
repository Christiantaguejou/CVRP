package Interface;

import metier.Graphe;
import metier.Lieu;
import metier.Solution;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

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
    private Graphe graphe;

    // Constructor to set up the GUI components and event handlers
    public InterfaceGraphique(Solution solution, Graphe graphe) {
        this.solution = solution;
        this.graphe = graphe;

        canvas = new DrawCanvas();    // Construct the drawing canvas
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        // Set the Drawing JPanel as the JFrame's content-pane
        Container cp = getContentPane();
        cp.add(canvas);

        setDefaultCloseOperation(EXIT_ON_CLOSE);   // Handle the CLOSE button
        setSize(1500,1500);
        setTitle("Graphe solution");  // "super" JFrame sets the title
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
                    g.drawLine(lieu.getCoordonnees().getX()*10,
                            lieu.getCoordonnees().getY()*10,
                            lieu2.getCoordonnees().getX()*10,
                            lieu2.getCoordonnees().getY()*10
                    );
                    lieu = lieu2;
                }
            }

        }
    }

}
