package SSAFY.algo.swea9299;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Solution {
    static int T, N, M, ans;
    static int[] src;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++){
            int[] inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            N = inputs[0];
            M = inputs[1]; // 제한
            src = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            ans = 0;
            comb(0,0, 0);
            sb.append("#").append(t).append(" ").append(ans == 0 ? -1 : ans).append("\n");

        }
        System.out.println(sb.toString());
        br.close();
    }
    public static void comb(int srcIdx, int tgtIdx, int sum){
        if(tgtIdx == 2){
            if(sum <= M)  ans = Math.max(ans, sum);
            return;
        }
        for(int i=srcIdx; i<N; i++){
            comb(i + 1, tgtIdx + 1, sum+src[i]);
        }
    }
}
