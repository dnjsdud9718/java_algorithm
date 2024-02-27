package SSAFY.study.week6.t10861;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
    baekjoon 10816 숫자 카드 2
    포인터 사용하여 문제 해결 -> 정렬을 해야하기 때문에 정렬 전 상태를 기억할 필요가 있다 -> 해시맵
    10
    6 3 2 10 10 10 -10 -10 7 3
    8
    10 9 -5 2 3 4 5 -10
 */
public class Main {
    static int N, M;
    static int[] A;
    static int[] B;
    static HashMap<Integer, Integer> hm1, hm2;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        A = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        A[N] = 987_654_321;
        M = Integer.parseInt(br.readLine());
        B = new int[M];
        hm1 = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
            hm1.put(i, B[i]);
        }
        Arrays.sort(A);
        Arrays.sort(B);
        hm2 = new HashMap<>(); // key : B[j], value : counts[B[j]]
        int i,j,cnt;
        i = j = cnt = 0;
        while (j < M) {
            if (A[i] == B[j]) {
                i++;
                cnt++;
            } else if (A[i] > B[j]) {
                if(!hm2.containsKey(B[j])) hm2.put(B[j], cnt);
                cnt = 0;
                j++;
            } else { // A[i] < B[j]
                i++;
            }
        }
        for (int n=0; n < M; n++) {
            sb.append(hm2.get(hm1.get(n))).append(' ');
        }
        System.out.println(sb);
        br.close();
    }
}
