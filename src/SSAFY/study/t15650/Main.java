package SSAFY.study.t15650;

import java.util.Scanner;

// 15650 N과 M(2)
// 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
// 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
// 고른 수열은 오름차순이어야 한다.

// 첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)

// 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다.
// 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
// 수열은 사전 순으로 증가하는 순서로 출력해야 한다.

public class Main {
    static int N, K;
    static int[] src;
    static int[] tgt;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        tgt = new int[K];
        comb(1, 0);
        System.out.println(sb.toString());
        sc.close();
    }
    public static void comb(int srcIdx, int tgtIdx){
        if(tgtIdx == K){
            for(int i=0; i<K; i++){
                sb.append(tgt[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i=srcIdx; i<=N; i++){
            tgt[tgtIdx] = i;
            comb(i+1, tgtIdx+1);
        }
    }
}
