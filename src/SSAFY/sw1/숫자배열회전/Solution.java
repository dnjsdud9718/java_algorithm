package SSAFY.sw1.숫자배열회전;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;
public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for(int ts = 1; ts<=T;ts++){
            int N = Integer.parseInt(br.readLine());
            int[][] grid = new int[N][N];
            for(int i=0; i<grid.length;i++) {
                grid[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            bw.write("#"+ts+"\n");
            for(int i=0; i<N; i++){
                for(int j=N-1; j>=0; j--){
                    bw.write(""+grid[j][i]);
                }
                bw.write(" ");
                for(int j=N-1; j>=0; j--){
                    bw.write(""+grid[N-i-1][j]);
                }
                bw.write(" ");
                for(int j=0; j<N; j++){
                    bw.write(""+grid[j][N-i-1]);
                }
                bw.write("\n");
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
