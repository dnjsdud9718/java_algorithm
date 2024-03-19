package SSAFY.study.week9.t1799;

import java.util.Arrays;

public class Test {
    static boolean[][] visit;
    static int[][] map;
    static int N;
    public static void main(String[] args) {
        N = 3;
        map = new int[N][N];
        int v = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = v++;
                System.out.printf("%d ", map[i][j]);
            }
            System.out.println();
        }
        visit = new boolean[N][N];
        mehtod(0, 0);

    }
    static void mehtod(int r, int c) {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(visit[i]));
        }
        System.out.println("--------");

        for (int i = r; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(visit[i][j]) continue;
                visit[i][j] = true;
                mehtod(i, j);
                visit[i][j] = false;
            }
        }
    }
}
