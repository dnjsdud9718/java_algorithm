package SSAFY.study.week12.t14501;

import java.io.*;
import java.util.*;

// subset
public class Main
{
    static int N;
    static int[] v;
    static int[] t;
    static int[] tgt;
    static int tLen;
    static int max = -1;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine()); // 1 <= N <= 15;
        v = new int[N]; // value
        t = new int[N]; // time
        tgt = new int[N];
        tLen = 0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int tt = Integer.parseInt(st.nextToken());
            int vv = Integer.parseInt(st.nextToken());
            t[i] = tt;
            v[i] = vv;
        }
        // System.out.println(Arrays.toString(t));
        // System.out.println(Arrays.toString(v));
        subset(0,0);
        System.out.println(max == -1 ? 0 : max);
        br.close();
    }
    public static void subset(int idx, int cnt){
        if(cnt == N){
            if(tLen != 0) max = Math.max(max, solve());
            return;
        }
        subset(idx+1, cnt+1);

        tgt[tLen++] = idx;
        subset(idx+1, cnt+1);
        tLen--;
    }
    /*
    I 0   1   2   3   4   5   6   7   8   9
    t 5   4   3   2   1   1   2   3   4   5
    v 50  40  3   20  10  10  20  30  40  50
    */
    public static int solve(){
        int ret = v[tgt[0]];
        int usedTime = tgt[0] + t[tgt[0]] - 1;

        for(int i=1; i<tLen; i++){
            if(usedTime < tgt[i]){ // 4 < 5
                usedTime = tgt[i] -1;
                if(usedTime + t[tgt[i]] < N){
                    usedTime += t[tgt[i]];
                    ret += v[tgt[i]];
                }else return -1;
            }else return -1;
        }
        if(usedTime >= N) return -1;
        return ret;
    }
}
