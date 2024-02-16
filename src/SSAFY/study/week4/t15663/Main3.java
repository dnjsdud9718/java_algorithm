package SSAFY.study.week4.t15663;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    IDEA 출처 :  baekjoon ID: ehdjs7878 송도언
    1. 주어진 수열을 정렬
    2. 순열을 구한다.
     -> 어떻게 하면 동일한 순열을 한 번만 출력할 수 있을까???
     -> permutation -> recursive call이 끝나고 돌아와서 다음을 찾아갈때
     ->



 */
public class Main3 {
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

        br.close();
    }
}
