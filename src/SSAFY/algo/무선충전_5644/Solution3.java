
package SSAFY.algo.무선충전_5644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Solution3 {
    static int M, A, ans;
    static int ay, ax, by, bx;
    static int[] pathA;
    static int[] pathB;
    static BC[] bc;
    static int[] dy = {0, -1, 0, 1, 0};
    static int[] dx = {0, 0, 1, 0, -1};
    static int[][][] dp;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            bc = new BC[A];
            pathA = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            pathB = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                bc[i] = new BC(y, x, c, p);
            }
            //memoization
            dp = new int[11][11][A];
            for (int i = 1; i <= 10; i++) {
                for (int j = 1; j <= 10; j++) {
                    for (int k = 0; k < A; k++) {
                        dp[i][j][k] = getPower(i, j, bc[k]); // (i, j)좌표에 위치 했을 때, 충전소 k에서 충전 가능한 값 저장.
                    }
                }
            }
//            for (int k = 0; k < A; k++) {
//                for (int i = 1; i <= 10; i++) {
//                    for (int j = 1; j <= 10; j++) {
//                        System.out.print(dp[i][j][k]+"  ");
//                    }
//                    System.out.println();
//                }
//                System.out.println("+++++++++++++++++++++++++++++++++++++++++");
//            }

            ay= ax = 1; // 플레이어 A 시작점
            by = bx = 10; // 플레이어 B 시작점
            ans = 0;
            // 사용자의 초기 위치(0초)부터 충전을 할 수 있다.
            charge();
            for (int i = 0; i < M; i++) { // 매 이동 시마다 값 갱신.
                ay = ay + dy[pathA[i]];
                ax = ax + dx[pathA[i]];
                by = by + dy[pathB[i]];
                bx = bx + dx[pathB[i]];
                charge();
            }
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    public static void charge() {
        int max = 0;
        for (int i = 0; i < A; i++) {; // player a가 선택한 충전소
            for (int j = 0; j < A; j++) { // player b가 선택한 충전소
                int sum = 0;
//                int a = getPower(ay, ax, bc[i]);
//                int b = getPower(by, bx, bc[j]);
                int a = dp[ay][ax][i];
                int b = dp[by][bx][j];
                if(a == 0 && b == 0) continue;
                if (i != j) {
                    sum = a + b;
                } else {
                    // 이 부분이 핵심인 것 같다.
                    // 둘 다 충전 <- 충전(K) : a(K/2) b(K/2)
                    // 한쪽만 충전(한 쪽만 범위에 covered) <- 충전(K) : a(K) b(0) or a(0) b(K)
                    // Why max() -> 한명만 방문했을 경우
                    // sum = a; -> No -> 충전소 같지만 playerA만 방문 또는 playerB만 방문 가능
                    // a == 0, b > 0 클 수 있다.
                    sum = Math.max(a, b); //
                }
                max = Math.max(max, sum);
            }
        }
        ans += max;
    }
    public static int getPower(int y, int x, BC bc) {
        return Math.abs(bc.y - y) + Math.abs(bc.x - x) <= bc.c ? bc.p : 0;
    }
    static class BC {
        int y, x, c, p;
        public BC(int y, int x, int c, int p) {
            this.y = y;
            this.x = x;
            this.c = c;
            this.p = p;
        }
    }
}

