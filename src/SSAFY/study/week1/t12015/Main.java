package SSAFY.study.week1.t12015;
/*
수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고,
길이는 4이다.

입력
첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다.
둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000,000)

출력
첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.

고려사항: N이 1,000,000이기 때문에 O(N^2)로 문제를 해결하면 안된다.
이분탐색 알고리즘 사용.
앞선 Lis_DP 클래스에서 푼 알고리즘에서 0 ~ i-1을 훑는 이유는 A[i] > A[j]를 만족하는 A[j]중 D[j]가 가장 큰 값을 찾기 위해서다.
즉, D[j]를 알고 싶다. 
만약 D[j]=k를 만족하는 여러 j중 A[j]가 가장 작은 A[j]를 X[k]에 저장해 두면, 나중에 D[i](i>j)를 계산할 때 그 값들만 참조하면 된다.
무슨 뜻이냐면 i>j & A[i] < A[j]를 만족하는 가장 작은 A[j]값이 있다면, D[i]는 D[j] 보다 클 수 없다. 정확히 말하면 
D[i]는 D[j]와 같다.  D[j] >= D[i] > D[j-1] 
이때 A[j]를 쉽게 찾기 위해 X배열을 이용한다~!~~~~~~!~!!

예제 입력
6
10 20 10 30 20 50
*/
import java.io.*;
import java.util.stream.Stream;

public class Main {
    static int idx;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] X = new int[N];
        br.close();
        idx = 0;
        X[idx++] = A[0];
        for(int i=1; i<N; i++){
            if(X[idx-1] < A[i]) {
                X[idx++] = A[i];
            }
            else{ // A[i] <= X[idx] => A[i] < X[j]를 만족하는 가장 작은 X[j]값을 찾아서 A[i]로 바꿔준다. 
                binarySearch(X, 0, idx-1, A[i]);
            }
            // for(int j=0; j<idx; j++) System.err.print(X[j]+" ");
            // System.out.println();
        }
        System.out.println(idx);
    }   
    static void binarySearch(int[] X, int s, int e, int val){
        // System.out.println("---"+s+" "+e+"\n");
        if(s == e) X[s] = Math.min(X[s], val);
        if(s < e){
            int m = (s+e)/2;
            if(X[m] > val){
                if(m == 0 || X[m-1] < val) X[m] = val;
                else binarySearch(X, s, m-1, val);
            }else if(X[m] < val){
                if(m == idx-1 || X[m+1] > val) X[m+1] = val;
                else binarySearch(X, m+1, e, val);
            }
        }
    } 
}
