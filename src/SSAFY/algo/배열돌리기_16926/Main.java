package SSAFY.algo.배열돌리기_16926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
    static int N, M, R, dir;
    static int[][] src;
    static int[][] tgt;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {-1, 0, 1, 0};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputs[0];
        M = inputs[1];
        R = inputs[2];
        src = new int[N][M];
        tgt = new int[N][M];
        for(int i=0; i<N; i++){
            src[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for(int i=0; i<R; i++) rotate();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                sb.append(src[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
    static  void rotate(){ // 1회 회전
        int sy = 0, ey = N-1;
        int sx = 0, ex = M-1;

        while(true){
//           종료 조건
            if(ey - sy < 1 || ex - sx < 1) return;
//            from rigth to left
            int tmp = src[sy][sx];
            for (int i=sx; i<ex; i++){
                src[sy][i] = src[sy][i+1];
            }
//            from bottom to top
            for (int i=sy; i<ey; i++){
                src[i][ex] = src[i+1][ex];
            }
//            from left to right\
            for (int i=ex; i > sx; i--){
                src[ey][i] = src[ey][i-1];
            }
//            from top to bottom
            for (int i=ey; i > sy; i--){
                src[i][sx] = src[i-1][sx];
            }
            src[sy+1][sx] = tmp;
            sy++; sx++;
            ey--; ex--;
        }

    }
}
