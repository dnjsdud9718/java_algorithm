package SSAFY.study.week1.t1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 baekjoon 1806 부분합
 10,000 이하의 자연수로 이뤄진 길이 N짜리 수열이 주어진다. 이 수열에서 연속된 수들의 부분합 중에 그 합이 S
 이상이 되는 것 중, 가장 짧은 것의 길이를 구하는 프로그램을 작성하시오.

 투 포인터 문제
 중요한 부분
 1. 포인터의 시작 위치
 2. while문의 종료 조건

 부분합 계산에서 자주 실수하는 부분
 i<j, sum[j] - sum[i], i+1 ~ j 사이의 부분합이다.

 ★ 연속된 수들의 부분합 중에 그 합이 S 이상이 되는 것 중, 가장 짧은 것의 길이
 ★ 부분합 배열은 오름차순 배열이다.


 */
public class Main {
    static int N,  S, left, right, ans;
    static int[] seq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        seq = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
            seq[i] += seq[i - 1]; // 부분합 계산.
        }
        // 투 포인터
        left = 0; // 부분합 배열의 0번째를 편의를 위해 비워둔다.
        right = 1;
        ans = N+1; // 길이 저장. 길이는 N을 넘지 못한다.

        // 종료 조건. right > N
        // 부분합이 S보다 작거나 left와 right가 같으면 right가 증가한다.
        // right가 N을 넘었다는 것은, left가 N위치에 있거나, left+1 ~ N까지의 합이 S보다 작단 의미.
        // 즉, 더 이상 S거나 같은 큰 부분합이 없다. 
        while (right<=N) {
            if (seq[right] - seq[left] >= S) {
                ans = Math.min(ans, right - left);
                left++;
            }else{  // seq[right] - seq[left] < S
                right++;
            }
            if(left == right) right++;
        }
        System.out.println(ans == N+1 ? 0 : ans);
        br.close();
    }
}
