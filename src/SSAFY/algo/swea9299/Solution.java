package SSAFY.algo.swea9299;


// 9299 한빈이와 Spot Mart
// 한빈이는 퇴근길에 스팟마트에 들러 과자 두 봉지를 사서 양 손에 하나씩 들고 가려고 한다.
// 마트에는 N개의 과자 봉지가 있으며, 각 과자 봉지는 Ai그램의 무게를 가지고 있다.
// 배가 많이 고픈 한빈이는 최대한 양이 많은(무게가 많은) 과자 봉지를 고르고 싶으나,
//  과자 두 봉지의 무게가 M그램을 초과하면 무거워서 과자를 들고 다닐 수 없다.
// 한빈이가 들고 다닐수 있는 과자들의 최대 무게 합을 출력, 정확히 두 봉지
// 
// 3 45 N M
// 20 20 20 A1 A2 A3
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
