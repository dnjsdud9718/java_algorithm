package SSAFY.algo.baekjoon.t17136;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987654321;
    static final int N = 10;
    static int[][] map = new int[N][N];
    static boolean[][] visited = new boolean[N][N];
    static int vLen = 0;
    static Point[] points = new Point[N * N];
    static int[] count = new int[5];
    static int pLen = 0;
    static int min = INF;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    points[pLen++] = new Point(i, j, false);
                }
            }
        }
        if (pLen == 0) {
            System.out.println(0);
            return;
        }
        dfs(0);
        System.out.println(min == INF ? -1 : min);
        br.close();
    }

    static void dfs(int cnt) { // cnt : 색종이 붙인 횟수
//        System.out.println(cnt);
        if(min < cnt) return; // 가지치기
        if (vLen == pLen) { // 색종이를 모두 붙였다.
            min = Math.min(min, cnt);
            return;
        }

        for (int i = 0; i < pLen; i++) {
            Point p = points[i];
            if(visited[p.r][p.c])continue;
            for (int k = 0; k < 5; k++) {
                if(count[k] == 5) continue;
                if(!canDraw(p.r, p.c, k+1)) break; // 모든 색종이를 붙일 수 없다고 판단되면 더 갈 필요 없다.
                count[k]++;
                int sum = fill(p.r, p.c, k + 1, true);
                vLen += sum;
                dfs(cnt + 1); // 재귀로 쭉쭉 가서 모든 색종이를 붙일 수도 있고, 아닐 수도 있다.
                vLen -= sum;
                fill(p.r, p.c, k + 1, false);
                count[k]--;
            }
            // p는 항상 붙이는 색종이의 좌상단 꼭짓점. p꼭짓점에서 붙일 수 있는 모든 경우를 봤다. 다시 볼 필요 없다.
            return;
        }

    }

    static int fill(int r, int c, int len, boolean b) {
        int cnt = 0;
        for (int i = r; i < r + len; i++) {
            for (int j = c; j < c + len; j++) {
                visited[i][j] = b;
                cnt++;
            }
        }
        return cnt;
    }
    static boolean canDraw(int r, int c, int len) {
        for (int i = r; i < r + len; i++) {
            if(i == N) return false;
            for (int j = c; j < c + len; j++) {
                if(j == N || map[i][j] == 0 || visited[i][j]) return false;
            }
        }
        return true;
    }
    static class Point {
        int r, c;
        boolean visit;

        public Point(int r, int c, boolean visit) {
            this.r = r;
            this.c = c;
            this.visit = visit;
        }
    }
}
