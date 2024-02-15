package SSAFY.study.week3.t2630;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2630 색종이 만들기
// 8
// 1 1 0 0 0 0 1 1
// 1 1 0 0 0 0 1 1
// 0 0 0 0 1 1 0 0
// 0 0 0 0 1 1 0 0
// 1 0 0 0 1 1 1 1
// 0 1 0 0 1 1 1 1
// 0 0 1 1 1 1 1 1
// 0 0 1 1 1 1 1 1

//output
// 9 -> 하얀 색종이의 개수
// 7 -> 파란 색종이의 개수
public class Main {
    static int N;
    static int[][] src;
    static int[] colors = new int[2]; // colors[0] => 하얀 색종이 수, colors[1] => 파란 색종이 수
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        src = new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                src[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        subset(1,1,N,N,N);
        sb.append(colors[0]).append("\n");
        sb.append(colors[1]).append("\n");
        System.out.println(sb.toString());
        
        br.close();
    }
    public static void subset(int r1, int c1, int r2, int c2, int n){
        if(n == 0) return;
        boolean flag = true;
        int color = src[r1][c1];
        for(int i=r1; i<=r2; i++){
            for(int j=c1; j<=c2; j++){
                if(color != src[i][j]){
                    flag = false;
                    break;
                }
            }
        }
        if(!flag){
            int m = n/2; 
            subset(r1, c1, r2-m, c2-m,m);
            subset(r1+m, c1+m, r2, c2, m);
            subset(r1, c1+m, r2-m, c2, m);
            subset(r1+m, c1, r2,c2-m, m);
        }else colors[color]++;
    }
}
