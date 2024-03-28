package SSAFY.algo.LIS_3307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static final int INF = 987_654_321;
    static int T, N;
    static int[] src;
    static int[] dp;
    static int dLen;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            src = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                src[i] = Integer.parseInt(st.nextToken());
            }
            dp = new int[N];
            dp[0] = src[0];
            dLen = 0;
            for (int i = 1; i < N; i++) {
                if(dp[dLen] < src[i]){
                    dp[++dLen] = src[i];
                }else{
                    int x = lower(0, dLen + 1, src[i]);
                    dp[x] = src[i];
                }
            }
            sb.append(dLen+1).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    static int lower(int l, int r, int x) {
        while(l < r){
            int mid = (l+r)/2;
            if (dp[mid] >= x) {
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        return r;
    }
}
