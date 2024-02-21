package SSAFY.study.week5.t1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, W;
    static List<Integer>[] list;
    static int[] weights;
    static int[] in;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            list = new List[N + 1];
            weights = new int[N + 1];
            in =new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                list[i] = new ArrayList<>();
                weights[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                list[u].add(v);
                in[v]++;
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
                return o1[1] - o2[1];
            });
            for(int i=1; i<=N; i++) if(in[i]==0) pq.add(new int[]{i, weights[i]});
            W = Integer.parseInt(br.readLine());
            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                if (cur[0] == W) {
                    sb.append(cur[1]).append('\n');
                    break;
                }
                for (int next : list[cur[0]]) {
                    if(in[next] == 0) continue;
                    in[next]--;
                    if (in[next] == 0) {
                        pq.add(new int[]{next, cur[1] + weights[next]});
                    }
                }
            }
        }
        System.out.println(sb);
        br.close();
    }

}
