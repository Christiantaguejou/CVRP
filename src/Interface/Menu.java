package Interface;

import main.MainTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tardy on 01/05/2018.
 */
public class Menu extends JFrame {

    public JPanel pan = new JPanel();
    public JButton boutonLancer = new JButton("Lancer");
    public JTextField jtfTemp = new JTextField("165");
    public JLabel labelTemp = new JLabel("Température initiale");
    public JTextField jtfProba = new JTextField("0.9");
    public JLabel labelProba = new JLabel("Probabilité");
    public JTextField jtfNbIteration = new JTextField("1000000");
    public JLabel labelNbIteration = new JLabel("Nombre iteration");
    public JTextField jtfNbIteAvtChgtTemp = new JTextField("6");
    public JLabel labelNbIteAvtChgtTemp = new JLabel("Nombre iteration avant changement de température");
    public JTextField jtfMu = new JTextField("0.99");
    public JLabel labelMu = new JLabel("Mu");


    public Menu(){
        this.setTitle("Ma première fenêtre Java");
        this.setSize(400, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        pan.add(bouton);
        this.setContentPane(pan);
        this.setLayout(new BorderLayout());
        boutonLancer.addActionListener(new MainTest());
        this.getContentPane().add(boutonLancer, BorderLayout.SOUTH);
        JPanel form = new JPanel();
        Font police = new Font("Arial", Font.BOLD, 14);

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
        form.add(labelTemp);
        form.add(jtfTemp);
        form.add(labelProba);
        form.add(jtfProba);
        form.add(labelNbIteration);
        form.add(jtfNbIteration);
        form.add(labelNbIteAvtChgtTemp);
        form.add(jtfNbIteAvtChgtTemp);
        form.add(labelMu);
        form.add(jtfMu);
        pan.add(form, BorderLayout.NORTH);
        this.setContentPane(pan);
        this.setVisible(true);
    }
}
