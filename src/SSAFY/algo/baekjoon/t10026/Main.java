package SSAFY.algo.baekjoon.t10026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/*
적록색약은 빨간색과 초록색의 차이를 거의 느끼지 못한다. 따라서, 적록색약인 사람이 보는 그림은 아닌 사람이 보는 그림과는 좀 다를 수 있다.
크기가 N×N인 그리드의 각 칸에 R(빨강), G(초록), B(파랑) 중 하나를 색칠한 그림이 있다. 그림은 몇 개의 구역으로 나뉘어져 있는데, 구역은 같은 색으로 이루어져 있다.
또, 같은 색상이 상하좌우로 인접해 있는 경우에 두 글자는 같은 구역에 속한다. (색상의 차이를 거의 느끼지 못하는 경우도 같은 색상이라 한다)

RRRBB
GGBBB
BBBRR
BBRRR
RRRRR

적록색약이 아닌 사람이 봤을 때 구역의 수는 총 4개이다. (빨강 2, 파랑 1, 초록 1) 하지만, 적록색약인 사람은 구역을 3개 볼 수 있다. (빨강-초록 2, 파랑 1)
그림이 입력으로 주어졌을 때, 적록색약인 사람이 봤을 때와 아닌 사람이 봤을 때 구역의 수를 구하는 프로그램을 작성하시오.

풀이
적록색약이 아닌 사람이 보는 그림(src1)과 적록색약인 사람이 보는 사람의 그림(src2)을 따로 만든다.
BFS -> Flood Fill 수행
 */
public class Main {
    static int N;
    static char[][] src1;
    static char[][] src2;
    static boolean[][] visited;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        src1 = new char[N][N];
        src2 = new char[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                src1[i][j] = line.charAt(j);
                if(src1[i][j] == 'R' || src1[i][j] == 'G') src2[i][j] = 'R';
                else src2[i][j] = 'B';
            }
        }
        ans = 0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(visited[i][j])continue;
//                bfs(src1, i, j, src1[i][j]);
                dfs(src1, i, j, src1[i][j]);
                ans++;
            }
        }
        System.out.print(ans+" ");
        ans = 0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(visited[i][j])continue;
//                bfs(src2, i, j, src2[i][j]);
                dfs(src2, i, j, src2[i][j]);
                ans++;
            }
        }
        System.out.println(ans);
        br.close();
    }

    public static void dfs(char[][] src, int r, int c, char v) {
        visited[r][c] = true;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr <0 || nr == N || nc < 0 || nc == N || visited[nr][nc] || src[nr][nc] != v) continue;
            dfs(src, nr, nc, v);
        }
    }
    public static void bfs(char[][] src, int r, int c, char v) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{r, c});
        visited[r][c] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(nr <0 || nr == N || nc < 0 || nc == N || visited[nr][nc] || src[nr][nc] != v) continue;
                visited[nr][nc] = true;
                queue.add(new int[]{nr, nc});
            }
        }
    }
}
