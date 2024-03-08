package SSAFY.algo.baekjoon.t13144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//5
//1 2 3 4 5
public class Main {
  static int N, left, right;
  static long answer;
  static int[] src;
  static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        src = new int[N + 1];
        check = new boolean[100001];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            src[i] = Integer.parseInt(st.nextToken());
        }

        long t=N;
        answer = t * (t + 1) / 2;
        left=1;
        right = 1;
        while (right <= N) {
            if(check[src[right]]){
                while(src[left] != src[right]) {
                    check[src[left]] = false;
                    answer -= (N - right + 1);
                    left++;
                }
                answer -= (N-right+1);
                left++;
            }
            check[src[right]] = true;
            right++;
        }
        System.out.println(answer);
        br.close();
    }
}
