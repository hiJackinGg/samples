package com.mycompany.logic;

/**
 * Class represents algorithms to determine roots of equation system.
 */
public class MatrixProps {

    public MatrixProps() {
    }

    /**
     * Bareiss algorithm to find determinant of any matrix (if it has).
     * @param mx
     * @return
     */
    public static double bareissDeterminant(double mx[][]) {

        double denom = 1;
        int exchanges = 0;

        for (int l1 = 0; l1 < mx.length - 1; ++l1) {
            int maxN = l1;
            double maxValue = Math.abs(mx[l1][l1]);
            for (int l2 = l1 + 1; l2 < mx.length; ++l2) {
                double value = Math.abs(mx[l2][l1]);
                if (value > maxValue) {
                    maxN = l2;
                    maxValue = value;
                }
            }

            if (maxN > l1) {
                double temp[] = mx[l1];
                mx[l1] = mx[maxN];
                mx[maxN] = temp;
                ++exchanges;
            } else {
                if (maxValue == 0) return maxValue;
            }

            double value1 = mx[l1][l1];

            for (int l2 = l1 + 1; l2 < mx.length; ++l2) {
                double value2 = mx[l2][l1];
                mx[l2][l1] = 0;
                for (int c = l1 + 1; c < mx.length; ++c)
                    mx[l2][c] = (mx[l2][c] * value1 - mx[l1][c] * value2) / denom;
            }

            denom = value1;
        }

        if (exchanges % 2 == 1)
            return -mx[mx.length - 1][mx.length - 1];
        else return mx[mx.length - 1][mx.length - 1];
    }

    /**
     * Transponation of matrix.
     * @param mx
     */
    public static void transposeMx(double mx[][]) {
        int i = 0, j = 0;

        for (; i < mx.length; i++) {
            j = i;
            for (; j < mx.length; j++) {
                double buf = mx[i][j];
                mx[i][j] = mx[j][i];
                mx[j][i] = buf;
            }
        }

    }

    /**
     * Defining a matrix addition.
     * @param mx
     */
    private static void algAdditionMx(double mx[][]) {
        int row = mx.length, col = mx.length;


        double mx2[][] = new double[row - 1][col - 1];
        double rez[][] = new double[row][col];


        int iTemp, jTemp;

        double m[] = new double[(row - 1) * (col - 1)];

        int i2 = 0, j2 = 0;
        int c = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                iTemp = i;
                jTemp = j;
                for (int i1 = 0; i1 < row; i1++) {
                    for (int j1 = 0; j1 < col; j1++) {
                        if (i1 != iTemp && j1 != jTemp) {

                            double buffer = mx[i1][j1];

                            m[c++] = buffer;
                        }
                    }
                }
                int cc = 0;
                for (int ii = 0; ii < row - 1; ii++)
                    for (int jj = 0; jj < col - 1; jj++)
                        mx2[ii][jj] = m[cc++];

                double det;
                det = bareissDeterminant(mx2);
                det = det * Math.pow(-1, (iTemp + jTemp));
                rez[iTemp][jTemp] = det;

                for (int k = 0; k < row - 1; k++)
                    for (int g = 0; g < col - 1; g++)
                        mx2[k][g] = 0.0;
                c = 0;
            }
        }

        for (int i = 0; i < row; i++)
            mx[i] = rez[i].clone();

    }

    /**
     * Finds the roots of equation system by Kramer algorithm (reverse matrix).
     * @param mx consists of equations coefficients.
     * @param vc values after "="
     * @return
     * @throws CloneNotSupportedException
     */
    public static Vector reverseResolveMx(Matrix mx, Vector vc) throws CloneNotSupportedException {

        int row = mx.getMatrix().length, col = mx.getMatrix().length;

        Matrix detMx = (Matrix) mx.clone();

        double determinant = bareissDeterminant(detMx.getMatrix());


        algAdditionMx(mx.getMatrix());
        transposeMx(mx.getMatrix());

        double d = 1 / determinant;
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                mx.getMatrix()[i][j] = mx.getMatrix()[i][j] * d;


        double vector[] = new double[row];
        double temp = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                temp += mx.getMatrix()[i][j] * vc.getVector()[j];
            }

            vector[i] = temp;
            temp = 0;
        }

        return new Vector(vector);

    }
}

