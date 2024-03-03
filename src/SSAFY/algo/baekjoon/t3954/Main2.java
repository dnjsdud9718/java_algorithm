package SSAFY.algo.baekjoon.t3954;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
    static final int MAX_K = 6;
    static int N,M, K;
    static int[][] map;
    static Item[] items;
    static int iLen;
    static boolean[] visited;
    static int min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        items = new Item[MAX_K];
        iLen = 0;
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
            items[iLen++] = new Item(r, c, s);
        }
        visited = new boolean[K];
        min = Integer.MAX_VALUE;
        backtracking(0);

        System.out.println(min);
        br.close();
    }

    static void backtracking(int cnt) {
        // 기저 조건 : 모든 회전이 수행
        if (cnt == K) {
            // 최솟값 갱신 후 진행
//            System.out.println("HI");
            calc();
            return;
        }
        for (int i = 0; i < K; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            // 회전
            Item item = items[i];
            rotate(item.r-item.s, item.c-item.s, item.r+item.s, item.c+item.s);
            backtracking(cnt + 1);
            // 회전 원복
            rollback(item.r-item.s, item.c-item.s, item.r+item.s, item.c+item.s);
            visited[i] = false;
        }
    }

    static void calc() {
        int mapVal = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int rowSum = 0;
            for (int j = 1; j <= M; j++) {
                rowSum += map[i][j];
            }
            mapVal = Math.min(mapVal, rowSum);
        }
        min = Math.min(min, mapVal);
    }
    static void rollback(int sr, int sc, int er, int ec) {
        if(sr == er | sc == ec) return;
        int tmp = map[sr][sc];
        // right
        for (int i = sc + 1; i <= ec; i++) { // left
            map[sr][i - 1] = map[sr][i];
        }
        for (int i = sr + 1; i <= er; i++) { // up
            map[i - 1][ec] = map[i][ec];
        }
        for (int i = ec - 1; i >= sc; i--) {
            map[er][i + 1] = map[er][i];
        }
        for (int i = er - 1; i >= sr; i--) {
            map[i + 1][sc] = map[i][sc];
        }
        map[sr + 1][sc] = tmp;
        rollback(sr + 1, sc + 1, er - 1, ec - 1);
    }
    static void rotate(int sr, int sc, int er, int ec) {
        if(sr == er || sc == ec) return;
        int tmp = map[sr][sc];
        for (int i = sr + 1; i <= er; i++) { // up
            map[i - 1][sc] = map[i][sc];
        }
        for (int i = sc + 1; i <= ec; i++) { // right
            map[er][i - 1] = map[er][i];
        }
        for (int i = er - 1; i >= sr; i--) { // down
            map[i + 1][ec] = map[i][ec];
        }
        for (int i = ec - 1; i >= sc; i--) { // left
            map[sr][i + 1] = map[sr][i];
        }
        map[sr][sc + 1] = tmp;
        rotate(sr + 1, sc + 1, er - 1, ec - 1);
    }
    static class Item {
        int r, c, s;
        public Item(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
}

