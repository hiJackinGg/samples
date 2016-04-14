package com.mycompany.logic;

import javax.swing.text.JTextComponent;
import java.util.Arrays;


/**
 * The class wraps a matrix of double type.
 */
    public class Matrix implements Cloneable {

    private double[][] matrix;

    //dimension of matrix
    Matrix(int dimension) {
        matrix = new double[(int) Math.sqrt(dimension)][(int) Math.sqrt(dimension)];
    }

    public Matrix(double mx[][]) {
        matrix = mx.clone();
    }

    /**
     * Retrieves values from GUI component (JTextComponent).
     * @param mx
     * @param <T>
     */
    public <T extends JTextComponent> Matrix(T mx[]) {
        this(mx.length);

        int k = 0;
        for (int i = 0; i < Math.sqrt(mx.length); i++) {
            for (int j = 0; j < Math.sqrt(mx.length); j++) {
                matrix[i][j] = Double.parseDouble(mx[k].getText());
                k++;
            }
        }
    }

    /**
     * Some algorithms may change the values of incoming matrix.
     * In these cases, a matrix has to be cloned to prevent been modified.
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {

        double matrix[][] = new double[this.matrix.length][this.matrix.length];

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix.length; j++)
                matrix[i][j] = this.matrix[i][j];

        Matrix mx = new Matrix(matrix);
        return mx;
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "matrix=" + Arrays.deepToString(matrix) +
                '}';
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }


}