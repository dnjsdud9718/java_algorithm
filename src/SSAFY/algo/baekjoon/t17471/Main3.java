package SSAFY.algo.baekjoon.t17471;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main3 {
    static final int INF = 987654321;
    static int N, min;
    static int[] population;
    static List<Integer>[] list;
    static Deque<Integer> queue = new ArrayDeque<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        population = new int[N + 1];
        list = new List[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
            list[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            for (int j = 0; j < K; j++) {
                int v = Integer.parseInt(st.nextToken());
                list[i].add(v);
            }
        }
        min =INF;
        subset(0, 0);
        System.out.println(min == INF ? -1 : min);
        br.close();
    }

    static void logic(int A) {
        int B = A ^ ((1 << N) - 1);
        queue.clear();
        visited = new boolean[N + 1];
        for (int i = 0; i < N; i++) {
            if ((A & (1 << i)) != 0) {
                visited[i+1] = true;
                queue.add(i + 1);
                break;
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : list[cur]) {
                if ((A & (1 << (next - 1))) != 0 && !visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        for (int i = 0; i < N; i++) {
            if ((B & (1 << i)) != 0) {
                visited[i+1] = true;
                queue.add(i + 1);
                break;
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : list[cur]) {
                if ((B & (1 << (next - 1))) != 0 && !visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        for(int i=1; i<=N; i++) if(!visited[i]) return;
        // 계산
        calc(A, B);
    }

    static void calc(int A, int B) {
        int sumA = 0;
        int sumB = 0;
        for (int i = 0; i < N; i++) {
            if ((A & (1 << i)) != 0) {
                sumA += population[i+1];
            } else {
                sumB += population[i + 1];
            }
        }
        min = Math.min(min, Math.abs(sumA - sumB));
    }
    static void subset(int idx, int check) {
        if (idx == N) {
            if(check == 0 || check == (1<<N)-1) return;
            logic(check);
            return;
        }
        subset(idx + 1, check | (1<<idx));
        subset(idx + 1, check);
    }
}
