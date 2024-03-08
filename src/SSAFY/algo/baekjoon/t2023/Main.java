package SSAFY.algo.baekjoon.t2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, left, right, answer, sum;
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
        }
        answer = 0;
        left = 0;
        right = 0;
        sum = 0;
        while (right <= N) {
            if (sum >= M) {
                if (sum == M) answer++;
                left++;
                if(left <= N) sum -= src[left];
            } else { // sum < M
                right++;
                if(right <= N) sum += src[right];
            }
        }
        System.out.println(answer);
        br.close();
    }
}
