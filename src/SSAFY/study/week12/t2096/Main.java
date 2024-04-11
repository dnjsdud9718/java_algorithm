package SSAFY.study.week12.t2096;

import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static int[][] map;
    static int[][][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][3];
        dp = new int[2][3][2];

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[i][0] = a;
            map[i][1] = b;
            map[i][2] = c;
        }
        for(int i=1; i<=N; i++){
            for(int j=0; j<3; j++){
                for(int k=0; k<2; k++){
                    int idx = (i+1)%2;
                    if(j==0){
                        if(k==0) dp[idx][j][k] = Math.max(dp[(idx+1)%2][j][k], dp[(idx+1)%2][j+1][k]);
                        else dp[idx][j][k] = Math.min(dp[(idx+1)%2][j][k], dp[(idx+1)%2][j+1][k]);
                    }
                    if(j==1){
                        if(k==0) dp[idx][j][k] = Math.max(Math.max(dp[(idx+1)%2][j-1][k], dp[(idx+1)%2][j+1][k]), dp[(idx+1)%2][j][k]);
                        else dp[idx][j][k] = Math.min(Math.min(dp[(idx+1)%2][j-1][k], dp[(idx+1)%2][j+1][k]), dp[(idx+1)%2][j][k]);
                    }
                    if(j==2){
                        if(k==0) dp[idx][j][k] = Math.max(dp[(idx+1)%2][j][k], dp[(idx+1)%2][j-1][k]);
                        else dp[idx][j][k] = Math.min(dp[(idx+1)%2][j][k], dp[(idx+1)%2][j-1][k]);
                    }
                    dp[idx][j][k] += map[i][j];
                }
            }
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i=0; i<3;i++){
            if(N%2 == 1){ // odd
                max = Math.max(max, dp[0][i][0]);
                min = Math.min(min, dp[0][i][1]);
            }else{
                max = Math.max(max, dp[1][i][0]);
                min = Math.min(min, dp[1][i][1]);
            }
        }
        System.out.println(max + " " + min);
    }
}

