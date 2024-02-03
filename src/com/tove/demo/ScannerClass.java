package com.tove.demo;

import java.util.Scanner;

public class ScannerClass {

    static Scanner scan = new Scanner(System.in);

    public static String getInput() {

        return scan.nextLine();
    }

    public static int getInt() {


        int x = scan.nextInt();
        scan.nextLine();
        return x;
    }
}
