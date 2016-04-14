package com.mycompany.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Describes panel (grid) that represents fields for equation coefficients.
 */
public class GMatrix extends JPanel {

    private int countMem = 3;
    int j = 0;
    int index = 0;


    //matrix of equation system
    public JTextField[] mxFields;
    //vector of equation system
    public JTextField[] vecFields;


    public GMatrix() {
        updateEq();
    }

    /**
     * Drawing of redrawing fields in convenient form.
     */
    public void updateEq() {
        this.removeAll();

        mxFields = new JTextField[countMem * countMem];
        vecFields = new JTextField[countMem];

        for (int i = 0; i < countMem; i++) {
            vecFields[i] = new JTextField();
        }

        for (int i = 0; i < countMem * countMem; i++) {
            mxFields[i] = new JTextField(1);
        }

        for (int i = 0; i < countMem * countMem; i++) {
            index++;

            if (i % countMem == 0 && i != 0)
                index = 1;


            if (index != countMem) {

                add(mxFields[i]);
                add(new JLabel(new String("<html><font size=\"5\" face=\"Forte\">X" + "</font>" + index + "<font size=\"5\" face=\"Forte\">  +" + "</font>")));
            } else {
                add(mxFields[i]);
                add(new JLabel(new String("<html><font size=\"5\" face=\"Forte\">X" + "</font>" + index)));
                add(new JLabel("<html><font face=\"Forte\">=" + "</font>"));
                add(vecFields[j]);
                j++;
            }

        }
        j = 0;
        index = 0;
        setLayout(new GridLayout(countMem, countMem, 15, 15));
    }

    public int getCountMem() {
        return countMem;
    }

    public void setCountMem(int countMem) {
        this.countMem = countMem;
    }
}
