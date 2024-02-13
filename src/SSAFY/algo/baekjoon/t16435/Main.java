package SSAFY.algo.baekjoon.t16435;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, L;
    static int[] src;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        src = new int[N];
        st = new StringTokenizer(br.readLine());
        int idx = 0;
        while (st.hasMoreTokens()) {
            src[idx++] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(src);
        idx =0;
        while (true) {
            if(src[idx++] <= L) L++;
            else break;
            if (idx == N) {
                break;
            }
        }
        System.out.println(L);
        br.close();
    }
}
