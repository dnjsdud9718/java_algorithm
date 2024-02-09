package SSAFY.순조부.perm;

import java.util.Scanner;

// 중복순열
// 방문처리를 할 필요가 없다.
public class PermRep {
    static int N, K;
    static int[] src;
    static int[] tgt;
    static int COUNT = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        src = new int[N];
        tgt = new int[K];
        System.out.print("src: ");
        for(int i=0; i<N; i++){
            src[i] = (int)(Math.random()*100)+1;
            System.out.printf("%d ", src[i]);
        }
        System.out.println();
        permWithRep(0);
        System.out.println("CNT: "+COUNT);
        sc.close();
    }
    public static void permWithRep(int cnt){
        if(cnt == K){
            for(int i=0; i<K; i++) System.out.print(tgt[i]+" ");
            System.out.println();
            COUNT++;
            return;
        }
        for(int i=0; i<N; i++){
            tgt[cnt] = src[i];
            permWithRep(cnt+1);
        }
    }
}
