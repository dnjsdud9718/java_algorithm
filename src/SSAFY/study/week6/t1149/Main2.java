package SSAFY.study.week6.t1149;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
    static final int INF = 987_654_321;
    static int N, answer;
    static int[][] src;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        src = new int[N+1][3];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                src[i][j] = Integer.parseInt(st.nextToken());
                src[i][j] += Math.min(src[i - 1][(j + 1) % 3], src[i - 1][(j + 2) % 3]);
            }
        }
        answer = INF;
        for (int i = 0; i < 3; i++) {
            answer = Math.min(answer, src[N][i]);
        }
        System.out.println(answer);
        br.close();
    }
}
