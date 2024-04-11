package DP.t15989;

import java.util.*;
import java.io.*;
public class Main
{
    static int T, N;
    static int[][] dp;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        dp = new int[10001][3];
        for(int i=0; i<10001;i++){
            for(int j=0; j<3; j++) dp[i][j] = -1;
        }
        for(int t=1; t<=T;t++){
            N = Integer.parseInt(br.readLine());
            sb.append(solve(N, 3)).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
    public static int solve(int n, int k){
        if(n == 0) {
            dp[n][k-1] = 1;
            return 1;
        }
        if(n < 0) {
            return 0;
        }
        if(dp[n][k-1] != -1) return dp[n][k-1];
        int ret = 0;
        for(int i=k; i>0;i--) ret += solve(n-i, i);
        dp[n][k-1] = ret;
        return ret;
    }
}
