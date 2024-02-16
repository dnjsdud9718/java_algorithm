package SSAFY.algo.요리사4012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Solution {
    static int N, ans;
    static int[][] src;
    static int[][] foods;
    static int[] tgt;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t ++) {
            N = Integer.parseInt(br.readLine());
            src = new int[N][];
            for (int i = 0; i < N; i++) {
                src[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            foods = new int[N][N];

            for (int i = 0; i < N ; i++) {
                for (int j = i + 1; j < N; j++) {
                    foods[i][j] = src[i][j] + src[j][i];
//                    foods[j][i] = foods[i][j];
                }
            }
//            for (int i = 0; i < N; i++) System.out.println(Arrays.toString(foods[i]));
            tgt = new int[N];
            ans = Integer.MAX_VALUE;
            comb(0, 0);
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }

        System.out.println(sb.toString());
        br.close();
    }

    public static void comb(int srcIdx, int cnt) {
        if (cnt == N / 2) {
//            System.out.println(Arrays.toString(tgt));
            int sumA = 0;
            int sumB = 0;
            for (int i = 0; i < N-1; i++) {
                for (int j = i+1; j < N; j++) {
                    if (tgt[i] != tgt[j]) continue;
                    if(tgt[i] == 0) sumB += foods[i][j];
                    else sumA += foods[i][j];
                }
            }
            ans = Math.min(ans, Math.abs(sumA - sumB));

            return;
        }

        for (int i = srcIdx; i < N; i++) {
            tgt[i] = 1;
            comb(i + 1, cnt + 1);
            tgt[i] = 0;
        }
    }
}
