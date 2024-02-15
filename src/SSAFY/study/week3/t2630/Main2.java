package SSAFY.study.week3.t2630;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

/*
    8
    1 1 0 0 0 0 1 1
    1 1 0 0 0 0 1 1
    0 0 0 0 1 1 0 0
    0 0 0 0 1 1 0 0
    1 0 0 0 1 1 1 1
    0 1 0 0 1 1 1 1
    0 0 1 1 1 1 1 1
    0 0 1 1 1 1 1 1

    1 -> blue
    0 -> white
 */
public class Main2 {
    static int blue;
    static int white;
    static int N;
    static int[][] src;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        src = new int[N][N];
        for (int i = 0; i < N; i++) {
            src[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        blue = white = 0;
        logic(0, 0, N);
        System.out.println(white);
        System.out.println(blue);
        br.close();
    }

    public static void logic(int y, int x, int n) {
        if(n < 1 ) return;
        int v = src[y][x];
        for (int i = y; i < y + n; i++) {
            for (int j = x; j < x + n; j++) {
                if(v != src[i][j]){
                    v = -1;
                    break;
                }
            }
            if(v < 0) break;
        }
        if(v == 0) white++;
        else if (v == 1) {
            blue++;
        } else {
            int m = n/2;
            logic(y, x, m);
            logic(y, x + m, m);
            logic(y + m, x, m);
            logic(y + m, x + m, m);
        }
    }
}
