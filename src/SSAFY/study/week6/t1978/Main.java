package SSAFY.study.week6.t1978;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    1978 소수 찾기
    에라토스테네스의 체

 */
public class Main {
    static int N, cnt;
    static int[] src;
    static boolean[] info;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        src = new int[N];
        for (int i = 0; i < N; i++) {
            src[i] = Integer.parseInt(st.nextToken());
        }
        // 에라토스테네스의 체
        info = new boolean[1001];
        for (int i = 2; i <= Math.sqrt(1001); i++) {
            if(info[i]) continue;
            for (int j = i * 2; j < 1001; j += i) {
                info[j]=true;
            }
        }
        cnt = 0;
        for (int i = 0; i < N; i++) {
            if (src[i] >= 2 && !info[src[i]]) {
                cnt++;
            }
        }
        System.out.println(cnt);
        br.close();
    }
}
