package SSAFY.algo.사람네트워크2_1263;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static final int INF = 987_654_321;
    static int T, N;
    static int[][] src;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            src = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int v =  Integer.parseInt(st.nextToken());
                    if(i == j) src[i][j] = 0;
                    else if(v == 0) src[i][j] = INF;
                    else src[i][j] = v;
                }
//                System.out.println(Arrays.toString(src[i]));
            }
            for (int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (src[i][j] > src[i][k] + src[k][j]) {
                            src[i][j] = src[i][k] + src[k][j];
                        }
                    }
                }
            }
            int answer = INF;
            for (int i = 0; i < N; i++) {
                int sum = 0;
                for (int j = 0; j < N; j++) {
                    if(src[i][j] >= INF) continue;
                    sum += src[i][j];
                }
                answer = Math.min(answer, sum);
            }
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
