package SSAFY.study.숨바꼭질.t12851;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/*
    숨바꼭질2
    1. 중복 처리에 대한 고민 -> 언제 방문 체크할 것인가?
    2. 최단 시간 계산
 */
public class Main {
    static int N, K;
    static boolean[] visited;
    static int time, cases;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        N = sc.nextInt();
        K = sc.nextInt();
        if (K <= N) { // 수민이가 동생의 위치보다 크거나 같은 경우.
            System.out.println(N - K);
            System.out.println(1);
            return;
        }

        time = Integer.MAX_VALUE; // 최단 시간 저장 변수
        cases = 0; // 경우의 수 저장 변수


        // 수민이가 움직이는 것이 아니라 동생이 움직이게 작성했음.
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{K, 0});

        visited = new boolean[100_001];


        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            /*
                일반적인 bfs에서는 방문처리를 큐에 삽입하는 시점에 진행하지만,
                여기서는 큐에서 나올 때 진행.

                수민이가 동생한테 가는 최단거리 경로에 x가 포함되어 있고, x에서 동생한테 가는 최단 경로는 1개이고,
                수민이가 x한테 가는 최단 경로는 3개라면, 경우의 수는 3이다. x를 큐에 삽입될 때 방문처리하면 나머지
                2개 경로가 큐에 들어올 수 없기 때문에 큐에 나 삽입하고 나올 때 방문처리해줘야 한다.

                동일시간에 방문할 수 있는 위치를 모두 큐에 넣어야 하기 때문에 큐에서 나왔을 때 방문처리 진행.
                1에서 2로 가는 경우 1*2 = 2, 1+1 = 2가 있는데, 이를 큐에 삽입하는 시점에 방문처리해버리면
                하나의 경우는 고려할 수가 없다.
             */
            visited[cur[0]] = true;
            if (time < cur[1]) { // 현재 진행 중인 시간이 최단 시간보다 클 경우 continue;
                continue;
            }

            if (cur[0] == N) { // 동생이 수민이를 찾았다.
                if (cases == 0) { // 처음 찾았다.
                    time = cur[1]; // 최단 시간 결정.
                    cases++;
                } else if (cases > 0 && time == cur[1]) {
                    cases++;
                }
            }

            if (cur[0] > 0 && !visited[cur[0] - 1]) { // -1칸 이동
                queue.add(new int[]{cur[0] - 1, cur[1] + 1});
            }
            if (cur[0] < 100_000 && !visited[cur[0] + 1]) { // +1칸 이동
                queue.add(new int[]{cur[0] + 1, cur[1] + 1});
            }
            if (cur[0] % 2 == 0 && !visited[cur[0] / 2]) { // /2칸 이동
                queue.add(new int[]{cur[0] / 2, cur[1] + 1});
            }
        }
        System.out.println(time);
        System.out.println(cases);
    }
}
