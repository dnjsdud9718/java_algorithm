package SSAFY.algo.baekjoon.t1992;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
    static int N;
    static String answer;
    static int[][] src;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        src = new int[N][];
        for (int i = 0; i < N; i++) {
            src[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
//            System.out.println(Arrays.toString(src[i]));
        }
        answer = "";
        logic(0, 0, N, N);
        System.out.println(answer);

        br.close();
    }

    public static void logic(int sy, int sx, int ey, int ex) {
        if (sy == ey && sx == ex) {
            return;
        }
        answer += "(";
        int v = src[sy][sx];
        boolean b = true;
        for (int i = sy; i < ey; i++) {
            for (int j = sx; j < ex; j++) {
                if (v != src[i][j]) {
                    b = false;
                    break;
                }
            }
            if(!b) break;
        }
        if (!b) {
            System.out.println(" "+sy+" "+ sx+" "+ (ey/2) +" "+ (ex/2));
            logic(sy, sx, ey/2, ex/2);
            System.out.println(" "+sy+" "+ (ex/2)+" "+ (ey/2) +" "+ ex);
            logic(sy, ex / 2, ey / 2, ex);
            System.out.println(" "+(ey/2)+" "+sx+" "+ey+" "+(ex/2));
            logic(ey / 2, sx, ey, ex / 2);
            System.out.println(" "+(ey/2)+" "+(ex/2) + " "+ ey+" "+ ex);
            logic(ey/2, ex/2, ey, ex);
        } else {
            answer += v + ")";
        }
    }
}
