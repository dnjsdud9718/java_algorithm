package SSAFY.study.silver.t5525;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, M;
    static char[] arr;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr = br.readLine().toCharArray();
        dp = new int[M];


        for (int i = 1; i < M-1; i++) {
            if (arr[i] == 'O' && arr[i - 1] == 'I' && arr[i + 1] == 'I') {
                if(i==1 || dp[i-2] == 0) dp[i]++;
                else if(dp[i-2] != 0) {
                    dp[i] = dp[i - 2] + 1;
                    dp[i - 2] = 0;
                }
            }
        }
//        System.out.println(Arrays.toString(dp));
        int answer = 0;
        for (int i = 1; i < M - 1; i++) {
            if (dp[i] != 0 && dp[i] >= N) {
                answer += dp[i] - N + 1;
            }
        }
        System.out.println(answer);
        br.close();
    }
}
