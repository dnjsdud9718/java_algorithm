package SSAFY.순조부.perm;

import java.util.Scanner;

// 순열 => 방무처리를 배열로...
public class PermBasic {
    static int N, K;
    static int[]src;
    static int[]tgt;
    static boolean[] visited;
    static int COUNT=0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        src = new int[N];
        visited = new boolean[N];
        tgt = new int[K];
        System.out.print("src: ");
        for(int i=0; i<N; i++){
            // src[i] = (int)(Math.random()*100)+1;
            src[i] = i+1;
            System.out.print(src[i]+" ");
        }
        System.out.println();
        perm(0);
        System.out.println("CNT:"+COUNT);
        sc.close();
    }
    public static void perm(int cnt){
        if(cnt == K){
            COUNT++;
            for(int i=0;i<K; i++) System.out.printf("%d ", tgt[i]);
            System.out.println();
            return;
        }
        for(int i=0; i<N; i++){
            if(visited[i]) continue;
            visited[i] = true;
            tgt[cnt] = src[i];
            perm(cnt+1);
            visited[i] = false;
        }
    }
}
