package com.mycompany.logic;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.util.Arrays;

/**
 * The class wraps a vector (array) of double type.
 */
public class Vector {

    private double[] vector;

    public Vector(int length) {
        vector = new double[length];
    }

    public Vector(double vc[]) {
        vector = vc;
    }

    public <T extends JTextComponent> Vector(T vc[]) {
        this(vc.length);

        for (int i = 0; i < vc.length; i++)
            vector[i] = Double.parseDouble(vc[i].getText());
    }

    /**
     * Retrieves values from GUI component (JTextComponent).
     * @return
     */
    public JTextField[] getJTextVector() {
        JTextField[] vs = new JTextField[vector.length];
        for (int i = 0; i < vector.length; i++)
            vs[i] = new JTextField(String.valueOf(vector[i]));


        return vs;
    }

    public double[] getVector() {
        return vector;
    }

    public void setVector(double[] vector) {
        this.vector = vector;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "vector=" + Arrays.toString(vector) +
                '}';
    }
}
