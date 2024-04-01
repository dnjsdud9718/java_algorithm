package SSAFY.study.week11.t12850;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    static int[][] map = {
            {0, 1, 1, 0, 0, 0, 0, 0},
            {1, 0, 1, 1, 0, 0, 0, 0},
            {1, 1, 0, 1, 1, 0, 0, 0},
            {0, 1, 1, 0, 1, 1, 0, 0},
            {0, 0, 1, 1, 0, 1, 1, 0},
            {0, 0, 0, 1, 1, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 1},
            {0, 0, 0, 0, 0, 1, 1, 0},
    };
    static int D, answer;
    static final int MOD = 1_000_000_007;
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        D = sc.nextInt();
        answer = 0;
        bfs();
        System.out.println(answer%MOD);
        sc.close();
    }

    static void bfs() {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
//            System.out.println(Arrays.toString(cur));
            if (cur[1] >= D) {
                if(cur[0] == 0 && cur[1] == D) answer++;
                continue;
            }
            for (int i = 0; i < map.length; i++) {
                if(map[cur[0]][i] == 0) continue;
                queue.add(new int[]{i, cur[1] + 1});
            }
        }
    }
}

