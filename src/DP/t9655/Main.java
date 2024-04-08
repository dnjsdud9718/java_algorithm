package DP.t9655;

import java.util.Scanner;

public class Main {
    static int N;
    static final String SK = "SK";
    static final String CY = "CY";
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        N = sc.nextInt();
        System.out.println(N % 2 == 0 ? CY : SK);
        sc.close();
    }
}
