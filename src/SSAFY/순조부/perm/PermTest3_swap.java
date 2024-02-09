package SSAFY.순조부.perm;

import java.util.Arrays;
import java.util.Scanner;

public class PermTest3_swap {
    static int N, K;
    static int[] src;
    static int[] tgt;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        src = new int[N];
        visited = new boolean[N];
        tgt = new int[K];
        for (int i = 0; i < N; i++) src[i] = i + 1;
        perm(0);
        sc.close();
    }

    static void perm(int srcIdx) {
        if(srcIdx == K){
            for(int i=0; i<K; i++) System.out.print(src[i]+" ");
            System.out.println();
            return;
        }
        for (int i = srcIdx; i < N; i++) {
            System.out.println("***"+i+" "+srcIdx);
            swap(i, srcIdx);
            perm(srcIdx + 1);
            System.out.println(i+" "+srcIdx+"***");
            swap(i, srcIdx);
        }
    }

    static void swap(int i, int j) {
        int t = src[i];
        src[i] = src[j];
        src[j] = t;
    }
}
