package SSAFY.study.week7.t7579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer;
    static int[] arr;
    static int[] cost;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        cost = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(cost));
        answer = Integer.MAX_VALUE;
        dfs(0, 0, 0);
        System.out.println(answer);
        br.close();
    }

    public static void dfs(int idx, int sum, int c) {
        if( sum >= M){
            answer = Math.min(answer, c);
            return;
        }
        if (idx == N) {
            return;
        }
        for (int i = idx; i < N; i++) {
            dfs(i + 1, sum + arr[i], c + cost[i]);
        }
    }
}
