package Q1.T10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    최솟값 찾기
    11003
    시간초과 나는 코드
 */

public class Main2 {
    static int N, L;
    static int[] src;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        src = new int[N];
        for (int i = 0; i < N; i++) {
            src[i] = Integer.parseInt(st.nextToken());
        }
        // O(N*L) 1<= L <= N <= 5,000,000
        for (int i = 0; i < N; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = i; j > i-L && j >= 0; j--) {
                min = Math.min(min, src[j]);
            }
            System.out.print(min+" ");
        }
        br.close();
    }
}
