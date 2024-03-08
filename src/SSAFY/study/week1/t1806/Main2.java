package SSAFY.study.week1.t1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
    static final int INF = 987_654_321;
    static int N, S, left, right,answer, sum;
    static int[] src;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        src = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            src[i] = Integer.parseInt(st.nextToken());
        }
        left = 0;
        right = 0;
        sum = 0;
        answer = INF;
        while (right <= N) {
            if (sum >= S) {
                answer = Math.min(answer, right - left);
                left++;
                if(left <= N) sum -= src[left];
            } else { // sum < S
                right++;
                if(right <= N) sum += src[right];
            }
        }
        System.out.println(answer == INF ? 0 : answer);
        br.close();
    }
}
