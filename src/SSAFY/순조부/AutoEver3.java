package SSAFY.순조부;


// 집합의 원소 개수 N이 주어진다.
// 각 부분집합의 합이 오름차순(단조 증가)으로 주어진다. S1 <= S2 <= ... <= Sn-1 (단, 공집합은 고려 X)
// 주어진 정보를 가지고 원본 집합의 원소를 알아내시오.
// 원본 집합 Ai 원소는 다음 조건을 만족한다.0 <= A1 <= A2 <= A2 <= ... <= Ai  <= An <= 10^6
// 2 <= N <= 20
// 예
// 3 : N
// 1 1 2 5 6 6 7 src
// output : 1 1 5
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class AutoEver3 {
    static int N, K, R;
    static int[] src;
    static int[] tgt;
    static int[] tmp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        src = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        tgt = new int[N]; // output array
        tgt[0] = src[0];  //
        tgt[1] = src[1];  //
        for(int i=2; i<N; i++){
            R = i;
            tmp = new int[(1<<i)]; // 현재 tgt에 들어간 값에 대한 부분집합의 합을 저장.
            K = 0;
            subset(0, 0);
            Arrays.sort(tmp);
//            for(int t : tmp) System.out.printf("%d ", t);
//            System.out.println();
            int tIdx =  tmp.length;
            for(int j=1; j<tmp.length; j++){
                if(src[j-1] != tmp[j]){
                    tIdx = j-1;
                    break;
                }
            }
            tgt[i] = src[tIdx];

            System.out.println();
        }
        for(int j=0; j<tgt.length; j++) System.out.printf("%d ", tgt[j]);
        br.close();
    }
    public static void subset(int idx, int sum){
        if(idx == R){   
            tmp[K++] = sum;
            return;
        }
        subset(idx+1, sum+tgt[idx]);
        subset(idx+1, sum);
    }
}