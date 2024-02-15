package SSAFY.study.week4.t1932;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    * *  *  *  *  *
    * 0  *  *  *  *
    * 1  2  *  *  *
    * 3  4  5  *  *
    * 6  7  8  9  *
    * 10 11 12 13 14
 */
public class Main2 {
    static int depth;
    static int[][] src;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        depth = Integer.parseInt(br.readLine());
        src = new int[depth + 1][depth + 1];
        StringTokenizer st;
        for (int i = 1; i <= depth; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; j++) {
                src[i][j] = Integer.parseInt(st.nextToken());
                src[i][j] = src[i][j] + Math.max(src[i - 1][j], src[i - 1][j - 1]);
                if (i == depth) {
                    max = Math.max(max, src[i][j]);
                }
            }
        }
        System.out.println(max);
        br.close();
    }
}
