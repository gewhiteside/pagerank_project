package com.company;

import java.util.ArrayList;

public class Main {

    // example 1
    // 4,0;0,1;0,2;1,3;2,3;2,4;3,4

    public static void main(String[] args) {
        boolean v = false;
        int n = 5;
        double d = .85;
        double error = .001;

        // set verbose mode
        if (args.length > 0 && "-v".equals(args[0])) {
            v = true;
            System.out.println("verbose mode");
            // need to print verbose output
        }

        if (v) {
            System.out.println("Number of pages is set to: " + n);
            System.out.println("Damping factor is set to: " + d);
            System.out.println("Error margin is set to: " + error);
        }

        // read list of pairs
        ArrayList<Pair> links = Input.readPairs();

        if (v) {
            System.out.println("\nList of links is:");
            System.out.println(links.toString());
        }

        // initialize matrix
        double[][] M = new double[n][n];
        int[] numLinks = new int[n];

        // build non-stochastic matrix
        for (Pair link : links) {
            numLinks[link.getX()]++;
            M[link.getY()][link.getX()] = 1;
        }

        if (v) {
            System.out.println("\nNumber of links from each page is:");
            // print numLinks
            System.out.print("[");
            for (int i=0; i<n-1; i++) {
                System.out.print(""+numLinks[i]+",");
            }
            System.out.print(""+numLinks[n-1]+"]\n");
            System.out.println("\nLink matrix is:");
            Matrix.printM(M);
        }

        // create stochastic matrix
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (numLinks[j] > 0) {
                    M[i][j] = M[i][j] / numLinks[j];
                } else {
                    M[i][j] = 1 / n;
                }
            }
        }

        if (v) {
            System.out.println("\nStochastic matrix is:");
            System.out.println("(where M[i][j] is the undampened probability of moving from j to i)");
            Matrix.printM(M);
        }

        // set up initial prev pr = all infinity
        double[][] prevPR = new double[n][1];
        for (int i=0; i<n; i++) {
            prevPR[i][0] = Double.POSITIVE_INFINITY;
        }

        // set up matrix of all ones
        double[][] ones = new double[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                ones[i][j] = 1;
            }
        }

        // set up transition matrix with damping factor
        double[][] MPrime = Matrix.addMatricies(Matrix.multiplyMatrixScalar(ones, ((1-d)/n)),
                Matrix.multiplyMatrixScalar(M, d));

        if (v) {
            System.out.println("\nMatrix with damping factor:");
            Matrix.printM(MPrime);
        }

        // initialize starting pagerank vector
        double[][] pr = new double[n][1];
        for (int i=0; i<n; i++) {
            pr[i][0] = 1/(n*1.0);
        }

        if (v) {
            System.out.println("\nInitial PageRank vector:");
            Vector.printV(pr);
        }

        // track the number of iterations
        int numIterations = 0;

        // calculate pagerank
        while (Vector.norm(Matrix.addMatricies(pr,
                Matrix.multiplyMatrixScalar(prevPR, -1))) > error) {
            numIterations++;
            prevPR = pr;
            pr = Matrix.multiplyMatricies(MPrime, pr);
        }

        if (v) {
            System.out.println("\nConverged to a steady state solution in " + numIterations
            + " iterations");
        }

        System.out.println("PageRank:");
        Vector.printV(pr);
    }
}
