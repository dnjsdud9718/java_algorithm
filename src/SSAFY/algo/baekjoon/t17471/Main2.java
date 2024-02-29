package SSAFY.algo.baekjoon.t17471;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/*
    1. 그룹 분리 -> 집합 S 부분집합(subset) -> 부분집합에 속한 원소들의 그룹(A), 그렇지 못한 원소들의 그룹(B) -> 공집합과 집합 S는 제외
    2. 각 그룹 별 BFS 수행
        -> A그룹 집행
            -> A에 속하는 임의의 노드를 큐에 삽입하고 BFS 수행 -> 모든 A에 속하는 원소가 방문되었다면 OK
        -> B그룹 진행
            -> B에 속하는 임의의 노드를 큐에 삽입하고 BFS 수행 -> 모든 B에 속하는 원소가 방문되었다면 OK
        -> 두 개 모두 만족해야 한다. 하나만 만족하는 경우는 안된다.
    3. 두 개 모두 만족하는 경우 -> 인구수 갱신(update)

    6
    5 2 3 4 1 2
    2 2 4           i번 도시와 인접한 도시의 수는 k이고 그 도시 들은 다음과 같다....
    4 1 3 6 5
    2 4 2
    2 1 3
    1 2
    1 2
 */
public class Main2 {
    static int N, min;
    static int[] population;
    static int[][] matrix;
    static boolean[] visited;
    static final int INF = 987_654_321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        matrix = new int[N + 1][N + 1];
        population = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= M; j++) {
                int k = Integer.parseInt(st.nextToken());
                matrix[i][k] = 1;
//                matrix[k][i] = 1;
            }
//            System.out.println(Arrays.toString(matrix[i]));
        }
        min = INF;
        subset(0, 0);
        System.out.println(min >= INF ? -1 : min);

        br.close();
    }

    public static void subset(int cnt, int check) {
        if (cnt == N) {
            if(check == 0 || check == (1<<N)-1) return;


            // 풀이
            if(!bfs(check)) return;

            int sumA = 0;
            int sumB = 0;
            for (int i = 1; i <= N; i++) {
                if ((check & (1 << (i - 1))) != 0) {
                    sumA += population[i];
                } else sumB += population[i];
            }
            min = Math.min(min, Math.abs(sumA - sumB));
            return;
        }
        subset(cnt + 1, check | (1 << cnt));
        subset(cnt + 1, check);
    }
    public static boolean bfs(int check) {
        Deque<Integer> queue = new ArrayDeque<>();
        visited = new boolean[N + 1];
        for(int i=0; i<N; i++) {
            if ((check & (1 << i)) != 0) {
                visited[i + 1] = true;
                queue.add(i + 1);
                break;
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = 1; i <= N; i++) {
                if(matrix[cur][i] == 0 || visited[i] || (check & (1<<(i-1))) == 0) continue;
                visited[i] = true;
                queue.add(i);
            }
        }
        for (int i = 0; i < N; i++) {
            if ((check & (1 << i)) == 0) {
                visited[i+1] = true;
                queue.add(i + 1);
                break;
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = 1; i <= N; i++) {
                if(matrix[cur][i] == 0 || visited[i] || (check & (1<<(i-1))) != 0) continue;
                visited[i] = true;
                queue.add(i);
            }
        }

        for (int i = 1; i <= N; i++) {
            if(!visited[i]) return false;
        }
        return true;
    }
}
