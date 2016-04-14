package com.mycompany.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Describes panel that represents fields for displaying the roots of equation system.
 */
public class GMenu extends JPanel {

    private int countMem = 3;
    public JButton calc = new JButton("Calculate");
    public JLabel lb[];
    public JTextField sv[] = null;
    public JSpinner dim = new JSpinner(new SpinnerNumberModel(0, -100, 100, 1));
    JPanel sp = new JPanel();


    public GMenu() {
        updateMenu();
    }

    public void rez() {
        this.removeAll();
        setBackground(Color.gray);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        dim.setMaximumSize(new Dimension(100, 33));
        dim.setValue(countMem);
        this.add(dim);

        JPanel pBtn = new JPanel();
        pBtn.add(calc);
        pBtn.setBackground(Color.gray);

        this.add(pBtn);


        lb = new JLabel[countMem];


        for (int i = 0; i < countMem; i++) {
            sp = new JPanel();
            lb[i] = new JLabel();

            sp.setBackground(Color.gray);
            lb[i].setText(new StringBuilder("<html><font size=\"5\" face=\"Forte\">X" + "</font>" + (i + 1) + "<font size=\"5\" face=\"Forte\">  =" + "</font>").toString());

            sv[i].setFont(new Font("Verdana", Font.ITALIC, 10));
            sv[i].setDisabledTextColor(Color.BLUE);
            sv[i].setColumns(5);
            sv[i].setEnabled(false);

            sp.add(lb[i]);
            sp.add(sv[i]);
            this.add(sp);
        }
    }

    public void updateMenu() {
        this.removeAll();
        setBackground(Color.gray);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        dim.setMaximumSize(new Dimension(100, 33));
        dim.setValue(countMem);
        this.add(dim);

        JPanel pBtn = new JPanel();
        pBtn.add(calc);
        pBtn.setBackground(Color.gray);

        this.add(pBtn);

        lb = new JLabel[countMem];
        sv = new JTextField[countMem];

        for (int i = 0; i < countMem; i++) {
            sp = new JPanel();
            lb[i] = new JLabel();
            sv[i] = new JTextField();
            sp.setBackground(Color.gray);
            lb[i].setText(new StringBuilder("<html><font size=\"5\" face=\"Forte\">X" + "</font>" + (i + 1) + "<font size=\"5\" face=\"Forte\">  =" + "</font>").toString());

            sv[i].setColumns(5);
            sv[i].setEnabled(false);

            sp.add(lb[i]);
            sp.add(sv[i]);
            this.add(sp);
        }
    }

    public int getCountMem() {
        return countMem;
    }

    public void setCountMem(int countMem) {
        this.countMem = countMem;
    }
}
