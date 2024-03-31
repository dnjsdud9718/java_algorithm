package SSAFY.study.week9.t2869;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int N = sc.nextInt();
        if (A == N) {
            System.out.println(1);
        } else {
            if ((N - A) % (A - B) == 0) {
                System.out.println((N - A) / (A - B) + 1);
            } else {
                System.out.println((N - A) / (A - B) + 2);
            }
        }
    }
}

