package SSAFY.study.week10.t1654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] src;
    static int K, N, max, answer;
    static long left, right;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        src = new int[K];
        max = Integer.MIN_VALUE;
        for (int i = 0; i < K; i++) {
            src[i] = Integer.parseInt(br.readLine());
            if (max < src[i]) {
                max = src[i];
            }
        }
        left = 1; right = max;
        answer = 0;
        while (left <= right) {
            long mid = (left+right)/2;
            int cnt = calc(mid);
            if (cnt >= N) {
                answer = (int)mid;
                left = mid + 1;
            } else if (cnt < N) {
                right = mid - 1;
            }
        }
        System.out.println(answer);
        br.close();
    }
    static int calc(long x) {
        int cnt = 0;
        for(int i=0; i<K; i++) cnt += src[i]/x;
        return cnt;
    }

}
