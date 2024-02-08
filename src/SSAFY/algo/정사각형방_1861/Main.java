package SSAFY.algo.정사각형방_1861;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Stream;

public class Main {
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N, CNT, NO;
    static int[][] src;
    static StringBuilder sb = new StringBuilder();
//    이동하려는 방에 적힌 숫자가 현재 방에 적힌 숫자보다 정확히 1 더 커야 한다.
//    숫자는 모든 방에 대해 서로 다르다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            src = new int[N][N];
            for (int i = 0; i < N; i++) {
                src[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            CNT = 0;
            NO = 0;
            for (int i = 0; i < N; i++) {
                System.out.print(i+" : ");
                for (int j = 0; j < N; j++) {
                    bfs(i, j);
                }
            }

            sb.append("#").append(t).append(" ").append(NO).append(" ").append(CNT).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
    public static void bfs(int row, int col){
        Deque<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(row, col, 1));
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (current.cnt > CNT) {
                CNT = current.cnt;
                NO = src[current.row][current.col]-CNT+1;
            } else if (current.cnt == CNT) {
                NO = Math.min(NO, src[current.row][current.col] - CNT + 1);
            }
            for(int i=0; i<4; i++){
                int nr = current.row + dr[i];
                int nc = current.col + dc[i];
                if(nr < 0 || nr == N || nc < 0 || nc == N) continue;
                if(src[nr][nc]-src[current.row][current.col] != 1) continue;
                queue.offer(new Point(nr, nc, current.cnt+1));
                break;
            }
        }
    }
}
class Point{
    int row;
    int col;
    int cnt;
    public Point(int row, int col, int cnt) {
        this.row = row;
        this.col = col;
        this.cnt = cnt;
    }
}
