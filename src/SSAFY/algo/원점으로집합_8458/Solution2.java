package SSAFY.algo.원점으로집합_8458;

/*
    팁: 원점으로부터의 거리가 짝수/홀수인 애들끼리 원점에 도달 가능하다.

 */

import java.io.*;
import java.util.*;
public class Solution2 {
    static int T, N, move, answer;
    static int[] src;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            N = Integer.parseInt(br.readLine());
            src = new int[N];
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int d = Math.abs(x) + Math.abs(y);
                src[i] = d;

            }
            move = 0;

            while(true) {
                int ones =0;
                int zeros = 0;
                for(int i=0; i<N; i++) {
                    if(src[i] == 0) zeros++;
                    else if(src[i] == 1) ones++;
                    if(zeros > 0 && ones > 0) break;
                }
//              System.out.println(zeros + " " + ones);
                if(ones > 0 && zeros > 0) {
                    answer = -1;
                    break;
                }
                if(zeros == N) {
                    answer = move;
                    break;
                }
                move++;
                for(int i=0; i<N; i++) {
                    if(src[i] >= move) {
                        src[i] -= move;
                    }
                    else { // src[i] < move
                        if((src[i]+move)%2 == 0) { // even
                            src[i] = 0;
                        }else { // odd
                            src[i] = 1;
                        }
                    }
                }
            }
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}