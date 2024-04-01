package SSAFY.algo.원점으로집합_8458;

/*
    팁: 원점으로부터의 거리가 짝수/홀수인 애들끼리 원점에 도달 가능하다.
 */

import java.io.*;
import java.util.*;
public class Solution {
    static int T, N, move, answer, max;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            N = Integer.parseInt(br.readLine());
            answer = move = max = 0;
            int even =0;
            int odd = 0;
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int d = Math.abs(x) + Math.abs(y);
                if(d % 2 == 0) even ++;
                else odd++;
                max = Math.max(max,  d);
            }
            if(even > 0 && odd > 0) {
                answer = -1;
            }
            if(answer > -1) {
                while(true) {
                    if(max == 0) {
                        answer = move;
                        break;
                    }
                    move++;
                    if(max >= move) {
                        max -= move;
                    }
                    else {
                        if((max+move)%2 == 0) max = 0;
                        else max = 1;
                    }
                }
            }
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
