package SSAFY.study.week9.t1799;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, answer;
    static int[][] map;
    static Point[] points;
    static int pLen;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        points = new Point[101];
        pLen = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int v = Integer.parseInt(st.nextToken());
                if(v == 0) map[i][j] = 1;
                else {
                    map[i][j] = 0;
                    points[pLen++] = new Point(i, j);
                }
            }
//            System.out.println(Arrays.toString(map[i]));
        }
        answer = 0;
        backtracking(0);
        System.out.println(answer);
        br.close();
    }

    static void backtracking(int cnt) {
        if (answer < cnt) {
            answer = cnt;
        }
        for (int i = 0; i < pLen; i++) {
            Point p = points[i];
            if(map[p.r][p.c] > 0) continue;
            // 그리고
            draw(p.r, p.c, 1);
            // 가고
            backtracking(cnt + 1);
            // 지우고
            draw(p.r, p.c, -1);
        }
    }

    static int dr[] = {-1, -1, 1, 1};
    static int dc[] = {-1, 1, -1, 1};
    static void draw(int r, int c, int v) {
        map[r][c] += v;

        // -1, -1
        for (int d = 0; d < 4; d++) {
            int nr = r;
            int nc = c;
            while (true) {
                nr = nr + dr[d];
                nc = nc + dc[d];
                if(nr < 0 || nc < 0 || nr == N || nc == N) break;
                map[nr][nc] += v;
            }
        }
    }
    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
