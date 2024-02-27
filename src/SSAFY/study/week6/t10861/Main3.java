package SSAFY.study.week6.t10861;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    10816 숫자 카드 2
    이분 탐색 -> lower/upper bounds
 */
public class Main3 {
    static int N, M;
    static int[] A;

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int val = Integer.parseInt(st.nextToken());
            sb.append(upper(val) - lower(val)).append(' ');
        }
        System.out.println(sb);

        br.close();
    }
    public static int lower(int X) {
        int left = 0;
        int right = N;
        while (left < right) {
            int mid = (left+right)/2;
            if (A[mid] >= X) {
                right = mid;
            } else if (A[mid] < X) {
                left = mid + 1;
            }
        }
        return right;
    }

    public static int upper(int X) {
        int left = 0;
        int right = N;
        while (left < right) {
            int mid = (left+right)/2;
            if (A[mid] > X) {
                right = mid;
            } else if (A[mid] <= X) {
                left = mid + 1;
            }
        }
        return right;
    }
}
