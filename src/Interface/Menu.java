package Interface;

import main.MainTest;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tardy on 01/05/2018.
 */
public class Menu extends JFrame {

    private JPanel pan = new JPanel();
    private JButton boutonLancer = new JButton("Lancer");
    public JTextField jtfTemp = new JTextField("165");
    private JLabel labelTemp = new JLabel("Température initiale");
    public JTextField jtfProba = new JTextField("0.9");
    private JLabel labelProba = new JLabel("Probabilité");
    public JTextField jtfNbIteration = new JTextField("1000000");
    private JLabel labelNbIteration = new JLabel("Nombre iteration");
    public JTextField jtfNbIteAvtChgtTemp = new JTextField("6");
    private JLabel labelNbIteAvtChgtTemp = new JLabel("Nombre iteration avant changement de température");
    public JTextField jtfMu = new JTextField("0.99");
    private JLabel labelMu = new JLabel("Mu");
    private JTextField textRecuit = new JTextField("Recuit");
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
        boutonLancer.addActionListener(new MainTest());
        this.getContentPane().add(boutonLancer, BorderLayout.SOUTH);
        JPanel formCenter = new JPanel();
        JPanel formNorth = new JPanel();
        Font police = new Font("Arial", Font.BOLD, 14);

        //declaration formNorth

        formNorth.add(labelFichier);
        formNorth.add(jrb1);
        formNorth.add(jrb2);
        formNorth.add(jrb3);
        formNorth.add(jrb4);
        formNorth.add(jrb5);
        this.add(formNorth,BorderLayout.NORTH);

        formCenter.setLayout(new GridLayout(6,1,0,5));
        //declaration form
        jtfTemp.setFont(police);
        jtfTemp.setPreferredSize(new Dimension(150, 30));
        jtfTemp.setForeground(Color.BLUE);
        jtfProba.setFont(police);
        jtfProba.setPreferredSize(new Dimension(150, 30));
        jtfProba.setForeground(Color.BLUE);
        jtfNbIteration.setFont(police);
        jtfNbIteration.setPreferredSize(new Dimension(150, 30));
        jtfNbIteration.setForeground(Color.BLUE);
        jtfNbIteAvtChgtTemp.setFont(police);
        jtfNbIteAvtChgtTemp.setPreferredSize(new Dimension(150, 30));
        jtfNbIteAvtChgtTemp.setForeground(Color.BLUE);
        jtfMu.setFont(police);
        jtfMu.setPreferredSize(new Dimension(150, 30));
        jtfMu.setForeground(Color.BLUE);
        formCenter.add(labelTemp);
        formCenter.add(jtfTemp);
        formCenter.add(labelProba);
        formCenter.add(jtfProba);
        formCenter.add(labelNbIteration);
        formCenter.add(jtfNbIteration);
        formCenter.add(labelNbIteAvtChgtTemp);
        formCenter.add(jtfNbIteAvtChgtTemp);
        formCenter.add(labelMu);
        formCenter.add(jtfMu);
        pan.add(formCenter, BorderLayout.CENTER);

        this.setContentPane(pan);
        this.setVisible(true);
    }
}
