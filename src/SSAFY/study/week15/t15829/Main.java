package SSAFY.study.week15.t15829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final long M = 1234567891;
    static final long R = 31;
    static int L;
    static long sum;
    static char[] src;
    static long[] dp = new long[50];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp[0] = 1;
        dp[1] = R;
        for (int i = 2; i < 50; i++) {
            dp[i] = dp[i - 1] * R % M;
        }
        // System.out.println(Arrays.toString(dp));
        L = Integer.parseInt(br.readLine());
        src = br.readLine().toCharArray();
        sum = 0;
        for (int i = 0; i < L; i++) {
            sum += (src[i] - 'a' + 1) * dp[i] % M;
        }
        System.out.println(sum % M);
        br.close();
    }

    public static long sum(int pow) {
        long ret = R;
        if (pow == 0) {
            return 1;
        }
        if (pow == 1) {
            return R;
        }
        return ret * sum(pow - 1);
    }
}
