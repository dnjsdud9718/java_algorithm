package SSAFY.algo.정사각형방_1861;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    static int N;
    static int[][] src;
    static boolean[][] visited;
    static int[][] count;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static Deque<Point> queue;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++){
            N = Integer.parseInt(br.readLine());
            src = new int[N+1][N+1];
            count = new int[N + 1][N + 1];
            visited = new boolean[N + 1][N + 1];
            for(int i=1; i<=N; i++){
                st = new StringTokenizer(br.readLine());
                int j = 1;
                while(st.hasMoreTokens()) src[i][j++] = Integer.parseInt(st.nextToken());
            }
            queue = new ArrayDeque<>();
            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++){
                    if(!visited[i][j]){
                        visited[i][j] = true;
                        queue.add(new Point(i, j, 1));
                        while (!queue.isEmpty()) {
                            Point current = queue.poll();
                            count[current.row][current.col] = current.cnt;
                            for(int k=0; k<4; k++){
                                int nr = current.row + dr[k];
                                int nc = current.col + dc[k];
                                if(nr == 0 || nr > N || nc == 0 || nc > N) continue;
                                if (visited[nr][nc] || Math.abs(src[nr][nc] - src[current.row][current.col]) > 1) {
                                    continue;
                                }
                                visited[nr][nc] = true;
                                queue.add(new Point(nr, nc, current.cnt+1));
                                break;
                            }
                        }
                    }
                }
            }
            int answer = Integer.MIN_VALUE;
            int answer1 = 0;
            int cr = 0, cc = 0;
            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++){
                    if(answer < count[i][j]) {
                        answer = count[i][j];
                        answer1 = src[i][j];
                    }else if(answer == count[i][j]){

                    }
                }
            }
            sb.append("#").append(t).append(" ").append(src[cr][cc]-answer+1).append(" ").append(answer).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
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
