package SSAFY.algo.프로세서연결하기_1767;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, minLen, coreCnt;
    static int[][] map;
    static Core[] cores;
    static int coreArrLen;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            cores = new Core[12];
            coreArrLen = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(i==0 || i == N-1 || j == 0 || j == N-1 || map[i][j] == 0 ) continue;
                    cores[coreArrLen++] = new Core(i, j);
                }
            }

            // 풀이
            minLen = 0;
            coreCnt = 0;
            dfs(0, 0, 0);

            sb.append("#").append(t).append(" ").append(minLen).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    public static void dfs(int idx, int length, int count) {

        // minLen, coreCnt 갱신
        if (coreCnt < count) {
            minLen = length;
            coreCnt = count;
        } else if (coreCnt == count) {
            minLen = Math.min(minLen, length);
        }

        if (idx == coreArrLen) return;

        for (int d = 0; d < 4; d++) {
            // 해당 방향으로 갈 수 있는가?
            if(!canGo(cores[idx], d)) continue;
            // 갈 수 있다면 go
            int wireLen = go(cores[idx], d);
            // 재귀 호출
            dfs(idx + 1, length + wireLen, count + 1);
            // 리셋
            unGo(cores[idx], d);
        }
        dfs(idx + 1, length, count);
    }

    static void unGo(Core core, int d) {
        int r = core.r + dr[d];
        int c = core.c + dc[d];
        while (true) {
            map[r][c] = 0;
            if(r == 0 || r == N-1 || c == 0 || c == N-1) break;
            r = r + dr[d];
            c = c + dc[d];
        }
    }
    static int go(Core core, int d) {
        int r = core.r + dr[d];
        int c = core.c + dc[d];
        int cnt = 0;
        while (true) {
            cnt++;
            map[r][c] = 1;
            if(r == 0 || r == N-1 || c == 0 || c == N-1) break;
            r = r + dr[d];
            c = c + dc[d];
        }
        return cnt;
    }

    static boolean canGo(Core core, int d) {
        int r = core.r + dr[d];
        int c = core.c + dc[d];
        while (true) {
            if((r==0 || r == N-1 || c == 0 || c == N-1) && map[r][c] == 0) return true;
            if(map[r][c] == 1) return  false;
            r = r + dr[d];
            c = c + dc[d];
        }
    }
    static class Core{
        int r, c;
        public Core(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
