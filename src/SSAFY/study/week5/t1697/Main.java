package SSAFY.study.week5.t1697;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    static int N, M;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        Deque<int[]> queue = new ArrayDeque<>();
        visited = new boolean[1000000];
        queue.add(new int[]{M, 0});
        visited[M] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cVal = cur[0];
            int cCnt = cur[1];
            if (cVal == N) {
                System.out.println(cCnt);
                break;
            }
            if (cVal - 1 >= 0 && !visited[cVal - 1]) {
                visited[cVal-1] = true;
                queue.add(new int[]{cVal - 1, cCnt + 1});
            }
            if (!visited[cVal + 1]) {
                visited[cVal+1] = true;
                queue.add(new int[]{cVal + 1, cCnt + 1});
            }
            if (cVal % 2 == 0 && !visited[cVal / 2]) {
                visited[cVal/2] = true;
                queue.add(new int[]{cVal / 2, cCnt + 1});
            }

        }

    }
}
