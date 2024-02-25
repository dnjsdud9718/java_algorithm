package SSAFY.study.week5.t2098;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    외판원 순회 문제(TSP)
    어느 한 도시에서 출발해 N개의 도시를 모두 거쳐 다시 원래의 도시로 돌아오는 순회 여행 경로를 계획.
    단, 한 번 갔던 도시로는 다시 갈 수 없다(매 마지막에 여행을 출발했던 도시로 복귀하는 경우 제외)
    비용이 가장 적은 여행 계획 세우기

    모든 경우를 고려 -> O((N-1)!)
    Why O((N-1)!) -> 외판원이 모든 도시를 방문한 다음에 처음 도시로 돌아오기 때문에,
    어느 도시에서 순회를 시작하는지는 중요하지 않다. 따라서 시작 도시를 1번 도시로 가정한다.
    16
    0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0
    0 0 1 1 1 1 1 1 1 1 1 1 1 1 1 1
    1 1 0 1 1 1 1 1 1 1 1 1 1 1 1 1
    0 1 1 0 1 1 1 1 1 1 1 1 1 1 1 1
    0 1 1 1 0 1 1 1 1 1 1 1 1 1 1 1
    0 1 1 1 1 0 1 1 1 1 1 1 1 1 1 1
    0 1 1 1 1 1 0 1 1 1 1 1 1 1 1 1
    0 1 1 1 1 1 1 0 1 1 1 1 1 1 1 1
    0 1 1 1 1 1 1 1 0 1 1 1 1 1 1 1
    0 1 1 1 1 1 1 1 1 0 1 1 1 1 1 1
    0 1 1 1 1 1 1 1 1 1 0 1 1 1 1 1
    0 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1
    0 1 1 1 1 1 1 1 1 1 1 1 0 1 1 1
    0 1 1 1 1 1 1 1 1 1 1 1 1 0 1 1
    0 1 1 1 1 1 1 1 1 1 1 1 1 1 0 1
    0 1 1 1 1 1 1 1 1 1 1 1 1 1 1 0
 */
public class DP {
    static int N;
    static int[][] W;
    static int[][] DP;
    static long answer;
    static final int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
//            System.out.println(Arrays.toString(W[i]));
        }
        DP = new int[N][(1 << N)];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < (1 << N); j++) {
                // 방문하지 않은 케이스(-1)와 방문했는데 길이 없는 경우(INF)
                DP[i][j] = -1; // INF로 초기화하면 안된다!!
            }
        }
        answer = tsp(0, 1 << 0);
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(DP[i]));
        }
        System.out.println(answer);
        br.close();
    }
    /*
         중복이 발생한다.
         1->2->3->4->1
         1->2->4->3->1
         1->3->2->4->1
         1->3->4->2->1
         1->4->2->3->1
         1->4->3->2->1
         call-> tsp(1, {1})
         tsp(1, {1})
         tsp(2, {1,2})                          tsp(3, {1,3})                        tsp(4, {1,4})
         tsp(3, {1,2,3})    tsp(4, {1,2,4})     tsp(2, {1,2,3})    tsp(4, {1,3,4})   tsp(2, {1,2,4})    tsp(3, {1,3,4})
         tsp(4, {1,2,3,4})  tsp(3, {1,2,3,4})   tsp(4, {1,2,3,4})  tsp(2, {1,2,3,4}) tsp(3, {1,2,3,4})  tsp(2, {1,2,3,4})
         중복 발생 -> tsp(4, {1,2,3,4}), tsp(3, {1,2,3,4}), tsp(2, {1,2,3,4}) <- 비효율적이다.

         파라미터에 따른 결과를 저장해 둘 필요가 있구나 -> 메모이제이션
     */
    public static int tsp(int here, int check) {
        if (check == (1 << N) - 1) {
            return W[here][0] == 0 ? INF : W[here][0]; // 시작 도시로 복귀
        }
        // Memoization
        // 이미 한 번 계산된 적 있는 입력이 들어오면 미리 저장해 둔 결과를 반환.
        // INF로 초기화하면 안되는 이유 -> 방문 전 INF vs. 방문 후 경로 없어서 INF 구분할 수 있나?
        // 방문했는데 경로가 없어서 INF이고 이때 계속 tsp() 호출한다면 DP의 효과를 볼 수 없다.
        if(DP[here][check] != -1) return DP[here][check];
        DP[here][check] = INF;
        for (int i = 0; i < N; i++) {
            if ((check & (1 << i)) != 0 || W[here][i] == 0) continue;
            int cost = W[here][i] + tsp(i, check | (1 << i));
            DP[here][check] = Math.min(DP[here][check], cost);
        }
        return DP[here][check];
    }
}

