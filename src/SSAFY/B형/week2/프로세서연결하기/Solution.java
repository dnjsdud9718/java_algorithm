package SSAFY.B형.week2.프로세서연결하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
7
0 0 1 0 0 0 0
0 0 1 0 0 0 0
0 0 0 0 0 1 0
0 0 0 0 0 0 0
1 1 0 1 0 0 0
0 1 0 0 0 0 0
0 0 0 0 0 0 0
 */
public class Solution {
    static int N;
    static int[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static Core[] cores;
    static int cIdx;
    static int ans, maxCnt;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            cores = new Core[12];
            cIdx = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (i == 0 || j == 0 || i == N - 1 || j == N - 1 || map[i][j] == 0) continue;
                    cores[cIdx++] = new Core(i, j);
                }
            }
            ans = 0;
            maxCnt = 0;
            backtracking(0, 0, 0);
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    public static void backtracking(int idx, int length, int cnt) {
        if (cnt > maxCnt) {
            maxCnt = cnt;
            ans = length;
        } else if (cnt == maxCnt) {
            ans = Math.min(ans, length);
        }
        if(idx == cIdx) return;
        for (int d = 0; d < 4; d++) {
            int nr = cores[idx].r + dr[d];
            int nc = cores[idx].c + dc[d];
            int wireLen = go(cores[idx], d);
            if(wireLen == -1) continue; // 여기서 backtracking(idx + 1, length, cnt); 중복이 많이 된다.
            else {
                backtracking(idx + 1, length + wireLen, cnt + 1);
                rollback(cores[idx], d);
            }
        }
        // 현재 코어 전원을 켜지 않는 경우.
        backtracking(idx + 1, length, cnt);
    }

    static int go(Core core, int dir) {
        int r = core.r + dr[dir];
        int c = core.c + dc[dir];
        int res = 0;
        // 전원을 연결할 수 있는지 확인
        while (true) {
            if (r < 0 || r == N || c < 0 || c == N) break; // 전원 연결 가능
            if(map[r][c] != 0) return -1; // 전원 연결 불가
            r = r + dr[dir];
            c = c + dc[dir];
        }
        // 전원 연결
        r = core.r + dr[dir];
        c = core.c + dc[dir];
        while (true) {
            if (r < 0 || r == N || c < 0 || c == N) break; // 전원 연결 완료
            map[r][c] = 2;
            res++;
            r = r + dr[dir];
            c = c + dc[dir];
        }
        return res;
    }

    static void rollback(Core core, int d) {
        int r = core.r + dr[d];
        int c = core.c + dc[d];
        while (true) {
            if (r < 0 || r == N || c < 0 || c == N) return;
            map[r][c] = 0;
            r = r + dr[d];
            c = c + dc[d];
        }
    }
    static class Core {
        int r;
        int c;
        public Core(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
