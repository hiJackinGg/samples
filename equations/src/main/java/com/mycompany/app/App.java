package com.mycompany.app;

import com.mycompany.GUI.GMainPanel;
import com.mycompany.GUI.GMenu;
import com.mycompany.logic.Matrix;
import com.mycompany.logic.MatrixProps;
import com.mycompany.logic.Vector;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class App extends JFrame {

    GMainPanel p = new GMainPanel();
    GMenu m = new GMenu();

    JButton clear = new JButton("Clear");

    App() {
        super("New App");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        JSplitPane splitMain = new JSplitPane();
        splitMain.setLeftComponent(new JScrollPane(m));
        splitMain.setRightComponent(new JScrollPane(p));

        splitMain.setOneTouchExpandable(true);
        splitMain.setDividerSize(20);

        add(splitMain);
        m.calc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Matrix mx = new Matrix(p.pm.mxFields);
                Vector vc = new Vector(p.pm.vecFields);
                Vector newVc = null;
                try {
                    newVc = new Vector(MatrixProps.reverseResolveMx(mx, vc).getVector());
                } catch (CloneNotSupportedException e1) {
                    e1.printStackTrace();
                }

                m.sv = newVc.getJTextVector();
                m.rez();
                m.updateUI();

            }
        });

                m.dim.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                int val = (Integer) m.dim.getValue();
                m.dim.setValue(val);
                m.setCountMem(val);
                p.pm.setCountMem(val);
                m.updateMenu();
                m.updateUI();

                p.pm.updateEq();

                p.pm.updateUI();


            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < p.pm.getCountMem(); i++)
                    p.pm.mxFields[i].setText("sd");

                p.pm.updateEq();

                p.pm.updateUI();

            }
        });

        JPanel p = new JPanel();
        setBackground(Color.green);
        p.add(clear);

        add(p, BorderLayout.SOUTH);
    }

    public static void main(String[] args) throws CloneNotSupportedException {

        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        new App();
                    }
                });
    }
}
