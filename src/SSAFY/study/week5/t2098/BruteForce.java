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
public class BruteForce {
    static int N;
    static int[][] W;
    static long answer;
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
        answer = tsp(0, 1 << 0);
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
     */
    public static int tsp(int here, int check) {
        if (check == (1 << N) - 1) {
            return W[here][0]; // 시작 도시로 복귀
        }
        // 이미 방문했다면 -> 저장한 값을 준다.(메모이제이션)
        int answer = 987654321; // Integer.MAX_VALUE -> X -> 오버플로 발생 가능
        for (int i = 0; i < N; i++) {
            if ((check & (1 << i)) != 0) continue;
            int cost = W[here][i] + tsp(i, check | (1 << i));
            answer = Math.min(answer, cost);
        }
        return answer;
    }
}
