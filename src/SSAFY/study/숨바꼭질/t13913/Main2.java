package SSAFY.study.숨바꼭질.t13913;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class Main2 {
    static final int INF = 100500;
    static int N, K;
    static int[] visited;
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
            visited = new int[INF];
            Arrays.fill(visited, -1);
            visited[K] = 0;

            Deque<Point> queue = new ArrayDeque<>();
            queue.add(new Point(K, 0));
            while (!queue.isEmpty()) {
                Point cur = queue.poll();
                if (cur.x == N) {
                    answer = cur.cnt;
                    break;
                }
                /*
                   방문 처리가 된다는 것은 가장 빠른 시간에 해당 위치에 도달했다는 것이다.
                 */
                if (cur.x > 0 && visited[cur.x - 1] == -1) {
                    visited[cur.x - 1] = cur.x;
                    queue.add(new Point(cur.x - 1, cur.cnt + 1));
                }
                if (cur.x < INF-100 && visited[cur.x + 1] == -1) {
                    visited[cur.x + 1] = cur.x;
                    queue.add(new Point(cur.x + 1, cur.cnt + 1));
                }
                if (cur.x % 2 == 0 && visited[cur.x / 2] == -1) {
                    visited[cur.x / 2] = cur.x;
                    queue.add(new Point(cur.x / 2, cur.cnt + 1));
                }
            }
            sb.append(answer).append("\n");
            while (K != N) {
                sb.append(N).append(" ");
                N = visited[N];
            }
            sb.append(K);
        }
        System.out.println(sb);
    }

    static class Point {
        int x, cnt;

        public Point(int x, int cnt) {
            this.x = x;
            this.cnt = cnt;
        }
    }
}
