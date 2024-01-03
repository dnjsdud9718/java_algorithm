package SSAFY.sw1.파리퇴치3;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;
public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for(int ts=1; ts<=T; ts++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[][] grid = new int[N][N];
            for(int i=0; i<N; i++){
                grid[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();                
            }
            int answer = Integer.MIN_VALUE;
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    int sumA=grid[i][j];
                    int sumB=grid[i][j];
                    for(int t=1; t<M; t++){
                        if(i-t >= 0) sumA += grid[i-t][j];
                        if(i+t < N) sumA += grid[i+t][j];
                        if(j-t >= 0) sumA += grid[i][j-t];
                        if(j+t <N) sumA += grid[i][j+t];

                        if(i-t >= 0 && j-t >= 0) sumB += grid[i-t][j-t];
                        if(i+t < N && j+t < N) sumB += grid[i+t][j+t];
                        if(i-t >= 0 && j+t < N) sumB += grid[i-t][j+t];
                        if(i+t < N && j-t >= 0) sumB += grid[i+t][j-t];

                    }
                    int m = Math.max(sumA, sumB);
                    answer = Math.max(answer, m);
                }
            }
            bw.write("#"+ts+" "+answer+"\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}