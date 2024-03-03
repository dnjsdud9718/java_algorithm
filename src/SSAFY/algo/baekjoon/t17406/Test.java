package SSAFY.algo.baekjoon.t17406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test {
    static int N, M, S;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        print();
        rotate(3, 4, 2);
        print();
        rotate(4, 2, 1);
        print();
        br.close();
    }

    static void rotate(int r, int c, int s) {
        int sr, sc, er, ec;
        sr = r - s;
        sc = c - s;
        er = r + s;
        ec = c + s;
        rotate(sr, sc, er, ec);
    }

    static void rotate(int sr, int sc, int er, int ec) {
        if(sr == er || sc == ec) return;
        int tmp = map[sr][sc];
        for (int i = sr + 1; i <= er; i++) {
            map[i - 1][sc] = map[i][sc];
        }
        for (int i = sc+1; i <= ec; i++) {
            map[er][i - 1] = map[er][i];
        }
        for (int i = er - 1; i >= sr; i--) {
            map[i + 1][ec] = map[i][ec];
        }
        for (int i = ec - 1; i >= sc; i--) {
            map[sr][i + 1] = map[sr][i];
        }
        map[sr][sc + 1] = tmp;
        rotate(sr + 1, sc + 1, er - 1, ec - 1);
    }
    static void print() {
        for (int i = 1; i <= N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println("=-========");
    }
}
