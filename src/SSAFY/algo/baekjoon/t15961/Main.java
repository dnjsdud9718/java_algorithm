package SSAFY.algo.baekjoon.t15961;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
    15961 회전초밥
    슬라이드 윈도우 -> DEQUE
    방문 처리
 */
public class Main {
    static int N, d, k, c;
    static int ans, prev;
    static int[] src;
    static int[] v = new int[3000300];
    static int left;
    static Deque<Integer> queue = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        src = new int[N];
        for (int i = 0; i < N; i++) {
            src[i] = Integer.parseInt(br.readLine());
        }
        // intialize;
        ans = 0;
        for (int i = 0; i < k; i++) {
            queue.add(src[i]);
            if(v[src[i]] == 0) ans++;
            v[src[i]]++;
        }
        if(v[c]==0) ans++;
        // ★★★ 직전상태를 유지하고 있어야 한다.★★★
        prev = ans;
        left = 1;
        do {
            check();
            left = (left+1)%N;
        } while (left != 0);

        System.out.println(ans);
        br.close();
    }
    public static void check() {
//        System.out.println(left+ " " + src[(left+k-1)%N]);
        if(v[c] == 0) prev--; // 쿠폰에 대해 중복적으로 카운트할 수도 있기 때문에
        int front = queue.poll();
        v[front]--;
        if(v[front] == 0) prev--;
        int rear = src[(left + k - 1) % N];
        queue.offer(rear);
        if(v[rear] == 0) prev++;
        v[rear]++;
        if(v[c] == 0) prev++;
        ans = Math.max(ans, prev);
//        System.out.println(front+ " " + rear);
    }
}
