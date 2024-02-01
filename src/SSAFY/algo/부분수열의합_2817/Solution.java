package SSAFY.algo.부분수열의합_2817;
/*
 부분 수열의 합
 A1, A2, ... , AN의 N개의 자연수가 주어졌을 때,
 최소 1개 이상의 수를 선택하여 그 합이 K가 되는 경우의 수를 구하는 프로그램을 작성하시오.
 조합은 N개 중에 R개를 택하는 것.
 부분집합은 2^N 모든 경우를 고려하는 것 nC0 + nC1 + ,,, + nCn
 이 문제는 최소 1개 이상의 수를 택하는 것이니 공집합을 제외한 모든 조합을 고려하는 부분집합 문제다.
    1 T
    4 3 N K
    1 2 1 2 A1, A2, .. ,AN

    boolean 배열을 대신하여 bit masking을 적용!!!
    입력되는 Ai가 자연수이기 때문에 가지치기 가능하다.

    매개변수로 sum값을 넘겨주는 것이 더 효율적이다.
    방문 표시를 별도로 할 필요가 없다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

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

            src = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            answer = 0;
            //binary counting
            int cases = 1<<N;
            for(int i=1; i<cases; i++){
                int sum = 0;
                for(int j=0; j<N; j++){
                    if((i & (1<<j)) != 0) {
                        sum += src[j];
                        if(sum > K) break;
                    }
                }
                if(sum == K) answer++;
            }
            // subset(0,0);
            sb.append("#").append(ts).append(" ").append(answer).append("\n");
        }

        System.out.println(sb.toString());
        br.close();
    }
    public static void subset(int idx, int sum){
        if(sum == K){
            answer++;
            return;
        }
        if(idx == N || sum > K) return;

        subset(idx+1, sum + src[idx]);
        subset(idx+1, sum);
    }
    // public static void subset(int idx, int mask){
    //     if(idx == N){
    //         int sum = 0;
    //         for(int i=0 ;i<N; i++){
    //             if((mask & (1<<i)) != 0) {
    //                 sum += src[i];
    //                 if(sum > K) return;
    //             }
    //         }
    //         if(sum == K) answer++; 
    //         return;
    //     }
    //     // 부분집합에 넣었다.
    //     subset(idx+1, mask | (1<<idx));
    //     //부분집합에 안 넣었다.
    //     subset(idx+1, mask);

    // }
}