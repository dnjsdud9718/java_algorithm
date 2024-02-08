package SSAFY.permutation;

import java.util.Scanner;

public class PermTest2 {
    static int N, K;
    static int[] src;
    static int[] tgt;
    static int CNT = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        src = new int[N];
        tgt = new int[K];
        for (int i = 0; i < N; i++) src[i] = i + 1;
        perm(0, 0);
        System.out.println("CNT "+CNT);
        sc.close();
    }

    static void perm(int tgtIdx, int mask) {
        if(tgtIdx == K){
            CNT++;
            for(int i=0; i<K; i++) System.out.print(tgt[i]+" ");
            System.out.println();
            return;
        }
        for (int i = 0; i < N; i++) {
            if((mask & (1<<i)) != 0) continue;
            tgt[tgtIdx] = src[i];
            perm(tgtIdx + 1, mask | (1<<i));
        }
    }
}
