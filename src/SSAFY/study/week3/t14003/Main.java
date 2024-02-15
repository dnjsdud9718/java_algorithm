package SSAFY.study.week3.t14003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Stream;

public class Main {
    static int N;
    static int src[];
    static int tgt[];
    static int dp[];
    static int dpIdx;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        src = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dp = new int[N];
        tgt = new int[N];
        dp[0] = src[0];
        dpIdx = 0;
        tgt[0] = 1;
        for(int i=1; i<N; i++){
//      이중 for문 DP로 문제를 풀 때 -> src[j]가 src[i]보다 작은 j중 D[j]가 가장 큰 j를 찾았다.
            if(dp[dpIdx] < src[i]) {
                dp[++dpIdx] = src[i];
                tgt[i] = dpIdx+1;
            }
            else{
                // D[j] = k(k는 가장 긴 부분수열의 길이)를 만족하는 j들 가장 작은 값(src[j])을 dp[k]에 저장
                // dp 배열의 길이가 곧 가장 긴 부분수열의 길이가 된다.
                // dp는 정렬된 상태->이분탐색->src[i]를 D[i] = k를 만족하는 dp[k]에 갱신
                int start = 0;
                int end = dpIdx;
                while(start <= end){
                    int middle = (start+end)/2;
                    if(src[i] <= dp[middle]) end = middle-1;
                    else start = middle+1;
                }
                dp[end+1] = src[i]; // 0 부터 시작해서 헷갈리네....
                tgt[i] = end+2;
            }
        }
        // System.out.println(dpIdx+1);
        sb.append(dpIdx+1).append("\n");
        Deque<Integer> stack = new ArrayDeque<>();
        int tmp = dpIdx+1;
        for(int i=N-1; i>=0; i--){
            if(tgt[i] == tmp) {
                stack.addFirst(src[i]);
                tmp--;
            }
        }
        while(!stack.isEmpty()) sb.append(stack.removeFirst()).append(" ");
        System.out.println(sb.toString());
        br.close();
    }
}
