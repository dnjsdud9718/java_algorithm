package SSAFY.study.week16.t17396;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static final long INF = 10_000_000_001L;

    static int N, M;
    static boolean[] isVisible; // similar to visited
    static List<Node>[] list;
    static long[] dist;
    static PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
        if(o1.w - o2. w == 0) return 0;
        if (o1.w - o2.w > 0)  return 1;
        return -1;
    });
    static long answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 분기점
        M = Integer.parseInt(st.nextToken()); // 분기점 사이의 이동 시간
        list = new List[N];
        isVisible = new boolean[N];
        dist = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int v = Integer.parseInt(st.nextToken());
            if (v == 1) {
                isVisible[i] = true;
            }
            list[i] = new ArrayList<>();
            dist[i] = INF;
        }
        isVisible[N - 1] = false;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if (isVisible[u] || isVisible[v]) {
                continue;
            }
            list[u].add(new Node(v, w));
            list[v].add(new Node(u, w));
        }
        answer = 0;
        if (isVisible[0]) {
            answer = -1;
        } else {
            dist[0] = 0;
            pq.add(new Node(0, 0));
            while (!pq.isEmpty()) {
                Node cur = pq.poll();
                if (isVisible[cur.v]) {
                    continue;
                }
                isVisible[cur.v] = true;
                for (Node next : list[cur.v]) {
                    if (!isVisible[next.v] && cur.w + next.w <= dist[next.v] ) {
                        dist[next.v] = cur.w + next.w;
                        pq.add(new Node(next.v, dist[next.v]));
                    }
                }
            }
            answer = dist[N - 1];
        }
//        System.out.println(Arrays.toString(dist));
        System.out.println(answer != INF ? answer : -1);

        br.close();
    }

    static class Node {

        private int v;
        private long w;

        public Node(int v, long w) {
            this.v = v;
            this.w = w;
        }

    }
}
