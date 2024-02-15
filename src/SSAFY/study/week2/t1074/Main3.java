package SSAFY.study.week2.t1074;

import java.util.Scanner;

public class Main3 {
    static int N, Y, X, CNT;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        Y = sc.nextInt();
        X = sc.nextInt();
        CNT = 0;
        logic(0, 0, 1<<N);
        System.out.println(CNT);
        sc.close();
    }

    public static void logic(int y, int x, int n) {
        if(n == 1) return;

        n /= 2;
        if (Y < y + n && X < x + n) {
            logic(y, x, n);
        } else if (Y < y + n && X >= x + n) {
            CNT += n*n;
            logic(y, x + n, n);
        } else if (Y >= y + n && X < x + n) {
            CNT += n*n*2;
            logic(y + n, x, n);
        } else {
            CNT += n*n*3;
            logic(y + n, x + n, n);
        }
    }
}
