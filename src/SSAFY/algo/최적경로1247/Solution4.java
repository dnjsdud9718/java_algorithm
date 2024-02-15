package SSAFY.algo.최적경로1247;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    적용해 볼 것
    방문 처리 -> 배열
    가지치기 -> sum 전달
    메모이제이션 -> 비용을 미리 계산 해두자 -> 비용을 중복하여 계산하는 것은 비효율적이다. cost(A -> B) == cost(B -> A)
 */
public class Solution4 {
    static int ey, ex;
    static int N;
    static int[] y;
    static int[] x;
    static boolean[] visited;
    static int[][] dp;
    static int min;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            y = new int[N + 2];
            x = new int[N + 2];
            visited = new boolean[N + 1];
            st = new StringTokenizer(br.readLine());
            x[0] = Integer.parseInt(st.nextToken());
            y[0] = Integer.parseInt(st.nextToken());
            x[N + 1] = Integer.parseInt(st.nextToken());
            y[N + 1] = Integer.parseInt(st.nextToken());
            for (int i = 1; i <= N; i++) {
                x[i] = Integer.parseInt(st.nextToken());
                y[i] = Integer.parseInt(st.nextToken());
            }

            // Memoization
            dp = new int[N+2][N+2];
            for (int i = 0; i < N+1; i++) {
                for (int j = i + 1; j < N + 2; j++) {
                    int dist = Math.abs(y[i] - y[j]) + Math.abs(x[i] - x[j]);
                    dp[i][j] = dp[j][i] = dist;
                }
            }
            min = Integer.MAX_VALUE;
            dfs(0, 0, 0);
            sb.append("#").append(t).append(" ").append(min).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    // 주의 -> 비트 마스킹을 매개변수로 넘겨 준다 -> 해제를 안해줘도 된다. 함수 호출할 때 0이 아닌 (1<<0) or 1 을 넘겨줘야 한다.
    public static void dfs(int idx, int cnt, int sum) {
        if (cnt == N) {
            min = Math.min(min, sum + dp[idx][N + 1]);
            return;
        }

        for (int i = 1; i <= N; i++) {
            if(visited[i]) continue;
            if(sum+dp[idx][i] >= min) continue;
            visited[i] = true;
            dfs(i, cnt+1,sum + dp[idx][i]);
            visited[i] = false;
        }
    }
}
