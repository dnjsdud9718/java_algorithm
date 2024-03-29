package SSAFY.study.week5.t2467;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    2467 용액
    풀이 -> lower bounds(이분 탐색)
 */
public class Main2 {
    static int N, div;
    static long[] seq;
    static long ans1, ans2, min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        seq = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            seq[i] = Long.parseLong(st.nextToken());
        }
        // 음수끼리 합이 0에 가장 가까운 경우 -> 가장 큰 음수 값 + 그 다음 큰 음수 값
        // 양수끼리 합이 0에 가장 가까운 경우 -> 가장 작은 양수 값 + 그 다음 작은 양수 값

        // 음수 + 양수가 0에 가깝게 되는 경우를 찾는 방법(양수 선정)
        // |음수| 보다 크거나 같으면서 가장 작은 양수 값과 더해보기
        // |음수| 보다 작으면서 가장 큰 양수 값과 더해보기
        // lowerbounds를 활용할 수 있다.
        // -> 찾고자 하는 값 x보다 크거나 같으면서 가장 왼쪽에 위치한 값.
        ans1 = ans2 = 0L;
        min = Long.MAX_VALUE;
        div = N; // 양수와 음수의 경계를 담을 예정. div가 변경되지 않았다면 배열이 음수만을 가지고 있다.
        for (int i = 0; i < N; i++) {
            if (seq[i] > 0) {
                div = i; // 양수와 음수의 경계
                break;
            }
            // seq[i] < 0
            int j = lowerBounds(-1 * seq[i]);
            // j == N -> 찾는값 보다 크거나 같은 값 중 가장 작은 값이 seq에 없다 -> j-1째만 고려
            if (j != N) {
                closeZero(min, i,j);
            }
            if (seq[j - 1] > 0) {
                closeZero(min, i,j-1);
            }

        }
        if (div != N && div + 1 < N) {
            closeZero(min, div,div + 1);
        }
        if (div - 2 >= 0) {
            closeZero(min, div - 2, div - 1);
        }
        System.out.println(ans1+" "+ans2);
        br.close();
    }

    public static void closeZero(long x, int i, int j) {
        long y = seq[i] + seq[j];
        long ax = Math.abs(x);
        long ay = Math.abs(y);
        if(ax > ay) {
            min = y;
            ans1 = seq[i];
            ans2 = seq[j];
        }
    }
    public static int lowerBounds(long x) {
        int left = 0;
        int right = N;
        while (left < right) {
            int mid = (left+right)/2;
            if(seq[mid] >= x) right = mid;
            else left = mid+1;
        }
        return right;
    }
}
