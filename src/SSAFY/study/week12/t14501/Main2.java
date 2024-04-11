package SSAFY.study.week12.t14501;

import java.util.*;
import java.io.*;

// dp
public class Main2{
    static int N;
    static int[] t;
    static int[] v;
    static int[] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        t = new int[N];
        v = new int[N];
        dp = new int[N+1];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int tt = Integer.parseInt(st.nextToken());
            int vv = Integer.parseInt(st.nextToken());
            t[i] = tt;
            v[i] = vv;
        }
        for(int i=0;i < N; i++){
            if( i+t[i] <= N ){
                dp[i+t[i]] = Math.max(dp[i+t[i]], dp[i]+v[i]);
            }
            dp[i+1] = Math.max(dp[i+1], dp[i]);
        }
        System.out.println(dp[N]);
        br.close();
    }
}
