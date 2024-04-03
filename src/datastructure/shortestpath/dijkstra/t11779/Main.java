package datastructure.shortestpath.dijkstra.t11779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int INF = 987_654_321;
    static int N, M, START, END;
    static List<Node>[] list;
    static PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)->{
        return o1.w - o2.w;
    });
    static int[] dist;
    static int[] path;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        list = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[u].add(new Node(v, w));
        }

        st = new StringTokenizer(br.readLine());
        START = Integer.parseInt(st.nextToken());
        END = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        visited = new boolean[N + 1];
        path = new int[N + 1];
        Arrays.fill(dist, INF);
        pq.add(new Node(START, 0));
        dist[START] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (!visited[cur.v]) {
                visited[cur.v] = true;
                for (Node next : list[cur.v]) {
                    if (dist[next.v] > dist[cur.v] + next.w) {
                        dist[next.v] = dist[cur.v] + next.w;
                        pq.add(new Node(next.v, dist[next.v]));
                        path[next.v] = cur.v;
                    }
                }
            }

        }

        System.out.println(dist[END]);

        Deque<Integer> stack = new ArrayDeque<>();
        int p = END;
        while (true) {
            if (p == 0) break;
            stack.push(p);
            p = path[p];
        }
        System.out.println(stack.size());
        while (!stack.isEmpty()) {
            System.out.print(stack.pop()+" ");
        }
        br.close();
    }

    // adjacent
    static class Node {
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

}
