package SSAFY.study.week4.t1208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, S, answer;
    static int[] src;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        src = new int[N];
        st = new StringTokenizer(br.readLine());
        int idx = 0;
        while (st.hasMoreTokens()) {
            src[idx] = Integer.parseInt(st.nextToken());
            idx++;
        }
        Arrays.sort(src);
        answer = 0;
        subset(0, 0);
        System.out.println(answer == 0 ? 0 : answer-1   );
        br.close();
    }
    public static void subset(int idx, int sum) {
        if (idx == N) {
            if (sum == S) {
                answer++;
            }
            return;
        }
        subset(idx + 1, sum);
        if(S > 0 && sum+src[idx] > S) return;
        if(S < 0 && sum+src[idx ]< S) return;
        subset(idx + 1, sum + src[idx]);
    }
}
