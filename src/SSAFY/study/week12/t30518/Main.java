package SSAFY.study.week12.t30518;

import java.util.*;
import java.io.*;
public class Main
{
    static final int MOD = 1_000_000_007;
    static char[] arr;
    static char[] tgt;
    static int tLen = 0;
    static char lighter;
    static int K;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        lighter = br.readLine().charAt(0);
        String s = br.readLine();
        K = s.length()+1;
        tgt = new char[K];
        tgt[tLen++] = lighter;

        arr = new char[K];
        arr[0] = lighter;
        for(int i=0; i<K-1; i++){
            arr[i+1] = s.charAt(i);
        }
        subset(1,1);
        System.out.println(count);
    }
    public static void subset(int idx, int cnt){
        if(cnt == K){
            boolean prevLose = false;
            for(int i=1; i<tLen; i++){
                if(tgt[i] == tgt[i-1] && prevLose){
                    return;
                }
                prevLose = game(tgt[i], tgt[i-1]);
            }
            if(tLen != 1) count = (count+1) % MOD;
            return;
        }
        tgt[tLen++] = arr[idx];
        subset(idx+1, cnt+1);
        tLen--;
        subset(idx+1, cnt+1);
    }
    // a 기준 승무패
    public static boolean game(char a, char b){
        if(a == b) return false;
        switch(a){
            case 'R':
                if(b == 'S') return false;
                break;
            case 'S':
                if(b == 'P') return false;
                break;
            case'P':
                if(b == 'R') return false;
                break;
        }
        return true;
    }
}
