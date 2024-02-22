package Q1.T10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
    최솟값 찾기
    11003
    문제
    N개의 수 A1, A2, ... , AN과 L이 주어진다.
    Di = Ai-L+1 ~ Ai중의 최솟값이라고 할 때, D에 저장된 수를 출력하는 프로그램
    이때, i <= 0인 Ai는 무시한다.

    O(N) 해결
    Deque를 활용하는 방법
    덱의 크기는 L을 넘을 수 없다.
    원소를 하나씩 큐에 삽입하면서 다음과 같은 조건을 확인하고 조건에 따라 처리한다.
    x : deque에 들어갈 원소(idx와 val로 구성)
    front : deque 가장 앞에 위치한 원소
    last : deque 가장  뒤에 위치한 원소
    x를 덱에 넣을 시
    1.만약, x.idx - front.idx == L이면, front를 제거
    2. 반복(덱이 not empty && last.val < x.val) last 제거
    3. x를 덱에 추가(last 다음에 들어간다)
 */

public class Main3 {
    static int N, L;
    static Deque<int[]> deque;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        deque = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        int val;
        for (int i = 0; i < N; i++) {
            val = Integer.parseInt(st.nextToken());
            // i==0일 때 제외 덱에는 항상 한 개 이상의 원소가 유지된다.
            if(i != 0 && i - deque.peekFirst()[0] == L) deque.removeFirst();
            while (!deque.isEmpty() && deque.peekLast()[1] >= val) {
                deque.removeLast();
            }
            deque.add(new int[]{i, val});
            sb.append(deque.peekFirst()[1]).append(' ');
        }

        System.out.println(sb);
        br.close();
    }
}
