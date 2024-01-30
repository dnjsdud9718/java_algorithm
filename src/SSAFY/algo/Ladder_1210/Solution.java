package SSAFY.algo.Ladder_1210;
/*
 SWEA 1210 Ladder1
 delta이용 문제
 */ 

 import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.util.StringTokenizer;
 
 public class Solution {
     static int N = 100;
     static int[][] ladder = new int[100][100];
     static int[][] D = {{0,-1}, {0,1}, {-1,0}};
     static int  sy, sx, ans;
     static StringBuilder sb = new StringBuilder();
     public static void main(String[] args) throws Exception{
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
         for(int ts=1; ts<=10;ts++) {
             br.readLine();
             for(int i=0; i<N; i++) {
                 StringTokenizer st = new StringTokenizer(br.readLine());
                 int j = 0;
                 while(st.hasMoreTokens()) {
                     ladder[i][j] = Integer.parseInt(st.nextToken());
                     if(ladder[i][j] == 2) {
                         sy = i;
                         sx = j;
                     }
                     j++;
                 }
             }
             int dir = 2;
             while(true) {
                 if(dir == 2) { // TO TOP <=  왼, 오 먼저 고려, 없으면 계속 위로
                     for(int d=0; d<D.length; d++) {
                         int ny = sy+D[d][0];
                         int nx = sx+D[d][1];
                         if( nx >=0 && nx <N  && ladder[ny][nx] == 1) {
                             sy = ny;
                             sx = nx;
                             dir = d;
                             break;
                         }
                     }
                 }else { // TO LEFT OR TO RIGHT <= 위를 먼저 고려, 없으면 진행 방향 유지
                     int ny = sy-1;
                     int nx = sx;
                     if(ladder[ny][nx] == 1) {
                         sy = ny;
                         dir = 2;
                     }else {
                         sx = sx + D[dir][1];
                     }
                 }
                 if(sy == 0) {
                     ans = sx;
                     break;
                 }
             }
             sb.append("#").append(ts).append(" ").append(ans).append("\n");
         }
         System.out.println(sb.toString());
         br.close();
     }
 
 }
 