package SSAFY.algo.baekjoon.t1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    시간 초과
    -> 해결 1(Main2) -> 파스칼 삼각형 -> 메모이제이션
    -> 해결 2(Main3) -> nCr = n-1Cr-1 + n-1Cr
        -> 증명: N개 중에 하나를 결정하고 A라고 하자. 그 후 A가 조합에 포함되는 경우와 포함되지 않는 경우를 생각해보자.
                포함되는 경우 -> n-1Cr-1을 뽑는다.
                포함되지 않는 경우 -> n-1Cr을 뽑는다.
                포함되는 경우와 포함되지 않는 경우를 합하면 nCr
 */
public class Main3 {
    static int N, K, COUNT;
    static int[][] dp;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        dp = new int[31][31];

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            for (int i = 0; i < 30; i++) {
                Arrays.fill(dp[i], -1);
            }
            comb(K, N);
            sb.append(dp[K][N]).append('\n');

        }
        System.out.println(sb);
        br.close();
    }

    static int comb(int n, int r) {
        // 기저 조건
        if(n==r || r == 0) return dp[n][r]=1;

        // 메모이제이션 활용
        if(dp[n][r] != -1) return dp[n][r];

        // 계산
        return dp[n][r] = comb(n - 1, r - 1) + comb(n - 1, r);
    }
}
