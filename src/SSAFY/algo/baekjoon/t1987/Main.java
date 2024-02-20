package SSAFY.algo.baekjoon.t1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
/*
    백준 알파벳 1987
    세로 R칸, 가로 C칸으로 된 표 모양의 보드가 있다. 보드의 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 좌측
    상단 칸(1행 1열)에는 말이 놓여 있다.
    말은 상하좌우 인접한 네 칸 중에 한 칸으로 이동할 수 있는데, 새로 이동한 칸에 적혀 있는 알파벳은 지금까지
    지나온 모든 칸에 적혀 있는 알파벳과 달라야 한다. 즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.

    좌측 상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하는 프로그램 작성.
    말이 지나는 칸은 좌측 상단의 칸도 포함.

    3 6
    HFDFFB
    AJHGDH
    DGAGEH

    숫자로 변환.
    DFS 백트레킹.
    BFS -> 안 된다.
 */
public class Main {
    static int R, C, ans;
    static int[][] src;
    static int basic = 'A';
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        src = new int[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                src[i][j] = s.charAt(j) - basic;
            }
        }

        ans = 1;
        dfs(0, 0, (1<<src[0][0]), 1);
        System.out.println(ans);
        br.close();
    }

    public static void dfs(int y, int x, int check, int cnt) { // permutation code랑 유사하다.
        ans = Math.max(ans, cnt);
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(ny < 0 || ny == R || nx < 0 || nx == C) continue;
            if((check & (1<<src[ny][nx])) != 0) continue;
            dfs(ny, nx, check | (1<<src[ny][nx]), cnt + 1);
        }
    }

}

