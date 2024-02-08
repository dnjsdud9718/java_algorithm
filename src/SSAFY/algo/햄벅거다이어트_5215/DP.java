package SSAFY.algo.햄벅거다이어트_5215;

// 부분 집합 문제
// DP로 해결 가능하다
// K-napsack과 같다!
// 이 문제는 부분집합(재귀)로 해결 가능하나 K-napsack은 2^N(1<=N<=100)이기 때문에 감당이 안된다.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP {
    static int N, L;
    static int[][] DP;
    static int[] w;
    static int[] v;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            DP = new int[N + 1][L + 1];
            w = new int[N + 1];
            v = new int[N + 1];
            for(int i=1; i<=N; i++){
                st = new StringTokenizer(br.readLine());
                v[i] = Integer.parseInt(st.nextToken());
                w[i] = Integer.parseInt(st.nextToken());
            }
            for(int i=1; i<=N; i++){
                for (int W = 1; W <= L; W++) {
                    if (w[i] <= W) {
                        DP[i][W] = Math.max(DP[i - 1][W], DP[i - 1][W - w[i]] + v[i]); // 넣었을 때와 넣지 못하는 경우
                    } else {
                        DP[i][W] = DP[i - 1][W];
                    }
                }
            }
//            for(int i=1 ; i<=N; i++){
//                for(int j=1; j<=L; j++){
//                    System.out.print(DP[i][j]+" ");
//                }
//                System.out.println();
//            }


            sb.append("#").append(t).append(" ").append(DP[N][L]).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
}
