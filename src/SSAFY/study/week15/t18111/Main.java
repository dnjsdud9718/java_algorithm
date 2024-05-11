package SSAFY.study.week15.t18111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, B;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int min = Integer.MAX_VALUE;
        int height = 0;
        for (int h = 0; h <= 256; h++) {
            int inventory = B;
            int time = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int stackHeight = Math.abs(h - map[i][j]);
                    // if map[i][j] < h -> 블록을 쌓아야한다. -> 1초
                    if (map[i][j] < h) {
                        time += stackHeight;
                        inventory -= stackHeight;
                    }
                    // if map[i][j] > h -> 블록을 제거하여 인벤토리에 넣는다 -> 2초
                    if (map[i][j] > h) {
                        time += stackHeight * 2;
                        inventory += stackHeight;
                    }
                }
            }
            if (inventory >= 0 && min >= time) {
                min = time;
                height = h;
            }
        }
        System.out.println(min + " " + height);
    }
}

