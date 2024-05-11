package SSAFY.study.week16.t16928;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] teleport = new int[101];
    static boolean[] visited = new boolean[101];
    static Deque<int[]> queue = new ArrayDeque<>();

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            teleport[x] = y;
        }
        int answer = 0;
        if (teleport[1] != 0) {
            queue.add(new int[]{teleport[1], 0});
        } else {
            queue.add(new int[]{1, 0});
        }
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            // System.out.println(Arrays.toString(cur));
            if (cur[0] == 100) {
                answer = cur[1];
                break;
            }
            for (int i = 1; i <= 6; i++) {
                if (cur[0] + i > 100) {
                    continue;
                }
                if (teleport[cur[0] + i] != 0 && visited[teleport[cur[0] + i]]) {
                    continue;
                }
                if (!visited[teleport[cur[0] + i]]) {
                    visited[teleport[cur[0] + i]] = true;
                    queue.add(new int[]{teleport[cur[0] + i], cur[1] + 1});
                } else if (!visited[cur[0] + i]) {
                    visited[cur[0] + i] = true;
                    queue.add(new int[]{cur[0] + i, cur[1] + 1});
                }
            }

        }
        System.out.println(answer);
        br.close();
    }

}
