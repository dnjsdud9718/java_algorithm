package SSAFY.study.week13.t2568;

import java.util.*;
import java.io.*;
public class Main
{
    static final int MAX_SIZE = 500_000;
    static int[] src = new int[MAX_SIZE+1];
    static int[] choice = new int[MAX_SIZE+1];;
    static boolean[] b = new boolean[MAX_SIZE+1];

    static int N;
    static int[] dp;
    static int dpSize;

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            src[a] = b;
        }

        dp = new int[N+1];
        dpSize = 1;
        for(int i=1; i<=MAX_SIZE; i++){
            if(src[i] == 0) continue;
            if(dpSize >= N) break;
            if(dp[dpSize-1] < src[i]) {
                dp[dpSize] = src[i];
                choice[i] = dpSize;
                dpSize++;
            }
            else{
                int ret = lowerBound(0, dpSize, src[i]);
                dp[ret] = src[i];
                choice[i] = ret;
            }
        }
// 		for(int i=1; i<=10; i++) System.out.printf("%d ", choice[i]);
        System.out.println();
        sb.append(N-(dpSize-1)).append("\n");
        int cnt = dpSize-1;
        for(int i=MAX_SIZE; i > 0 ; i--){
            if(cnt < 1) break;
            if(cnt == choice[i]){
                b[i] = true;
                cnt--;
            }
        }
        for(int i=1; i<=MAX_SIZE; i++){
            if(b[i] || choice[i] == 0) continue;
            sb.append(i).append("\n");
        }
        br.close();
        System.out.println(sb);
    }
    public static int lowerBound(int lt, int rt, int x){

        while(lt < rt) {
            int mid = (lt+rt)/2;
            if(dp[mid] >= x){
                rt = mid;
            }else{
                lt = mid + 1;
            }
        }
        return rt;
    }
}

