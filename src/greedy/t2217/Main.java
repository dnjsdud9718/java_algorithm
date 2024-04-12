package greedy.t2217;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
    baekjoon 2217 로프 Greedy
    1. 버틸 수 있는 무게가 최대인 로프부터 선택

    W/K <= 선택된 로프 중 가장 작은 값 : 선택된 모든 로프가 버틸 수 있다.
    W == K * 선택된 로프 중 가장 작은 값
    ans = max(ans, K * 선택된 로프 중 가장 작은 값)
 */
public class Main {
    static int N, k;
    static long w, ans;
    static int[] loaf;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        loaf = new int[N];
        for (int i = 0; i < N; i++) {
            // loaf[i] : i번째 로프가 버틸 수 있는 최대 중량
            loaf[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(loaf);
        w = ans = k = 0;
        for (int i = N - 1; i >= 0; i--) {
            k++;
            ans = Math.max(ans, (long) k * loaf[i]);
        }
        System.out.println(ans);
        br.close();
    }
}
