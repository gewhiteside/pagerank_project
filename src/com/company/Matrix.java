package com.company;

public class Matrix {
    static double[][] A = {{4.00, 3.00}, {2.00, 1.00}};
    static double[][] B = {{-0.500, 1.500}, {1.000, -2.0000}};

    public static double[][] multiplyMatricies(double[][] A, double[][] B) {

        int aRows = A.length;
        int aColumns = A[0].length;
        int bRows = B.length;
        int bColumns = B[0].length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
        }

        double[][] C = new double[aRows][bColumns];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                C[i][j] = 0.00000;
            }
        }

        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }

    public static double[][] multiplyMatrixScalar(double[][] A, double x) {
        int aRows = A.length;
        int aColumns = A[0].length;

        double[][] B = new double[aRows][aColumns];
        for (int i=0; i<aRows; i++) {
            for (int j=0; j<aColumns; j++) {
                B[i][j] = A[i][j] * x;
            }
        }

        return B;
    }

    public static double[][] addMatricies(double[][] A, double[][] B) {
        int aRows = A.length;
        int aColumns = A[0].length;
        int bRows = B.length;
        int bColumns = B[0].length;

        if (!(aRows == bRows && aColumns == bColumns)) {
            throw new IllegalArgumentException("A and B are not the same size");
        }

        double[][] C = new double[aRows][aColumns];
        for (int i=0; i<aRows; i++) {
            for (int j=0; j<aColumns; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }

        return C;
    }

    public static void printM(double[][] M) {
        int mRows = M.length;
        int mColumns = M[0].length;

        System.out.print("[");
        // print first n-1 rows
        for (int i=0; i<mRows-1; i++) {
            System.out.print("[");
            for (int j=0; j<mColumns-1; j++) {
                System.out.print(""+M[i][j]+", ");
            }
            System.out.print(""+M[i][mColumns-1]+"],\n");
        }
        // print last row
        System.out.print("[");
        for (int i=0; i<mRows-1; i++) {
            System.out.print(""+M[mRows-1][i]+", ");
        }
        System.out.print(""+M[mRows-1][mColumns-1]+"]]\n");
    }
}
