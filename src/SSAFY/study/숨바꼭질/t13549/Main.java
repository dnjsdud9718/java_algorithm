package SSAFY.study.숨바꼭질.t13549;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;
/*
    baekjoon 13549
    숨바꼭질 3
    수빈이는 다빈이를 찾기 위해 현재 위치 X에서 X+1, X+2을 1초에 이동 가능하고, X*2칸을 0초에 이동 가능.
    수빈이가 다빈이를 가장 빠른 시간 안에 찾았을 때의 시간을 출력
    그래프의 간선의 가중치가 0 또는 1인 그래프로 생각할 수 있다.
    다익스트라 가능하지만 0/1 BFS가 O(V+E)로 더 빠르다.

 */
public class Main {
    static final int INF = 987_654_321;
    static int N, K;
    static int[] weights;
    static Deque<Integer> queue = new ArrayDeque<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        if (K <= N) {
            System.out.println(N - K);
            return;
        }
        weights = new int[K + 2];
        Arrays.fill(weights, INF);

        weights[N] = 0;
        queue.add(N);
        int answer =  0;
        // 0/1 BFS 간선의 가중치가 0 또는 1로만 구성된 BFS
        while (!queue.isEmpty()) {
            int cur = queue.removeFirst();
            if(cur == K) {
                answer = weights[cur];
                break;
            }
            if (cur >= 1 && weights[cur] + 1 < weights[cur - 1]) {
                weights[cur - 1] = weights[cur] + 1;
                queue.addLast(cur - 1);
            }
            if (cur < K && weights[cur] + 1 < weights[cur + 1]) {
                weights[cur + 1] = weights[cur] + 1;
                queue.addLast(cur + 1);
            }
            if (Math.round(1.0 * K / 2) >= cur && weights[cur] < weights[cur*2]) {
                weights[cur * 2] = weights[cur];
                queue.addFirst(cur * 2);
            }

        }
//        System.out.println(Arrays.toString(weights));
        System.out.println(answer);
        sc.close();
    }
}
