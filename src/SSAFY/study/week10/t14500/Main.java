package SSAFY.study.week10.t14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer;
    static int[][] map;
    static int[] dr = {0, 1, 0, -1}; // 우, 하, 좌, 상
    static int[] dc = {1, 0, -1, 0};


    static int[][][] dir = {
            {{0, 0}, {1, 0}, {2, 0}, {2, 1}},
            {{0, 0}, {1, 0}, {0, 1}, {0, 2}},
            {{0, 0}, {0, 1}, {1, 1}, {2, 1}},
            {{0, 0}, {1, 0}, {1, -1}, {1, -2}},

            {{0, 0}, {1, 0}, {2, 0}, {2, -1}},
            {{0, 0}, {1, 0}, {1, 1}, {1, 2}},
            {{0, 0}, {0, 1}, {1, 0}, {2, 0}},
            {{0, 0}, {0, 1}, {0, 2}, {1, 2}},

            {{0, 0}, {0, 1}, {0, 2}, {1, 1}},
            {{0, 0}, {1, 0}, {2, 0}, {1, -1}},
            {{0, 0}, {1, 0}, {1, -1}, {1, 1}},
            {{0, 0}, {1, 0}, {2, 0}, {1, 1}},

            {{0, 0}, {1, 0}, {1, 1}, {2, 1}},
            {{0, 0}, {0, 1}, {1, 0}, {1, -1}},
            {{0, 0}, {1, 0}, {1, -1}, {2, -1}},
            {{0, 0}, {0, 1}, {1, 1}, {1, 2}},

            {{0, 0}, {0, 1}, {1, 0}, {1, 1}},

            {{0, 0}, {0, 1}, {0, 2}, {0, 3}},
            {{0, 0}, {1, 0}, {2, 0}, {3, 0}}
    };

    /*
        L
        {0,0} + {1,0} + {2,0} + {2,1}
        {0,0} + {1,0} + {0,1} + {0,2}
        {0,0} + {0,1} + {1,1} + {2,1}
        {0,0} + {1,0} + {1,-1} + {1,-2}

        ┘
        {0,0} + {1,0} + {2,0} + {2,-1}
        {0,0} + {1,0} + {1,1} + {1,2}
        {0,0} + {0,1} + {1,0} + {2,0}
        {0,0} + {0,1} + {0,2} + {1,2}

        ㅗ
        {0,0} + {0,1} + {0,2} + {1,1}
        {0,0} + {1,0} + {2,0} + {1,-1}
        {0,0} + {1,0} + {1,-1} + {1,1}
        {0,0} + {1,0} + {2,0} + {1,1}

        ㄹ
        {0,0} + {1,0} + {1,1} + {2,1}
        {0,0} + {0,1} + {1,0} + {1,-1}
        {0,0} + {1,0} + {1,-1} + {2,-1}
        {0,0} + {0,1} + {1,1} + {1,2}

        ㅁ
        {0,0} + {0,1} + {1,0} + {1,1}

        -
        {0,0} + {0,1} + {0,2} + {0,3}
        {0,0} + {1,0} + {2,0} + {3,0}


     */




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
            }
        }
        answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer = Math.max(answer, calc(i, j));
            }
        }
        System.out.println(answer);

        br.close();
    }

    static int calc(int r, int c) {
        int ret = 0;
        for (int i = 0; i < dir.length; i++) {
            int sum = 0;
            for (int j = 0; j < 4; j++) {
                int nr = r + dir[i][j][0];
                int nc = c + dir[i][j][1];
                if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                sum += map[nr][nc];
            }
            ret = Math.max(ret, sum);
        }
        return ret;
    }
}
