package com.company;

public class Vector {
    static double[][] A = {{4.00}, {1.00}};

    public static double norm(double[][] v) {
        int n = v.length;

        double norm = 0.0;
        for (int i=0; i<n; i++) {
            norm += (v[i][0] * v[i][0]);
        }
        norm = Math.sqrt(norm);
        return norm;
    }

    public static void printV(double[][] v) {
        int n = v.length;

        System.out.print("[");
        for (int i=0; i<n-1; i++) {
            System.out.print("" + v[i][0] + ",\n");
        }
        System.out.print("" + v[n-1][0] + "]\n");
    }
}
