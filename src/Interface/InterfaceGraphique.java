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
        // or "setContentPane(canvas);"

        setDefaultCloseOperation(EXIT_ON_CLOSE);   // Handle the CLOSE button
        pack();              // Either pack() the components; or setSize()
        setTitle("......");  // "super" JFrame sets the title
        setVisible(true);    // "super" JFrame show
    }

    /**
     * Define inner class DrawCanvas, which is a JPanel used for custom drawing.
     */
    private class DrawCanvas extends JPanel {
        // Override paintComponent to perform your own painting
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);     // paint parent's background
            setBackground(Color.BLACK);  // set background color for this JPanel

            // Your custom painting codes. For example,
            // Drawing primitive shapes
//            g.setColor(Color.YELLOW);    // set the drawing color
//            g.drawLine(30, 40, 100, 200);
//            g.drawOval(150, 180, 10, 10);
//            g.drawRect(200, 210, 20, 30);
//            g.setColor(Color.RED);       // change the drawing color
//            g.fillOval(300, 310, 30, 50);
//            g.fillRect(400, 350, 60, 50);
            // Printing texts
            g.setColor(Color.WHITE);
//            g.setFont(new Font("Monospaced", Font.PLAIN, 12));
//            g.drawString("Testing custom drawing ...", 10, 20);
            ArrayList<Lieu> lieux = new ArrayList<>();
            for (Integer integer : solution.getListeSolution()) {
                lieux.add(graphe.getLieux().get(integer));
            }

            Iterator iterator = lieux.iterator();
            Lieu lieu = null;
            while (iterator.hasNext()) {
                lieu = (Lieu) iterator.next();
                if (iterator.hasNext()) {
                    Lieu lieu2 = (Lieu) iterator.next();
                    g.drawLine(lieu.getCoordonnees().getX(),
                            lieu.getCoordonnees().getY(),
                            lieu2.getCoordonnees().getX(),
                            lieu2.getCoordonnees().getY()
                    );
                }
            }

        }
    }

}
