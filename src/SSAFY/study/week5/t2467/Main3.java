package SSAFY.study.week5.t2467;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    2467 용액
    풀이 --> 투 포인터
 */
public class Main3 {
    static int N;
    static int[] seq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        seq = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int left, ansLeft, right, ansRight;
        left = ansLeft = 0;
        right = ansRight = N-1;
        int min = Math.abs(seq[ansLeft] + seq[ansRight]);
        while (left < right) {
            if (seq[left] + seq[right] == 0) {
                System.out.println(seq[left] + " " + seq[right]);
                return;
            }
            if (Math.abs(seq[left] + seq[right]) < min) {
                min = Math.abs(seq[left] + seq[right]);
                ansLeft = left;
                ansRight = right;
            }
            if(seq[left]+seq[right] < 0) left++;
            else right--;
        }
        System.out.println(seq[ansLeft] + " " + seq[ansRight]);
    }
}
