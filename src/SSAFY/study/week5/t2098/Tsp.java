package SSAFY.study.week5.t2098;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    bootom - up
    cost
        0   1   2   3   4
    0   0   0   0   0   0
    1   0   0   10  15  20
    2   0   5   0   9   10
    3   0   6   13  0   12
    4   0   8   8   9   0
    시작점 1
    g(i, s) -> i에서 s집합에 속한 도시를 모두 방문하고 1로 가는 최단 거리값.
    g(i, s) -> min(cost[i][j] + g(j, s-{j})),  (j ∈ s) (1 ∉ s)
    위 cost예시에서 구하고자 하는 값
    g(1, {2,3,4}) <- min(cost[1][k] + g(k, {2,3,4}-{k})), (k ∈ {2,3,4})
    g(1, {2,3,4}) <- cost[1][2] + g(2, {3,4})  -> cost[2][3] + g(3, {4}) <- cost[3][4] + g(4,{})
                                               <- cost[2][4] + g(4, {3}) <- cost[4][3] + g(3,{})
                  -> cost[1][3] + g(3, {2,4})  -> cost[3][2] + g(2, {4}) <- cost[2][4] + g(4,{})
                                               <- cost[3][4] + g(4, {2}) <- cost[4][2] + g(2,{})
                  -> cost[1][4] + g(4, {2,3})  <- cost[4][2] + g(2, {3}) <- cost[2][3] + g(3,{})
                                               -> cost[4][3] + g(3, {2}) <- cost[3][2] + g(2,{})
    N <- 4
    cost[N+1][N+1]
    dp[N+1][(1<<(N-1))]
    why (1<<(N-1))? 항상 1에서 시작해도 상관없다(어디에서 시작해도 상관 없다)
                    비트마스킹으로 집합을 표현 -> 도시번호는 1번부터 N번
                    최종적으로 구하고 싶은 것 -> g(1, {2,3,4})
                    0번 비트 - 2번 도시, 1번 비트 - 3번 도시, 2번 비트 - 4번도시 매핑.
                    따라서 3개도시에 대한 부분집합이 필요 -> 1<<(N-1)
    1. bottom-up이기 때문에 집합의 크기가 1인 것부터 계산을 시작. k : 1 ~ N-2
    2. 집합의 크기가 k일 때 가능한 집합을 선택 s : 0 ~ 1<<(N-1), |s| == k인 s를 선택한다 -> count(s) 메서드
    3. 도시 선택 -> s에 속하지 않은 도시 i: 2 ~ N, i가 s에 속하는 지 판단 -> isIn(i, s) 메서드
    4. g(i,s) 계산 -> minimum(i, s) 메서드

 */
public class Tsp {
    static int N;
    static int[][] cost;
    static long[][] dp;
    static final int INF = 987_654_321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        cost = new int[N + 1][N + 1];
        dp = new long[N + 1][1 << (N - 1)];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // tsp
        for (int i = 1; i <= N; i++) {
            dp[i][0] = cost[i][1]; // i에서 아무 도시도 거치지 않고 시작 도시(1)로 가는 거리. g(i, {})
        }
        for (int k = 1; k <= N - 2; k++) { // |s| 범위
            for (int s = 1; s < (1 << (N - 1)); s++) { // 가능한 s
                if (count(s) == k) {  // |s| == k
                    for (int i = 2; i <= N; i++) {
                        if (!isIn(i, s)) { // g(i, s) 조건 -> i ∉ s
                            dp[i][s] = minimum(i, s);
                        }
                    }
                }
            }
        }
        // g(1, {2,3,4}) -> 1번 도시에서 모든 도시를 방문하고 다시 1번으로 돌아오는 최단 거리값 계산.
        dp[1][(1 << (N - 1)) - 1] = minimum(1, (1 << (N - 1)) - 1);
        System.out.println(dp[1][(1 << (N - 1)) - 1]);
        br.close();
    }

    // g(i, s) -> i에서 출발하여 s에 속한 모든 도시를 방문하고 1번 도시로 가는 최단 거리값 계산 메서드
    public static long minimum(int i, int s) {
        long min = INF;
        for (int j = 2; j <= N; j++) {
            if (isIn(j, s) && cost[i][j] != 0 && dp[j][s ^ (1 << (j-2))] != 0) {
                long m = cost[i][j] + dp[j][s ^ (1 << (j-2))];
                min = Math.min(min, m);
            }
        }
        return min;
    }

    // 집합 s에 i도시가 속해있는 지 확인하는 메서드
    public static boolean isIn(int i, int s) {
        return (s & (1 << (i-2))) != 0;
    }
    // s집합에 속한 도시 수 계산 메서드
    public static int count(int s) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if ((s & (1 << i)) != 0) cnt++;
        }
        return cnt;
    }
}
