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


    구해진 순열의 중복을 제거하는 것이 문제
    동일한 값이 하나의 슌열에 중복되어 나타나는 것을 문제가 되지 않는다.

    perm() 메서드 -> k-1번째까지 구해진 수열이 동일한 경우, 수열에 들어갈 k번째 값에 동일한 값이 두 번 이상 들어가면 안된다. 이미 해당 값을 가지고 구할 수 있는 수열(순열)을 모두 구했기 때문에 중복된 수열이 구해진다.
 */
public class Main4 {
    static int N, M;
    static int[] src;
    static int[] tgt;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        src = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        tgt = new int[M];
        Arrays.sort(src);
        perm(0, 0);
        System.out.println(sb);
        br.close();
    }

    public static void perm(int cnt, int check) {
        if (cnt == M) {
            for(int i=0; i<M; i++)sb.append(tgt[i]).append(' ');
            sb.append('\n');
            return;
        }
        // 왜 중복 체크가 가능한가? 초기 입력을 정렬 시켰기 때문에...!!
        int dup = -1; // duplication check
        for (int i = 0; i < N; i++) {
            if((check & (1<<i)) != 0 || src[i] == dup) continue;
            tgt[cnt] = src[i];
            dup = src[i];
            perm(cnt + 1, check | (1<<i));
        }
    }
}
