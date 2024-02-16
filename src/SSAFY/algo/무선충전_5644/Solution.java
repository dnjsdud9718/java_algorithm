package SSAFY.algo.무선충전_5644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int M, A, ans;
    static int[][] map = new int[11][11];
    static int[] pathA;
    static int[] pathB;
    static BC[] bcArray;
    static int ay, ax, by, bx;
    static int[] dy = {0, -1, 0, 1, 0};
    static int[] dx = {0, 0, 1, 0, -1};
    static StringBuilder sb = new StringBuilder();
    static class BC{
        int y,x,c, p;

        public BC(int y, int x, int c, int p) {
            this.y = y;
            this.x = x;
            this.c = c;
            this.p = p;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int ts = 1; ts <= T; ts++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            pathA = new int[M];
            pathB = new int[M];
            bcArray = new BC[A];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) pathA[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) pathB[i] = Integer.parseInt(st.nextToken());

            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                bcArray[i] = new BC(y, x, c, p);
            }

            // 풀이
            // 초기화
            ans = 0;
            ay = ax = 1;
            by = bx = 10;
            // charge -> 시작 위치에서 충전해줘야 한다.
            charge();

            for (int i = 0; i < M; i++) {
                ay += dy[pathA[i]];
                ax += dx[pathA[i]];
                by += dy[pathB[i]];
                bx += dx[pathB[i]];
                // charge
                charge();
            }
            sb.append("#").append(ts).append(" ").append(ans).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
    static void charge() {
        int max = 0;
        for (int i = 0; i < A; i++) {
            for (int j = 0; j < A; j++) {
                int sum = 0;
                int aPower = getPower(bcArray[i], ay, ax);
                int bPower = getPower(bcArray[j], by, bx);
                if(aPower == 0 && bPower == 0) continue;

                if (i != j) {
                    sum = aPower + bPower;
                } else { // 같은 충전소
                    // 둘 다 충전 <- 충전(K) : a(K/2) b(K/2)
                    // 한쪽만 충전(한 쪽만 범위에 covered) <- 충전(K) : a(K) b(0) or a(0) b(K)
                    // Why max() -> 한명만 방문했을 경우
                    sum = Math.max(aPower, bPower);
                }

                max = Math.max(max, sum);
            }
        }
        ans += max;
    }
    static int getPower(BC bc, int y, int x) {
        if (Math.abs(bc.y - y) + Math.abs(bc.x - x) <= bc.c) {
            return bc.p;
        }else return 0;
    }
}
