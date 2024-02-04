package SSAFY.study.t2748;

import java.io.*;
public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] fib = new long[N+1];
        fib[1] = 1;
        for(int i=2; i<=N; i++) fib[i] = fib[i-1] + fib[i-2];
        System.out.println(fib[N]);
        br.close();
    }
}
