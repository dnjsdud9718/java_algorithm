package SSAFY.algo.baekjoon.t1992;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;
/*
    백준 1992 쿼드 트리 -> 전형적인 재귀 문제
    8
    11110000
    11110000
    00011100
    00011100
    11110000
    11110000
    11110011
    11110011
 */
public class Main {
    static  int N;
    static int[][] src;
    static String answer;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        src = new int[N][N];
        for (int i = 0; i < N; i++) {
            src[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
//            System.out.println(Arrays.toString(src[i]));
        }
        logic(0, 0, N);
        System.out.println(sb.toString());
        br.close();
    }

    static void logic(int y, int x, int n) {
        if(n<1) return;
//        System.out.println(y + " " + x + " " + (y+n) + " " + (x+n)+" : " + n);
        int v = src[y][x];
        for (int i = y; i < y + n; i++) {
            for (int j = x; j < x + n; j++) {
                if (v != src[i][j]) {
                    v = -1;
                    break;
                }
            }
            if( v < 0) break;
        }
        if(v >= 0) {
            sb.append(v);
            return;
        }
        sb.append("(");
        int m = n/2;
        logic(y, x, m);
        logic(y, x + m, m);
        logic(y + m, x, m);
        logic(y + m, x + m, m);
        sb.append(")");
    }
}