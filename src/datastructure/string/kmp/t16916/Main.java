package datastructure.string.kmp.t16916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[] S, P;
    static int sLen, pLen;
    static int[] pi;
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine().toCharArray();
        sLen = S.length;
        P = br.readLine().toCharArray();
        pLen = P.length;
        kmp();

        br.close();
    }
    public static void kmp() {
        count = 0;
        makePi();
        int j = 0;
        for (int i = 0; i < sLen; i++) {
            while (j > 0 && S[i] != P[j]) {
                j = pi[j - 1];
            }
            if (S[i] == P[j]) {
                if (j == pLen - 1) {
                    count++;
                    j = pi[j];
                } else j++;
            }
        }
        System.out.println(count > 0 ? 1 : 0);
    }

    public static void makePi() {
        pi = new int[pLen];
        int j=0;
        for (int i = 1; i < pLen; i++) {
            while (j > 0 && P[i] != P[j]) {
                j = pi[j - 1];
            }
            if (P[i] == P[j]) {
                pi[i] = ++j;
            }
        }
    }
}
