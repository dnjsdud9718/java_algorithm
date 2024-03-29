package datastructure.string.kmp.t1786;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static char[] T, P;
    static int tLen, pLen, cnt;
    static int[] pi, loc;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = br.readLine().toCharArray();
        tLen = T.length;
        P = br.readLine().toCharArray();
        pLen = P.length;

        // pattern table
        makePi();
        System.out.println(Arrays.toString(pi));
        // search
        cnt = 0;
        loc = new int[tLen];
        search();
        System.out.println(cnt);
        for (int i = 0; i < cnt; i++) {
            System.out.print(loc[i] + " ");
        }
        br.close();
    }
    static void search() {
        int j=0;
        for (int i = 0; i < tLen; i++) {
            while (j > 0 && T[i] != P[j]) {
                System.out.println("hello");
                j = pi[j - 1];
            }

            if (T[i] == P[j]) {
                if (j == pLen - 1) {
                    loc[cnt] = i - j + 1;
                    cnt++;
                    System.out.printf("%d ", j);
                    j = pi[j];
                    System.out.println(j);
                } else {
                    j++;
                }
//                System.out.println("j = " + j);
            }
        }
    }
    static void makePi() {
        pi = new int[pLen];
        int j=0;
        for (int i = 1; i < pLen; i++) {
            while (j > 0 && P[i] != P[j]) {
                System.out.printf("%d ", j);
                j = pi[j - 1];
                System.out.println(j);
            }
            if (P[i] == P[j]) {
                pi[i] = ++j;
            }
        }
    }
}
