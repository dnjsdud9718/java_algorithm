package SSAFY.study.week4.t7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
    7576 토마토
    철수의 토마토 농장에서는 토마토를 보관하는 큰 창고를 가지고 있다. NxM
    창고에 보관되는 토마토들 중에는 잘 익은 것도 있지만, 아직 익지 않는 토마토들도 있을 수 있다. 보관 후 하루가 지나면,
    익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게 된다. 하나의 토마토의 인접한 곳은
    상하좌우 네방향을 의미한다. 토마토가 혼자 저절로 익는 경우는 없다고 가정한다. 철수는 창고에 보관된 토마토들이 며칠이
    지나면 다 익게 되는지, 그 최소 일수를 알고 싶어 한다.
    0 : 안 익은 토마토
    1 : 익은 토마토
    -1 : 빈 칸(토마토가 없다)
 */
public class Main {
    static int[] dr = new int[]{1, 0, -1, 0};
    static int[] dc = new int[]{0, 1, 0, -1};
    static int[][] box;
    static int N, M;
    static StringTokenizer st;
    static Deque<Point> deque;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        box = new int[N][M];
        deque = new ArrayDeque<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if(box[i][j] == 1) deque.add(new Point(i, j));
            }
        }
        while (!deque.isEmpty()) {
            Point current = deque.poll();
            for(int i=0; i<4; i++){
                int nr = current.row + dr[i];
                int nc = current.col + dc[i];
                if(nr < 0 || nr == N || nc < 0 || nc == M || box[nr][nc] != 0) continue;
                box[nr][nc] = box[current.row][current.col]+1;
                deque.add(new Point(nr, nc));
            }
        }
        int max = 0;
        for (int i = 0; i < N; i++) {
            for(int j=0; j<M; j++){
                if (box[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
                max = Math.max(max, box[i][j]);
            }
        }
        System.out.println(max-1);
        br.close();
    }
}
class Point{
    int row;
    int col;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
