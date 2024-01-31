package SSAFY.algo.부분수열의합_2817;
/*
 부분 수열의 합
 키 포인트: 부분 수열(수열의 순서를 무시하면 안 된다.)
 조합으로 해결 가능하네...

 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static StringBuilder sb = new StringBuilder();
    static int N, K;
    static int[]src;
    static long answer;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int ts=1; ts <=T; ts++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            src = new int[N+1];
            st = new StringTokenizer(br.readLine());
            int idx=1;
            while(st.hasMoreTokens()){
                src[idx] = Integer.parseInt(st.nextToken());
                idx++;
            }
            answer = 0;
            comb(1, 0);
            sb.append("#").append(ts).append(" ").append(answer).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
    public static void comb(int idx, int sum){
        if(sum == K){
            answer++;
            return;
        }
        if(sum > K) return;
        for(int i=idx; i < src.length; i++){
            comb(i+1, sum+src[i]);
        }
    }
}