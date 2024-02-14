package SSAFY.study.t1932;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    baekjoon 1932 정수 삼각형
    핵심 : 자료를 어떻게 저장하고 찾을 것인가
    완탐 + 백트레킹을 생각해 봤다. -> 완탐 + 백트래킹으로 문제를 풀 수 없네 -> depth가 500이다.
                                                i
             7                   0              1 - 0+1 0+1+1
           3   8               1   2            2 - 1+2 1+2+1, 2+2, 2+2+1
          8  1  0           3   4   5           3
         2  7  4  4       6   7   8   9         4
        4  5  2  6  5   10  11  12  13  14      5

    depth도 같이 저장해둔다??
    bottom - up으로 올라가면서 해를 구한다. depth*(depth+1)/2만큼 연산
 */
public class Main {
    static int depth;
    static int[] src;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        depth = Integer.parseInt(br.readLine());
        int size = depth*(depth+1)/2;
        src = new int[size];
        int idx = 0;
        for (int i = 0; i < depth; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                src[idx++] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = depth; i >= 1; i--) {
            int k = i*(i+1)/2;
            for (int j = k-1; j > k - i; j--) {
                src[j - i] = src[j - i] + Math.max(src[j], src[j - 1]);
            }
        }
        System.out.println(src[0]);

        br.close();
    }
}
