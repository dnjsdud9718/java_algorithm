package SSAFY.algo.도영이가만든맛있는음식_2961;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 2961 도영이가 만든 맛있는 음식
 지금 도영이 앞에는 재료가 N개가 있고 각 재료는 신맛S와 쓴맛B를 알고 있다. 여러 재료를 이용해서 요리할 때,
 그 음식의 신맛은 사용한 재료의 신맛의 곱이고, 쓴 맛은 합이다.

 도영이는 재료를 ☆적절히 섞어서☆ 요리의 신맛과 쓴맛의 차이를 작게 만들려고 한다. 재료는 ☆적어도 하나☆
 사용해야 한다.
 재료의 신맛과 쓴맛이 주어졌을 때, 신맛과 쓴맛의 차이가 가장 작은 요리를 만드는 프로그램 작성.

 2  N
 3 8 S1 B1
 5 8 S2 B2

 다양한 풀이 방법
 1. bit masking
 2. binaryCounting
 3. selected array
 4. S와 P를 파라미터로 전달. (처음 생각난 것)

 재료를 적어도 하나 적절히 섞어서 요리를 만들어야 한다. => 부분 집합 문제
 */
public class Main {
    static int N;
    static int src[][];
    static int subsetCount, answer;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        src = new int[N][2];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            src[i][0] = Integer.parseInt(st.nextToken()); // i번 재료 신맛
            src[i][1] = Integer.parseInt(st.nextToken()); // i번 재료 쓴맛
        }
        // binary counting
        subsetCount = 1<<N; // 경우의 수
        answer = Integer.MAX_VALUE;
        for(int i=1 ; i< subsetCount; i++){ // ingredients가 최소 1개 있어야 하므로 1부터 시작, i는 부분집합을 의미
            int P, B;
            P = 1; 
            B = 0;
            for(int j=0; j<N; j++){
                if((i & (1<<j))!=0) {
                    P *= src[j][0];
                    B += src[j][1];
                }
            }
            answer = Math.min(answer, Math.abs(P-B));
        }
        // subset(0,1,0);
        // subset1(0,0);
        
        System.out.println(answer);
        br.close();
    }

    // 비트 마스킹
    public static void subset1(int idx, int mask){
        if(idx == N){
            int S = 1;
            int B = 0;
            int cnt = 0;
            for(int i=0; i<N; i++){
                if((mask & (1<<i)) != 0){
                    S *= src[i][0];
                    B += src[i][1];
                    cnt++;
                }
            }
            if(cnt != 0) answer = Math.min(answer, Math.abs(S-B));
            return;
        }

        subset1(idx+1, mask | (1<<idx));
        subset1(idx+1, mask);
    }
    // 신맛과 쓴맛 정보를 파라미터를 통해 전달
    public static void subset(int idx , int S, int B){
        if(idx == N){
            // 재료가 반드시 하나 이상 들어가야 한다. -> B가 0일 수 없다. 양의 정수
            if(B != 0) answer = Math.min(answer, Math.abs(S-B));
            return;
        }
        // idx 재료를 부분집합에 넣는다.
        subset(idx+1, S*src[idx][0], B+src[idx][1]);
        // 넣지 않는다.
        subset(idx+1, S, B);
    }

}
