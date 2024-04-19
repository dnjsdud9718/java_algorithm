package SSAFY.study.week13.t12931;

import java.io.*;
import java.util.*;

public class Main
{
    static int N, cnt, max;
    static int[] B;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        B = new int[N];
        st = new StringTokenizer(br.readLine());
        cnt = 0;
        max = 0;
        for(int i=0; i<N;i++){
            B[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, B[i]);
        }
        int cntMax = 0;
        for(int i=0; i<N; i++){
            if(B[i] == max) {
                cntMax++;
                continue;
            }
            while(B[i] > 0){
                if(B[i] % 2 == 0) B[i]/=2;
                else {
                    B[i]--;
                    cnt++;
                }
            }
        }
        while(max > 0) {
            if(max%2==0) {
                max/=2;
                cnt++;
            }
            else {
                max--;
                cnt += cntMax;
            }
        }


        System.out.println(cnt);
        br.close();
    }
}
