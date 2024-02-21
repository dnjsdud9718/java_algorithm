package SSAFY.algo.baekjoon.t15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M; // 행, 열
    static int[][] map; // 원본
    static int[][] copy; // 카피본
    static int[] dr = {0, 1, 0, -1}; // 우, 상, 좌, 하
    static int[] dc = {1, 0, -1, 0};
    static CCTV[] cctvs = new CCTV[8]; // CCTV는 최대 8개
    static int ptIdx = 0; // points 배열의 size

    static int min = Integer.MAX_VALUE; // 정답을 담을 변수
    static int[] cases;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // CCTV가 위치한 좌표의 좌표와 종류를 cctvs 배열에 저장.
                if (1 <= map[i][j] && map[i][j] <= 5) {
                    cctvs[ptIdx++] = new CCTV(i, j, map[i][j]);
                }
            }
        }
        // cases는 CCTV가 바라보고 있을 방향에 대한 모든 경우를 확인하기 위해 필요.
        cases = new int[ptIdx]; // 중복 순열의 tgt배열
        copy = new int[N][M];
        perm(0);
        System.out.println(min);
        br.close();
    }
    public static void perm(int cnt) {
        if (cnt == ptIdx) {
//            System.out.println(Arrays.toString(cases));
            for (int j = 0; j < N; j++) {
                copy[j] = Arrays.copyOf(map[j], M);
//                System.out.println(Arrays.toString(copy[j]));
            }
            for (int i = 0; i < ptIdx; i++) {
                CCTV p = cctvs[i];
                switch(p.cctv) {
                    case 1:
                        monitor(cases[i], p.y, p.x );
                        break;
                    case 2:
                        monitor(cases[i], p.y, p.x );
                        monitor((cases[i]+2)%4, p.y, p.x );
                        break;
                    case 3:
                        monitor(cases[i], p.y, p.x );
                        monitor((cases[i]+1)%4, p.y, p.x );
                        break;
                    case 4:
                        monitor(cases[i], p.y, p.x );
                        monitor((cases[i]+1)%4, p.y, p.x );
                        monitor((cases[i]+2)%4, p.y, p.x );
                        break;
                    case 5:
                        monitor(cases[i], p.y, p.x );
                        monitor((cases[i]+1)%4, p.y, p.x );
                        monitor((cases[i]+2)%4, p.y, p.x );
                        monitor((cases[i]+3)%4, p.y, p.x );
                        break;
                }
            }
//            for (int i = 0; i < N; i++) System.out.println(Arrays.toString(copy[i]));
//            System.out.println("---------------");
            int zeros = countZero(copy);
            min = Math.min(min, zeros);
            return;
        }
        // 모든 경우의 수 4^K (K = # of CCTV)
        for (int i = 0; i < 4; i++) {
            cases[cnt] = i;
            perm(cnt + 1);
        }
    }

    public static void monitor(int dir, int r, int c) {
        int nr = r;
        int nc = c;
        while (canGo(nr, nc)) {
            if(copy[nr][nc] == 0) copy[nr][nc] = -1;
            nr = nr + dr[dir];
            nc = nc + dc[dir];
        }
    }
    public static boolean canGo(int r, int c) {
        if(r < 0 || r == N || c < 0 || c == M || copy[r][c] == 6) return false;
        return true;
    }

    public static int countZero(int[][] src) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(src[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }

    static class CCTV {
        int y, x, cctv;
        public CCTV(int y, int x, int cctv) {
            this.y = y;
            this.x = x;
            this.cctv = cctv;
        }
    }
}
