package SSAFY.algo.요리사4012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2 {
    static int T, N, min;
    static int[][] S; // 시너지
    static int[] tgt;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            S = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    S[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // combination NC(N/2)
            tgt = new int[N / 2];
            min = Integer.MAX_VALUE;
            comb(0, 0);
            sb.append("#").append(t).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    static void comb(int srcIdx, int tgtIdx) {
        if (tgtIdx == N / 2) {
            boolean[] b = new boolean[N];
            for(int i=0; i<N/2; i++) b[tgt[i]] = true;
            int sumA, sumB;
            sumA = sumB = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(i ==j ) continue;
                    if(b[i] && b[j]) sumA += S[i][j];
                    else if(!b[i] && !b[j]) sumB += S[i][j];
                }
            }
            min = Math.min(min, Math.abs(sumA - sumB));
            return;
        }
        for (int i = srcIdx; i < N; i++) {
            tgt[tgtIdx] = i;
            comb(i + 1, tgtIdx + 1);
        }
    }
}
