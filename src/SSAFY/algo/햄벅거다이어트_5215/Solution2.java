package SSAFY.algo.햄벅거다이어트_5215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2 {
    static int T, N, L, MAX;
    static int[][]src;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            src = new int[N][2];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                src[i][0] = Integer.parseInt(st.nextToken());
                src[i][1] = Integer.parseInt(st.nextToken());
            }
            MAX = Integer.MIN_VALUE;
            subset(0,0, 0);
            sb.append("#").append(t).append(" ").append(MAX).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    static void subset(int idx, int sumK, int sumS) {
        if(idx == N){
            MAX = Math.max(MAX, sumS);
            return;
        }

        subset(idx + 1, sumK, sumS);
        // 가지치기
        if(L >= sumK + src[idx][1]) subset(idx + 1, sumK + src[idx][1], sumS + src[idx][0]);


    }

    static void swap(int i, int j) {
        int[] t = src[i];
        src[i] = src[j];
        src[j] = t;
    }
}
