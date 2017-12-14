package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Input {
    public static ArrayList<Pair> readPairs() {
        ArrayList<Pair> lop = new ArrayList<>();

        // read user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input a list of links:");
        String input = sc.next();

        // form ArrayList from input
        sc = new Scanner(input).useDelimiter("[,;]");
        while (sc.hasNextInt()) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            Pair aPair = new Pair(i,j);
            lop.add(aPair);
        }

        return lop;
    }
}
