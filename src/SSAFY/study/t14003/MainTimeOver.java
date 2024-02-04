package SSAFY.study.t14003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 가장 긴 증가하는 부분수열 14003
// input
// 6
// 10 20 10 30 20 50
// output
// 4
// 10 20 30 50
//  당연히 시간초과 난다 -> O(N^2)
public class MainTimeOver {
    static int N;
    static int[] src;
    static int[] tgt;
    static int tgtIdx;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        src = new int[N];
        tgt = new int[N];
        for(int i=0; i<N; i++) tgt[i] = Integer.MIN_VALUE;
        tgtIdx = 0; 
        StringTokenizer st= new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) src[i] = Integer.parseInt(st.nextToken());
        tgt[0] = 1;
        for(int i=1 ; i<N; i++){
            for(int j=0; j<i; j++){
                if(src[i] > src[j]) tgt[i] = Math.max(tgt[i], tgt[j]+1);
            }
        }
        int answer = tgt[0];
        for(int i=1; i<N; i++){
            answer = Math.max(answer, tgt[i]);
        }
        System.out.println(answer);
        int[] arr = new int[answer];
        for(int i=N-1; i>=0 ;i--){
            if(tgt[i] == answer) {
                // System.out.printf("%d ", src[i]);
                arr[answer-1] = src[i];
                answer--;
            }
        }
        Arrays.sort(arr);
        for(int x: arr) System.out.printf("%d ", x);
        br.close();
    }
}
