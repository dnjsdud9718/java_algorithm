package SSAFY.study.week4.t18870;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] src;
    static int[] copy;
    static int[] answer;
    static int[] DP;
    static HashMap<Integer, Integer> map;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        src = new int[N]; // input array
        answer = new int[N]; // output array
        DP = new int[N]; // Dynamic programming
        map = new HashMap<>(); //  핵심 ->  copy[i]의 DP[i]를 저장해 둔다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            src[i] = Integer.parseInt(st.nextToken());
        }
        copy = Arrays.copyOf(src, src.length); // copy array -> sort한 후에  DP로 문제 해결
        Arrays.sort(copy);
        map.put(copy[0], DP[0]); // 초기 세팅 -> 정렬된 배열의 가장 앞쪽(가장 작은 값은) 압축 가능한 경로가 없다.
        for(int i=1; i<N; i++){
            if (copy[i] > copy[i - 1]) {
                DP[i] = DP[i - 1] + 1;
            } else {
                DP[i] = DP[i - 1];
            }
            map.put(copy[i], DP[i]); // copy[i] == copy[j] 더라도 둘의 DP값은 같을 수 밖에 없다 -> 따라서 덮어 써도 된다. copy에 모든 값은 src에 있다.
        }
        for (int i = 0; i < N; i++) {
            int j = map.get(src[i]);
            answer[i] = j;
        }
        for (int x : answer) {
            sb.append(x).append(" ");
        }
        System.out.println(sb.toString());
        br.close();
    }
}
