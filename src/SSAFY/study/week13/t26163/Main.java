package SSAFY.study.week13.t26163;

import java.util.*;
import java.io.*;
public class Main
{
    static int[] b = new int[6];
    static int max = -1;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i <= 5; i++ ){
            b[i] = Integer.parseInt(st.nextToken());
        }
        bruteForce(1, 0, 0, 0);
        System.out.println(max);
        br.close();
    }
    public static void bruteForce(int idx, int cond, int sum, int cnt){
        if(cnt <=3){
            if(cond >10) return;
        }else{
            if(cond > 15) return;
        }
        // System.out.println(idx + " " + cond + " " + sum + " " + cnt);
        max = Math.max(max, sum);
        for(int i=idx; i<=5; i++){
            bruteForce(i, cond+i, sum + b[i], cnt+1);
        }
    }
}

