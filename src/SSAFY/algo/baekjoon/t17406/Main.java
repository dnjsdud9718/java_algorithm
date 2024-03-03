package SSAFY.algo.baekjoon.t17406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K, min;
    static int[][] map;
    static RCS[] rcs;
    static int[] tgt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        rcs = new RCS[K];
        tgt = new int[K];
        min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            rcs[i] = new RCS(r, c, s);
        }
        perm(0, 0);
        System.out.println(min);

        br.close();
    }

    static void simul() {
        int[][] copy = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            copy[i] = Arrays.copyOf(map[i], M+1);
        }
        for (int i = 0; i < K; i++) {
            RCS now = rcs[tgt[i]];
//            System.out.println(now.r + " " + now.c + " " + now.s);
            rotate(now.r - now.s, now.c - now.s, now.r + now.s, now.c + now.s);
        }
        calc();
        for (int i = 1; i <= N; i++) {
            map[i] = Arrays.copyOf(copy[i], M + 1);
        }
    }

    static void calc() {
        int sum;
        int cmp = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            sum = 0;
            for (int j = 1; j <= M; j++) {
                sum += map[i][j];
            }
            cmp = Math.min(cmp, sum);
        }
        min = Math.min(min, cmp);
    }
    static void rotate(int sr, int sc, int er, int ec) {
        if(sr==er || sc == ec) return;
        int tmp = map[sr][sc];
        for (int i = sr + 1; i <= er; i++) {
            map[i - 1][sc] = map[i][sc];
        }
        for (int i = sc + 1; i <= ec; i++) {
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
    static void perm(int idx, int check) {
        if (idx == K) {
//            System.out.println(Arrays.toString(tgt));
            simul();
            return;
        }
        for (int i = 0; i < K; i++) {
            if((check & (1<<i))!=0) continue;
            tgt[idx] = i;
            perm(idx+1, check | (1 << i));
        }
    }
    static class RCS {
        int r, c, s;

        public RCS(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
}
