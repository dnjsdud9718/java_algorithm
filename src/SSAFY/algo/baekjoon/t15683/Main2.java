package SSAFY.algo.baekjoon.t15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
    static int N, M, min;
    static int[][] map;
    static CCTV[] cctvs;
    static int cLen;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cctvs = new CCTV[8];
        cLen = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int v = Integer.parseInt(st.nextToken());
                if (v == 6) map[i][j] = -1;
                else map[i][j] = 0;
                if (v != 0 && v != 6) {
                    cctvs[cLen++] = new CCTV(i, j, v);
                }
            }
        }
        min = Integer.MAX_VALUE;
        backtracking(0);
        System.out.println(min);
        br.close();
    }

    static void backtracking(int cnt) {
        if (cnt == cLen) {
            int result = countZero();
            min = Math.min(min, result);
            return;
        }

        for (int d = 0; d < 4; d++) {
            // 칠하고
            monitor(cctvs[cnt], d, 1);
            // 가고
            backtracking(cnt + 1);
            // 복원하고
            monitor(cctvs[cnt], d, -1);
        }
    }

    static int countZero() {
        int cnt=0;
        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(map[i]));
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 0)cnt++;
            }
        }
//        System.out.println("=================");
        return cnt;
    }
    static void monitor(CCTV cctv, int dir, int v) {
        switch (cctv.n) {
            case 1 -> {
                draw(cctv.r, cctv.c, dir, v);
            }
            case 2 -> {
                draw(cctv.r, cctv.c, dir, v);
                draw(cctv.r, cctv.c, (dir+2)%4, v);
            }
            case 3 -> {
                draw(cctv.r, cctv.c, dir, v);
                draw(cctv.r, cctv.c, (dir+1)%4, v);
            }
            case 4 -> {
                draw(cctv.r, cctv.c, dir, v);
                draw(cctv.r, cctv.c, (dir+1)%4, v);
                draw(cctv.r, cctv.c, (dir+2)%4, v);
            }
            case 5 -> {
                draw(cctv.r, cctv.c, dir, v);
                draw(cctv.r, cctv.c, (dir+1)%4, v);
                draw(cctv.r, cctv.c, (dir+2)%4, v);
                draw(cctv.r, cctv.c, (dir+3)%4, v);
            }
        }
    }

    static void draw(int r, int c, int dir, int v) {
        while (true) {
            if(r < 0 || r == N || c < 0 || c == M || map[r][c] == -1) return;
            map[r][c] += v;
            r += dr[dir];
            c += dc[dir];
        }
    }

    static class CCTV {
        int r, c, n;
        public CCTV(int r, int c, int n) {
            this.r = r;
            this.c = c;
            this.n = n;
        }
    }
}
