package Interface;

import metier.Graphe;
import metier.Lieu;
import metier.Solution;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

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
    public List<Color> colorList;
    public int indiceCurrentColor;

    // Constructor to set up the GUI components and event handlers
    public InterfaceGraphique(Solution solution, Graphe graphe, String title,double coutSolution) {
        this.solution = solution;
        this.graphe = graphe;
        this.title = title;
        this.coutSolution = coutSolution;
        colorList = new LinkedList<>();
        indiceCurrentColor = 0;
        setColors();
        JPanel jPanel = new JPanel();

        Font titleFont = new Font("Arial", Font.BOLD, 20);

        canvas = new DrawCanvas();    // Construct the drawing canvas
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        Border blackline = BorderFactory.createLineBorder(Color.black);
        TitledBorder titleCout = BorderFactory.createTitledBorder(
                blackline, "Coût",0,0,titleFont);
        titleCout.setTitleJustification(TitledBorder.CENTER);

        TitledBorder titleChemin = BorderFactory.createTitledBorder(
                blackline, "Chemin",0,0,titleFont);
        titleChemin.setTitleJustification(TitledBorder.CENTER);

        JLabel label1 = new JLabel("Coût de cette solution: " + coutSolution);
        JPanel jPanel2 = new JPanel();

        label1.setBorder(titleCout);
        jPanel2.setBorder(titleChemin);
        ArrayList<JLabel> jLabelArrayList = new ArrayList<>();

        String chemin = "";
        Integer lastInteger = 1;
        for (Integer integer : solution.getListeSolution()) {
            if (integer == 0 && lastInteger == 0) {
                jLabelArrayList.add(new JLabel(chemin));
                chemin = "";
            }
            chemin += " " + integer;
            lastInteger = integer;
        }
        jLabelArrayList.add(new JLabel(chemin));
        jPanel2.setLayout(new GridLayout( jLabelArrayList.size(),2));
        for (JLabel jLabel: jLabelArrayList) {
            RectDraw rectDraw = new RectDraw(colorList.get(indiceCurrentColor));
            jPanel2.add(rectDraw);
            jPanel2.add(jLabel);
            indiceCurrentColor++;
        }
        indiceCurrentColor = 0;
        jPanel.setLayout(new GridLayout(2 ,1));

        jPanel.add(label1);
        jPanel.add(jPanel2);



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
            g.setColor(colorList.get(indiceCurrentColor));
            indiceCurrentColor++;
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
                        Color color = colorList.get(indiceCurrentColor);
                        indiceCurrentColor++;
                        g.setColor(color);
                    }
                    
                    g.drawLine(lieu.getCoordonnees().getX()*7,
                            lieu.getCoordonnees().getY()*7,
                            lieu2.getCoordonnees().getX()*7,
                            lieu2.getCoordonnees().getY()*7
                    );
                    g.drawLine(lieu.getCoordonnees().getX()*7 +1,
                            lieu.getCoordonnees().getY()*7 +1,
                            lieu2.getCoordonnees().getX()*7 +1,
                            lieu2.getCoordonnees().getY()*7+1
                    );
                    lieu = lieu2;
                    g.drawString("" + lieu.getId(),lieu.getCoordonnees().getX()*7 + 5,lieu.getCoordonnees().getY()*7 + 5);
                }
            }
            indiceCurrentColor = 0;

        }
    }

    private class RectDraw extends JPanel {

        Color color;
        public RectDraw(Color color) {
            super();
            this.color = color;
        }
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawRect(230,40,10,10);
            g.setColor(color);
            g.fillRect(230,40,10,10);
        }
    }

    private void setColors(){

        ArrayList<Lieu> lieux = new ArrayList<>();
        for (Integer integer : solution.getListeSolution()) {
            lieux.add(graphe.getLieux().get(integer));
        }
        int i = 0;
        Iterator iterator = lieux.iterator();
        Lieu lieu = null;
        lieu = (Lieu) iterator.next();
        while (iterator.hasNext()) {
            if (iterator.hasNext()) {
                Lieu lieu2 = (Lieu) iterator.next();
                if (lieu.getId() == 0 && lieu2.getId() == 0) {
                    Random rand = new Random();
                    Color color = new Color(rand.nextInt(0xFFFFFF));
                    colorList.add(color);
                }

                lieu = lieu2;
            }
        }
        Random rand = new Random();
        Color color = new Color(rand.nextInt(0xFFFFFF));
        colorList.add(color);
    }

}
