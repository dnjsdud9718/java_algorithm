package SSAFY.algo.달팽이숫자_1954;
// 달팽이는 1부터 N*N까지의 숫자가 시계방향으로 이루어져 있다.
// 다음과 같이 정수 N을 입력 받아 N크기의 달팽이를 출력하시오.

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int[][] D = {
            {0,1},{1,0},{0,-1},{-1, 0}
        };

        int T = sc.nextInt();
        for(int ts = 1; ts <= T; ts++){
            int N = sc.nextInt();
            int [][] grid = new int[N+1][N+1];
            int cy = 1;
            int cx = 1;
            int val = 1;
            grid[cy][cx] = val++;
            int dir = 0;
            while(val <= N*N ){
                int ny = cy + D[dir][0];
                int nx = cx + D[dir][1];
                if(ny < 1 || nx < 1 || ny > N || nx > N || grid[ny][nx] != 0){
                    dir = (dir+1)%4;
                    ny = cy + D[dir][0];
                    nx = cx + D[dir][1];
                }
                grid[ny][nx] = val;
                cy = ny;
                cx = nx;
                val++;
            }
            sb.append("#").append(ts).append("\n");
            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++){
                    sb.append(grid[i][j]).append(" ");
                }
                sb.append("\n");
            }
        }
        sc.close();
        System.out.println(sb.toString());
    }    
}
