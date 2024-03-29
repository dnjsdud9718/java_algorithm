package datastructure.string.kmp.t16172;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static char[] src, pat;
    static int sLen, pLen;
    static int[] pi;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        src = sc.next().toCharArray();
        sLen = src.length;
        pat = sc.next().toCharArray();
        pLen = pat.length;
//        System.out.println(Arrays.toString(src));
//        System.out.println(Arrays.toString(pat));
        kmp();
        sc.close();
    }
    static void kmp() {
        int answer = 0;
        make();
        int j=0;
        for (int i = 0; i < sLen; i++) {
            if(Character.isDigit(src[i]))continue;
            while (j > 0 && src[i] != pat[j]) {
                j = pi[j - 1];
            }
            if (src[i] == pat[j]) {
                if (j == pLen - 1) {
                    answer++;
                    break;
//                    j = pi[j];
                } else {
                    j++;
                }
            }
        }
        System.out.println(answer > 0 ? 1 : 0);
    }
    static void make() {
        pi = new int[pLen];
        int j = 0;
        for (int i = 1; i < pLen; i++) {
            if(Character.isDigit(src[i]))continue;
            while (j > 0 && src[i] != pat[j]) {
                j = pi[j - 1];
            }
            if (src[i] == pat[j]) {
                pi[i] = ++j;
            }
        }
    }
}

