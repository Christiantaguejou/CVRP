package Interface;

import main.MainTest;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tardy on 01/05/2018.
 */
public class Menu extends JFrame {

    private JPanel pan = new JPanel();
    public JRadioButton selectedRadioButton;
    private JButton boutonLancerRecuit = new JButton("Lancer l'algorithme de Recuit");
    private JButton boutonLancerGen = new JButton("Lancer l'algorithme génétique");
    public JTextField jtfTemp = new JTextField("165");
    private JLabel labelTemp = new JLabel("Température initiale");
    public JTextField jtfNbIterationRecuit = new JTextField("1000000");
    private JLabel labelNbIterationRecuit = new JLabel("Nombre iteration");
    public JTextField jtfNbIteAvtChgtTemp = new JTextField("6");
    private JLabel labelNbIteAvtChgtTemp = new JLabel("Nombre iteration avant changement de température");
    public JTextField jtfMu = new JTextField("0.99");
    private JLabel labelMu = new JLabel("Mu");
    public JTextField jtfNbIterationGen = new JTextField("10000");
    private JLabel labelNbIterationGen = new JLabel("Nombre itérations");
    public JTextField jtfNbPopulation = new JTextField("150");
    private JLabel labelNbPopulation = new JLabel("Nombre population");
    private JLabel labelFichier = new JLabel("Fichier");
    public JRadioButton jrb1 = new JRadioButton("data01");
    public JRadioButton jrb2 = new JRadioButton("data02");
    public JRadioButton jrb3 = new JRadioButton("data03");
    public JRadioButton jrb4 = new JRadioButton("data04");
    public JRadioButton jrb5 = new JRadioButton("data05");


    public Menu(){
        this.setTitle("Launcher");
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(pan);
        this.setLayout(new BorderLayout());
        boutonLancerRecuit.addActionListener(new MainTest());
        boutonLancerRecuit.setName("Recuit");
        boutonLancerGen.addActionListener(new MainTest());
        boutonLancerGen.setName("Gen");
        JPanel formCenterRecuit = new JPanel();
        JPanel formNorth = new JPanel();
        JPanel formCenter = new JPanel();
        JPanel formCenterGen = new JPanel();
        Font police = new Font("Arial", Font.BOLD, 14);
        Font title = new Font("Arial", Font.BOLD, 20);

        Border blackline = BorderFactory.createLineBorder(Color.black);
        TitledBorder titleRecuit = BorderFactory.createTitledBorder(
                blackline, "Recuit",0,0,title);
        titleRecuit.setTitleJustification(TitledBorder.CENTER);
        formCenterRecuit.setBorder(titleRecuit);

        TitledBorder titleGen = BorderFactory.createTitledBorder(
                blackline, "Génétique",0,0,title);
        titleGen.setTitleJustification(TitledBorder.CENTER);
        formCenterGen.setBorder(titleGen);

        //declaration formNorth

        jrb1.setSelected(true);
        selectedRadioButton = jrb1;
        jrb1.addActionListener(new StateListener());
        jrb2.addActionListener(new StateListener());
        jrb3.addActionListener(new StateListener());
        jrb4.addActionListener(new StateListener());
        jrb5.addActionListener(new StateListener());

        formNorth.add(labelFichier);
        formNorth.add(jrb1);
        formNorth.add(jrb2);
        formNorth.add(jrb3);
        formNorth.add(jrb4);
        formNorth.add(jrb5);
        this.add(formNorth,BorderLayout.NORTH);

        formCenterRecuit.setLayout(new GridLayout(6,1,0,5));
        //declaration form
        jtfTemp.setFont(police);
        jtfTemp.setPreferredSize(new Dimension(150, 30));
        jtfTemp.setForeground(Color.BLUE);
        jtfNbIterationRecuit.setFont(police);
        jtfNbIterationRecuit.setPreferredSize(new Dimension(150, 30));
        jtfNbIterationRecuit.setForeground(Color.BLUE);
        jtfNbIteAvtChgtTemp.setFont(police);
        jtfNbIteAvtChgtTemp.setPreferredSize(new Dimension(150, 30));
        jtfNbIteAvtChgtTemp.setForeground(Color.BLUE);
        jtfMu.setFont(police);
        jtfMu.setPreferredSize(new Dimension(150, 30));
        jtfMu.setForeground(Color.BLUE);
        formCenterRecuit.add(labelTemp);
        formCenterRecuit.add(jtfTemp);
        formCenterRecuit.add(labelNbIterationRecuit);
        formCenterRecuit.add(jtfNbIterationRecuit);
        formCenterRecuit.add(labelNbIteAvtChgtTemp);
        formCenterRecuit.add(jtfNbIteAvtChgtTemp);
        formCenterRecuit.add(labelMu);
        formCenterRecuit.add(jtfMu);


        formCenterGen.setLayout(new GridLayout(2,1,5,5));
        jtfNbIterationGen.setFont(police);
        jtfNbIterationGen.setPreferredSize(new Dimension(150, 30));
        jtfNbIterationGen.setForeground(Color.BLUE);
        jtfNbPopulation.setFont(police);
        jtfNbPopulation.setPreferredSize(new Dimension(150, 30));
        jtfNbPopulation.setForeground(Color.BLUE);
        formCenterGen.add(labelNbIterationGen);
        formCenterGen.add(jtfNbIterationGen);
        formCenterGen.add(labelNbPopulation);
        formCenterGen.add(jtfNbPopulation);

        JPanel buttonFormRecuit = new JPanel();
        buttonFormRecuit.setSize(30,30);
        JPanel buttonFormGen = new JPanel();
        buttonFormGen.setSize(30,30);

        boutonLancerRecuit.setSize(new Dimension(150, 30));
        boutonLancerGen.setSize(new Dimension(150, 30));

        buttonFormRecuit.add(boutonLancerRecuit);
        buttonFormGen.add(boutonLancerGen);

        formCenter.setLayout(new GridLayout(2,1));
        formCenter.add(formCenterRecuit);
        formCenter.add(buttonFormRecuit,BorderLayout.CENTER);
        formCenter.add(formCenterGen);
        formCenter.add(buttonFormGen,BorderLayout.CENTER);

        pan.add(formCenter, BorderLayout.CENTER);

        this.setContentPane(pan);
        this.setVisible(true);
    }

    private class StateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedRadioButton != null) {
                selectedRadioButton.setSelected(false);
            }
            selectedRadioButton = (JRadioButton) e.getSource();
        }
    }
}
