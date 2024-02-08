package SSAFY.permutation;

import java.util.Scanner;

public class PermTest {
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

    static void perm(int tgtIdx) {
        if(tgtIdx == K){
            for(int i=0; i<K; i++) System.out.print(tgt[i]+" ");
            System.out.println();
            return;
        }
        for (int i = 0; i < N; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            tgt[tgtIdx] = src[i];
            perm(tgtIdx + 1);
            visited[i] = false;
        }
    }
}
