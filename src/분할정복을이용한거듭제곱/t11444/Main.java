package 분할정복을이용한거듭제곱.t11444;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static final int MOD = 1_000_000_007;
    static long exp;
    static long[][] base = {
            {1, 1},
            {1, 0}
    };
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        exp = sc.nextLong();
        if(exp <= 1) System.out.println(exp);
        else System.out.println(fib(base, exp-1));

    }

    public static long[][] mul(long[][] m1, long[][] m2) {
        long[][] ret = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                long sum = 0;
                for (int k = 0; k < 2; k++) {
                    sum += ((m1[i][k]%MOD) * (m2[k][j]%MOD));
                }
                ret[i][j] = sum%MOD;
            }
        }
        return ret;
    }
    public static long fib(long[][] base, long exp) {
        if(exp == 0) return 0;
        long ret[][] = {{1,1},{1,0}};
        while (exp > 0) {
            if (exp % 2 == 1) {
                ret = mul(ret, base);
            }
            base = mul(base, base);
            exp >>= 1;
        }
        return ret[0][1];
    }
}
