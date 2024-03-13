package SSAFY.algo.baekjoon.t1081;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int L, R;
    static long ten;
    static long[] sum = new long[10];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        L = sc.nextInt();
        R = sc.nextInt();
        ten = 1L;
        if (L == 0) {
            L = 1;
        }
        while (L <= R) {
            while (L % 10 != 0 && L <= R) {
                calc(L, ten);
                L++;
            }
            if(L > R) break;
            while (R % 10 != 9 && L <= R) {
                calc(R, ten);
                R--;
            }
            for (int i = 1; i < 10; i++) {
                sum[i] += i * (R / 10 - L / 10 + 1) * ten;
            }
            L /= 10;
            R /= 10;
            ten *= 10;
        }
//        System.out.println(Arrays.toString(sum));
        long answer = 0;
        for (int i = 1; i < 10; i++) {
            answer += sum[i];
        }
        System.out.println(answer);
        sc.close();
    }

    static void calc(long x, long ten) {
        while (x > 0) {
            sum[(int)x%10] += (x % 10) * ten;
            x /= 10;
        }
    }
}


