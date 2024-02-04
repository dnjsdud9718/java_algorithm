package SSAFY.study.t1003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 피보나치 함수
public class Main {
    static int T;
    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // DP
        int[] fib = new int[41];
        int[][] cnt = new int[41][2];
        fib[0] = 0;
        cnt[0][0] = 1;
        fib[1] = 1;
        cnt[1][1] = 1;
        for(int i=2; i<fib.length; i++) {
            fib[i] = fib[i-1] + fib[i-2];
            cnt[i][0] = cnt[i-1][0] + cnt[i-2][0];
            cnt[i][1] = cnt[i-1][1] + cnt[i-2][1];
        }
        T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            N = Integer.parseInt(br.readLine());
            sb.append(cnt[N][0]).append(" ").append(cnt[N][1]).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
}
