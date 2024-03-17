package SSAFY.study.week8.t21396;

import java.io.*;
import java.util.*;

public class Test {
    static int T, N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            Map<Integer, Integer> map = new HashMap<>();
            long cnt = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int n = Integer.parseInt(st.nextToken());
                cnt += map.getOrDefault(n, 0);
                map.put(M ^ n, map.getOrDefault(M ^ n, 0) + 1);
            }
            sb.append(cnt).append('\n');
        }
        System.out.println(sb);
    }
}