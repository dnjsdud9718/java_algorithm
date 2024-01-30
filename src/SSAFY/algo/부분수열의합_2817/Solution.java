package SSAFY.algo.부분수열의합_2817;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Solution {
    static StringBuilder sb = new StringBuilder();
    static int N, K;
    static int[]src;
    static long answer;
    static boolean visited[];
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
            visited = new boolean[N+1];
            answer = 0;
            for(int i=1; i<=N; i++){
                perm(i, src[i]);
            }
            sb.append("#").append(ts).append(" ").append(answer).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
    public static void perm(int x, int sum){
        visited[x] = true;
        if(sum == K) {
            answer++;
        }else if(sum < K){
            for(int i=x+1; i<=N; i++){
                if(!visited[i]){
                    perm(i, sum+src[i]);
                }
            }
        }
        
        visited[x] = false;
    }
}