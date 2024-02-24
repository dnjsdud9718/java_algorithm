package SSAFY.B형.week3.최장공통부분수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    DP
    Longest Common Subsequence
    최장 공통 부분 수열
    subsequence는 연속된 값이 아니다.
    따라서, 현재의 문자를 비교하는 과정 이전의 최대 공통 부분 수열은 계속해서 유지된다.
    A: acaykp
    B: capcak
    dp(i, j) -> 수열 A[1,...,i]와 B[1,...,j] 최장 공통 부분 수열(LCS)의 길이

    현재의 문자를 비교하는 과정 바로 이전의 과정이 lcs[i-1][j]와 lcs[i][j-1]이다.
    if(i==0 || j == 0)
        lcs[i][j] = 0;
     else if(A[i] == B[j]) // 매칭 -> 이전까지 구한 lcs길이 + 1 추가
        lcs[i][j] = lcs[i-1][j-1]+1;
     else // 매칭 안됨 -> 두 개가 동시에 매칭되는 경우가 존재할 수 없다.
        // 따라서 A[i]가 매칭되지 않거나, B[i]가 매칭되지 않거나 중에 큰값.
        lcs[i][j] = max(lcs[i-1][j], lcs[i][j-1])

    acaykp capcak
            c   a   p   c   a   k
        0   0   0   0   0   0   0
    a   0   0   1   1   1   1   1
    c   0   1   1   1   2   2   2
    a   0   1   2   2   2   3   3
    y   0   1   2   2   2   3   3
    k   0   1   2   2   2   3   4
    p   0   1   2   3   3   3   4

 */
public class Solution {
    static String s1, s2;
    static int len1, len2;
    static int[][] dp;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            s1 = st.nextToken();
            s2 = st.nextToken();
            len1 = s1.length();
            len2 = s2.length();
            dp = new int[len1 + 1][len2 + 1];
            for (int i = 1; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                    }
                }
            }
            for (int i = 0; i <= len1; i++) {
                System.out.println(Arrays.toString(dp[i]));
            }
            sb.append('#').append(t).append(' ').append(dp[len1][len2]).append('\n');
        }
        System.out.println(sb);
        br.close();
    }
}
