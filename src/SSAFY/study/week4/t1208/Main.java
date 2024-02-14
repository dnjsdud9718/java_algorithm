package SSAFY.study.week4.t1208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
  baekjoon 1208 부분 수열의 합2 ★★★★★
  N개의 정수로 이뤄진 수열이 있을 때, 크기가 양수인 부분수열 중에서 그 수열의 원소를 다 더한 값이  S가 되는 경우의
  수를 구하는 프로그램을 작성하시오.

  입력
  첫째 줄에 정수의 개수 N(1<=N<=40)과 S(|S| <= 1,000,000)이 주어진다.
  둘째 줄에 N개의 정수가 빈 칸을 사이에 두고 주어진다. 주어지는 정수의 절댓값은 100,000을 넘지 않는다.

  츨력
  합이 S가 되는 부분수열의 개수를 출력한다.

  ★ -> 입력 받은 배열의 모든 부분 집합을 구하면 안되는 이유 -> 2^40 은 약 1조개이다.
     -> 입력 받은 배열을 2개로 나눠 생각하는 아이디어! 2^20은 약 1백만개이다. 따라서 배열에 담을 수 있다.
     -> 입력 받은 배열을 두 개의 배열로 나눠 각각 left, right라고 하자. lSize : left.size(), rSize : right.size();
        -> N이 홀수인지 짝수인지에 따라 크기를 잘 지정해줘야 한다.
     -> left, right 각각의 부분집합의 합을 담는 배열을 subsetLeft, subsetRight라고 하자 -> subsetLeft의 사이즈 : (1<<lSize), subsetRight의 사이즈 : (1<<rSize)
     -> ★★★ 핵심 알고리즘
        -> 문제 : 부분집합의 합이 S인 부분집합의 개수를 구하기
        -> subsetLeft의 모든 원소에 대해 각 원소 하나가 subsetRight의 원소들과 더하여 S를 만드는 갯수를 구해야 한다.
            -> 시간 복잡도를 줄이기 위해서 "이진 탐색"을 해야 한다.
            -> subsetRight를 정렬하여 "이진 탐색"을 해야 한다, subsetRight에 S-Ki의 갯수를 구해야 한다.(Ki는 leftSubset의 i번째 원소)
            -> ★★★★★ 중요! -> subsetRight의 각 원소는 서로 다를 수도 있으나 같은 수가 있을 수도 있다.
                -> 따라서, 이진탐색 시 lower/upper bounds를 활용해야 한다.
                -> lowerBounds -> 찾고자 하는 값 x 이상인 첫 번째 위치
                -> upperBounds -> 찾고자 하는 값 x 초과하는 첫 번째 위치
        -> S가 0일 때의 예외처리
            -> 공집합을 제외해줘야 한다.
            -> leftSubset의 공집합 + rightSubset의 공집합은 0이기 때문에 구해진 COUNT에서 1 감소해줘야 한다.
            -> leftSubset이 0인 원소 중 하나는 반드시 공집합 0이다.
            -> rightSubset이 0인 원소 중 하나는 반드시 공집합 0이다.
            -> leftSubset 공집합 + rightSubset 공집합 0인 경우를 제외해줘야 한다!!
            -> 두 서브셋 중 하나는 공집합 0이고 다른 하나는 부분집합의 합이 0인 경우는 카운트 해줘야 한다.

 */
public class Main {
    static int N, S, L;
    static long COUNT;
    static int[] sub;
    static int[] src;
    static int[] tgt;
    static int tgtIdx;
    static int[] left;
    static int[] subsetLeft;
    static int[] right;
    static int[] subsetRight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        src = new int[N];
        int sIdx = 0;
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            src[sIdx++] = Integer.parseInt(st.nextToken());
        }
        int lSize = sIdx/2;
        int rSize = (sIdx % 2 == 0) ? lSize : lSize+1;
        left = new int[lSize];
        right = new int[rSize];
        System.arraycopy(src, 0, left, 0, lSize);
        System.arraycopy(src, lSize, right, 0, rSize);
        subsetLeft = new int[(1 << lSize)];
        subsetRight = new int[(1 << rSize)];
        L = lSize; // L과 sub을 사용한 이유 -> subsetLeft와 subsetRight의 원소를 채울 때 하나의 subset()를 call하기 위해서.
        sub = left;
        tgt = subsetLeft;
        tgtIdx = 0;
        subset(0, 0);

        L = rSize;
        sub = right;
        tgt = subsetRight;
        tgtIdx = 0;
        subset(0, 0);
//        System.out.println("subsetLeft = " + Arrays.toString(subsetLeft));
        Arrays.sort(subsetRight); // 이진탐색을 위한 정렬
//        System.out.println("subsetRight = " + Arrays.toString(subsetRight));

        COUNT = 0;
        for (int i = 0; i < (1<<lSize); i++) {
            binarySearch(subsetRight, 0, (1<<rSize), S - subsetLeft[i]);
        }
        System.out.println(S == 0 ? COUNT -1 : COUNT);
        br.close();
    }

    public static void binarySearch(int[] array, int s, int e, int x) {

        int l = lowerBound(array, s, e, x);
        int r = upperBound(array, s, e, x);
//        System.out.println(x + " " + l + " " + r );
        COUNT += (r - l);
    }


    //원하는 값 이상의 첫번째 위치
    public static int lowerBound(int[] array, int s, int e, int x) {
        while (s < e) {
            int m = (s+e)/2;
            if(array[m] < x) s = m+1;
            else e = m;
        }
        return e;
    }

    // 원하는 값을 초과하는 첫번째 위치
    public static int upperBound(int[] array, int s, int e,int x) {
        while (s < e) {
            int m = (s+e)/2;
            if(array[m] <= x ) s = m+1;
            else e = m;
        }
        return e;
    }

    public static void subset(int idx, int sum) {
        if (idx == L) {
            tgt[tgtIdx++] = sum;
            return;
        }
        subset(idx + 1, sum);
        subset(idx + 1, sum + sub[idx]);
    }
}
