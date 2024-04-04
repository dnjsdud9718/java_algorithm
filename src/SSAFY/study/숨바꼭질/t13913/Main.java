package SSAFY.study.숨바꼭질.t13913;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    static final int INF = 100500;
    static int N, K;
    static boolean[] visited;
    static Deque<Integer> path;
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        N = sc.nextInt();
        K = sc.nextInt();

        if (N >= K) {
            sb.append(N - K).append("\n");
            for (int i = N; i >= K; i--) {
                sb.append(i).append(" ");
            }
        } else {
            int answer = 0;
            visited = new boolean[INF];
            Deque<Point> queue = new ArrayDeque<>();
            queue.add(new Point(K, 0));
            while (!queue.isEmpty()) {
                Point cur = queue.poll();
                if (cur.x == N) {
                    answer = cur.cnt;
                    path = cur.path;
                    break;
                }
                if (cur.x > 0 && !visited[cur.x - 1]) {
                    visited[cur.x - 1] = true;
                    queue.add(new Point(cur.x - 1, cur.cnt + 1, cur.path));
                }
                if (cur.x < INF-100 && !visited[cur.x + 1]) {
                    visited[cur.x + 1] = true;
                    queue.add(new Point(cur.x + 1, cur.cnt + 1, cur.path));
                }
                if (cur.x % 2 == 0 && !visited[cur.x / 2]) {
                    visited[cur.x / 2] = true;
                    queue.add(new Point(cur.x / 2, cur.cnt + 1, cur.path));
                }
            }
            sb.append(answer).append("\n");
            while (!path.isEmpty()) {
                sb.append(path.pop()).append(" ");
            }
        }
        System.out.println(sb);
    }

    static class Point {
        int x, cnt;
        Deque<Integer> path;

        public Point(int x, int cnt) {
            this.x = x;
            this.cnt = cnt;
            this.path = new ArrayDeque<>();
            path.push(x);
        }

        public Point(int x, int cnt, Deque<Integer> path) {
            this.x = x;
            this.cnt = cnt;
            this.path = new ArrayDeque<>(path);
            this.path.push(x);
        }
    }
}
