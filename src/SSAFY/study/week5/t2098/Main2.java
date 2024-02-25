package SSAFY.study.week5.t2098;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

// Subject: Study of Algorithm with SSAFY
// ProblemNo: 2098
// Language: JAVA
// Author: pasta (JungJonghwa)
import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main2 {

    static final int MX = 16_000_002;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, v[][], dp[][], res;

    public static void main(String[] args) throws Exception{

        N = Integer.parseInt(br.readLine());
        v = new int[N][N];
        dp = new int[(1 << N)][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) v[i][j] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < (1 << N); i++) for(int j = 0; j < N; j++) dp[i][j] = MX;
        dp[1][0] = 0;

        for(int visit = 1; visit < (1 << N); visit++){
            for(int here = 0; here < N; here++){ // 0 ~ N-1번 도시
                if(dp[visit][here] == MX) continue;
                for(int next = 0; next < N; next++){
                    if(v[here][next] == 0 || ((visit & (1 << next)) != 0)) continue;
                    dp[visit | (1 << next)][next] = Math.min(dp[visit | (1 << next)][next], dp[visit][here] + v[here][next]);
                    print();
                }
            }
        }

        res = MX;
        for(int j = 1; j < N; j++) if(v[j][0] != 0) res = Math.min(res, dp[(1 << N) - 1][j] + v[j][0]);
        System.out.print(res);
    }
    static void print() {
        for (int i = 1; i < 1<<N; i++) {
            for (int j = 0; j < N; j++) {
                if(dp[i][j] == MX) System.out.print("* ");
                else System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("------------------------------------");
    }
}