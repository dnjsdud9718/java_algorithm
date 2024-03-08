package SSAFY.algo.baekjoon.t2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
    static int N, M;
    static int left, right, answer;
    static int[] src;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        src = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            src[i] = Integer.parseInt(st.nextToken());
            src[i] += src[i - 1];
        }
        left = right = answer = 0;
        while (right <= N) {
            if (src[right] - src[left] >= M) {
                if (src[right] - src[left] == M) answer++;
                left++;
            } else {
                right++;
            }
        }
        System.out.println(answer);
        br.close();
    }
}
