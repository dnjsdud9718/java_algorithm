package SSAFY.study.week5.t15654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    baekjoon 15654 N과 M(5)
    N개의 자연수와 자연수 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두
    구하는 프로그램 작성
     -> N개의 자연수 중에서 M개를 고른 수열

    수열은 사전 순으로 증가하는 순서로 출력해야 한다.

    풀이
    1. 기본 순열
    2. 비트 마스킹
    3. next permutation -> X -> nPn
 */
public class Main {
    static int N, M;
    static int[] src;
    static int[] tgt;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        src = new int[N];
        visited = new boolean[N];
        tgt = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            src[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(src);
        perm(0);
        System.out.println(sb);
        br.close();
    }
    public static void perm(int cnt) {
        if (cnt == M) {
            for (int i = 0; i < M; i++) {
                sb.append(tgt[i]).append(' ');
            }
            sb.append('\n');
            return;
        }
        for (int i = 0; i < N; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            tgt[cnt] = src[i];
            perm(cnt + 1);
            visited[i] = false;
        }
    }

}
