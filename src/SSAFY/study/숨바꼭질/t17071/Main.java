package SSAFY.study.숨바꼭질.t17071;

import java.util.*;

public class Main {
    static final int MAX_RANGE = 500_001;
    static Scanner sc = new Scanner(System.in);
    static int N, K, answer;
    static int[] visited;
    static PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
        if(o1[1] == o2[1] ) return o1[0] - o2[0];
        return o1[1] - o2[1];
    });
    public static void main(String[] args) {
        N = sc.nextInt();
        K = sc.nextInt();
        if (N == K) {
            System.out.println(0);
            return;
        }
        visited = new int[MAX_RANGE];
        // new int[]{수민이 위치, 현재 시간, 동생 위치}
        queue.add(new int[]{N, 0, K});
        Arrays.fill(visited, -1);
        visited[N] = 0;
        answer = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            visited[cur[0]] = cur[1];

//            System.out.println(Arrays.toString(cur));
            if(cur[2] >= MAX_RANGE || cur[1] >= MAX_RANGE) continue;
            if (cur[0] == cur[2]) { // 수민이와 동생의 위치가 같다.
                answer = cur[1];
                break;
            }
//            System.out.println("ddd  " + cur[2]);
            if (visited[cur[2]] != -1) { // 동생이 수민이가 방문했던 위치에 도착했다.
//                for (int i = 0; i < 30; i++) {
//                    System.out.printf("%d ", visited[i]);
//                }
//                System.out.println(Arrays.toString(cur));

//                System.out.print("hello ");
//                System.out.println(cur[1] + " " + visited[cur[2]]);
//
//                if ((cur[1]%2 == 0 && visited[cur[2]]%2==0) || (cur[1]%2 != 0 && visited[cur[2]]%2!=0)) {
//                    answer = cur[1];
//                    break;
//                }
                answer = cur[1];
                break;
            }

            if (cur[0] >= 1 && visited[cur[0] - 1] == -1) {
                queue.add(new int[]{cur[0] - 1, cur[1] + 1, cur[2] + cur[1] + 1});
            }
            if (cur[0] < MAX_RANGE-1 && visited[cur[0] + 1] == -1) {
                queue.add(new int[]{cur[0] + 1, cur[1] + 1, cur[2] + cur[1] + 1});
            }
            if (cur[0] * 2 < MAX_RANGE && visited[cur[0] * 2] == -1) {
                queue.add(new int[]{cur[0] * 2, cur[1] + 1, cur[2] + cur[1] + 1});
            }
        }
        System.out.println(answer == 0 ? -1 : answer);
        sc.close();
    }
}
