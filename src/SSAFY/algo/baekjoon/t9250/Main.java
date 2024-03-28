package SSAFY.algo.baekjoon.t9250;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987_654_321;
    static int T, N;
    static Point[] points;
    static int[][] dist;
    static boolean[] visited;
    static Deque<Integer> queue = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();
    static String answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            points = new Point[N + 2];
            for (int i = 0; i < N + 2; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                points[i] = new Point(y, x);
            }
            dist = new int[N + 2][N + 2];
            // 거리 계산
            for (int i = 0; i < N+1; i++) {
                Point p1 = points[i];
                for (int j = i + 1; j < N+2; j++) {
                    Point p2 = points[j];
                    int d =  Math.abs(p1.y - p2.y) + Math.abs(p1.x - p2.x);
                    if((d+49)/50 > 20) dist[i][j] = dist[j][i] = INF;
                    else dist[i][j] = dist[j][i] = d;
                }
            }
            // bfs
            answer = "sad";
            queue.clear();
            queue.add(0);
            visited = new boolean[N + 2];
            visited[0] = true;
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                if (cur == N + 1) {
                    answer = "happy";
                    break;
                }
                for (int i = 0; i < N + 2; i++) {
                    if(visited[i] || cur == i || dist[cur][i] >= INF) continue;
                    visited[i] = true;
                    queue.add(i);
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
