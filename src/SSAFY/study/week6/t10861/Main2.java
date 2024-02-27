package SSAFY.study.week6.t10861;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    숫자 카드 2 10816
    해결 방안 -> 음수가 양수가 되도록 이동시키기

 */
public class Main2 {
    static final int MOVE = 10_000_000;
    static int N, M;
    static int[] src = new int[MOVE * 2 + 1];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int j = Integer.parseInt(st.nextToken());
            src[j + MOVE]++;
        }
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int j = Integer.parseInt(st.nextToken());
            sb.append(src[j + MOVE]).append(' ');
        }
        System.out.println(sb);
        br.close();
    }
}
