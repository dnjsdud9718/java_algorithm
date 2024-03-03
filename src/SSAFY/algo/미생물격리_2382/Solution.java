package SSAFY.algo.미생물격리_2382;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int T, N, M, K;
    static int[][] map;
    static long[][][] store;
    static Virus[] virus;
    static int[] dr = {1, 0, -1, 0}; // 하0 우1 상2 좌3
    static int[] dc = {0, 1, 0, -1};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            virus = new Virus[K+1];
            for (int i = 1; i <= K; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                if(d == 1) d = 2;
                else if(d == 2) d = 0;
                else if(d == 4) d = 1;
                virus[i] = new Virus(r, c, k, d, 0);
            }
//            for (int g = 1; g <= K; g++) {
//                System.out.println(g+ " " +virus[g]);
//            }
//            System.out.println("==============");

            for (int i = 0; i < M; i++) {
                store = new long[N][N][2];
                for (int j = 1; j <= K; j++) {
                    if(virus[j].dead == 1) continue;
                    Virus v = virus[j];
                    v.r = v.r + dr[v.dir];
                    v.c = v.c + dc[v.dir];
                    if (v.r == 0 || v.r == N - 1 || v.c == 0 || v.c == N - 1) { // 경계 도달
                        v.num /= 2;
                        v.dir = (v.dir + 2) % 4;
                        if (v.num == 0) v.dead = 1;
                    } else {
                        if (store[v.r][v.c][0] != 0) {
                            if (store[v.r][v.c][1] > v.num) {
                                v.dead = 1;
                                virus[(int)store[v.r][v.c][0]].num += v.num;
                            } else {
                                store[v.r][v.c][1] = v.num;
                                virus[(int)store[v.r][v.c][0]].dead = 1;
                                v.num += virus[(int)store[v.r][v.c][0]].num;
                                store[v.r][v.c][0] = j;
                            }
                        } else {
                            store[v.r][v.c][0] = j;
                            store[v.r][v.c][1] = v.num;
                        }
                    }
                }
//                for (int g = 1; g <= K; g++) {
//                    System.out.println(g+ " " +virus[g]);
//                }
//                System.out.println("========");
            }
            long answer = 0;
            for (int i = 1; i <= K; i++) {
                if(virus[i].dead == 0) answer += virus[i].num;
            }
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
    static class Virus {
        int r, c, dir, dead;
        long num;
        public Virus(int r, int c, long num, int dir, int dead) {
            this.r = r;
            this.c = c;
            this.num = num;
            this.dir = dir;
            this.dead = dead;
        }

        @Override
        public String toString() {
            return "Virus{" +
                    "r=" + r +
                    ", c=" + c +
                    ", dir=" + dir +
                    ", dead=" + dead +
                    ", num=" + num +
                    '}';
        }
    }
}
