package SSAFY.B형.week3.중간값구하기;
/*
    중간값
    반드시 정렬해야 찾을 수 있는가?
    k를 어떤 수열의 중간값이라고 하자
    1. k 좌우 값들의 갯수가 동일해야 한다.
    2. k의 왼쪽은 k보다 작아야 한다.
    3. k의 오른쪽은 k보다 커야 한다.
    max heap -> k의 왼쪽 값들을 저장하는 자료구조, max 힙의 최댓값은 k보다 작다.
    min heap -> k의 오른쪽 값들과 k를 저장, min 힙의 최솟값은 k다.
    min heap의 크기는 항상 max heap보다 1 크게 유지된다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    1
    3 5
    1 3
    2 6
    8 9
 */
public class Solution {
    static int N, S;
    static PriorityQueue<Integer> max;
    static PriorityQueue<Integer> min;
    static final int divisor = 20171109;
    static long answer;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
            answer = 0;
            max = new PriorityQueue<>(Collections.reverseOrder());
            min = new PriorityQueue<>();
            min.add(S);
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                max.add(n1);
                min.add(n2);
                n1 = max.peek();
                n2 = min.peek();
                if (n1 > n2) {
                    max.poll();
                    min.poll();
                    max.add(n2);
                    min.add(n1);
                }
                answer += min.peek() % divisor;
            }
            sb.append('#').append(t).append(' ').append(answer%divisor).append('\n');
        }
        System.out.println(sb);
        br.close();
    }
}
