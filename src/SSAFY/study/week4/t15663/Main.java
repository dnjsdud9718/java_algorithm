package SSAFY.study.week4.t15663;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/*
    backjoon 15663 N과M (9)
    N개의 자연수와 자연수 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 출력
     조건 -> N개의 자연수 중에서 M개를 고른 수열
     수열 정의 -> 순서 있는 나열 -> 따라서, permutation 이다!
    입력 : (1<=M <= N <= 8)
    A1 A2, ... , AN( 1<= Ai <= 10,000, i=1,2,...,N)

    출력
    한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력, 중복되는 수열을 여러 번 출력하면 안되며,
    각 수열은 공백으로 구분해서 출력
    ★★★★ 수열은 사전 순으로 증가하는 순서로 출력

    핵심 -> 중복되는 수열을 한 번만 출력해야 한다.
    내 생각 -> 순열을 구하면서 출력된 직전 순열과 같은지 아닌지 비교한다. -> 안된다
    4 2
    9 7 9 1 -> 1 7 9 9
    중복되는 순열이 연속적으로 등장하지 않는다.
    [1, 7][1, 9][1, 9][7, 1][7, 9][7, 9][9, 1][9, 7][9, 9][9, 1][9, 7][9, 9]
    내 생각 2 -> 순열을 구하기 전에 중복 값을 다 제거한다. -> 안 된다
    -> Stream 중복 제거 -> distinct
    [1 7][1 9][7 1][7 9][9 1][9 7] -> [9,9]가 있어야 한다.
    내 생각 -> 순열을 구해지면 이전까지 구한 모든 순열과 비교하여 중복 체크 -> 입력의 크기가 작기 때문에 가능할 것 같다. -> 문제는 통과하나 시간이 너무 오래 걸린다.

    출처 :  baekjoon ID: ehdjs7878

 */
public class Main {
    static int N, M;
    static int[] src;
    static int[] tgt;
    static int[][] dst;
    static int dstIdx = 0;
    static StringBuilder sb = new StringBuilder();

    public static void perm(int cnt, int check) {
        if (cnt == M) {
            for (int i = 0; i < dstIdx; i++) {
                if (Arrays.equals(dst[i], tgt)) {
                    return;
                }
            }
            dst[dstIdx++] = Arrays.copyOf(tgt, M);
            for (int i = 0; i < M; i++) {
                sb.append(tgt[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 0; i < N; i++) {
            if ((check & (1 << i)) == 0) {
                tgt[cnt] = src[i];
                perm(cnt + 1, check | (1 << i));
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        src = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        tgt = new int[M];
        int K = 1;
        for (int i = 0; i < M; i++) {
            K *= (N - i);
        }
        dst = new int[K][M];
        Arrays.sort(src);
        perm(0, 0);

        System.out.println(sb);
        br.close();
    }
}
