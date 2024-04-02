package SSAFY.study.t13977;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int P = 1_000_000_007;
    static int M, N, K, max;
    static long[] dp;
    static int[][] combs;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        M = Integer.parseInt(br.readLine());
        combs = new int[M][2];
        max = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            combs[i][0] = N;
            combs[i][1] = K;
            max = Math.max(max, N);
        }
//        System.out.println("max = " + max);

        // n1 dp
        dp = new long[max + 1];
        dp[1] = 1;
        for (int i = 2; i <= max; i++) {
            dp[i] = dp[i - 1] * i % P;
        }
//        System.out.println(Arrays.toString(dp));
        // calc
        for (int i = 0; i < M; i++) {
            N = combs[i][0];
            K = combs[i][1];
            if (K == 0 || N == K) {
                sb.append(1).append("\n");
                continue;
            }
//            long answer = dp[N] * pow(dp[N - K] * dp[K] % P, P - 2) % P;
            long answer = dp[N] * ((pow(dp[N - K], P - 2) % P) * (pow(dp[K], P - 2)%P) % P) % P;
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    static long pow(long base, long exp) {
        if(exp == 0) return 1;
        if(exp == 1) return base;

        long ret = 1;
        while (exp > 0) {
            if (exp % 2 == 1) {
                ret = ret * base % P;
            }
            base = (base * base) % P;
            exp = exp >> 1;
        }
        return ret;
    }
}
