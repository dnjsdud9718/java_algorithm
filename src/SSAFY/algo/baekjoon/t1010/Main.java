package SSAFY.algo.baekjoon.t1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
    시간 초과
    -> 해결 1(Main2) -> 파스칼 삼각형 -> 메모이제이션
    -> 해결 2(Main3) -> nCr = n-1Cr-1 + n-1Cr
 */
public class Main {
    static int N, K, COUNT;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            COUNT = 0;
            if (N < K) {
                int tmp = N;
                N = K;
                K = tmp;
            }
            comb(0, 0);
            sb.append(COUNT).append('\n');
        }
        System.out.println(sb);
        br.close();
    }

    public static void comb(int idx, int cnt) {
//        System.out.println(idx+" "+cnt);
        if (cnt == K) {
            COUNT++;
            return;
        }
        // dp
        for (int i = idx; i < N; i++) {
            comb(i+1, cnt + 1);
        }
    }
}
