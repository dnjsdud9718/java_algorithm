package Q1.T5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
/**
 * 10986 silver5
 * (A+B) % M <=> (A%M + B%M)%M
 * (A%M + B%M)%M로 진행해보자.
 */

public class Main2 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.valueOf(st.nextToken());
        int M = Integer.valueOf(st.nextToken());

        long[] iArr = new long[N+1];
        long[] C = new long[M];
        long answer = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            iArr[i] = Long.valueOf(st.nextToken()) % M;  //나머지 연산
            iArr[i] += iArr[i-1]; // 가중합
            if(iArr[i] % M == 0 )answer++; //나머지 연산이 0이면 증가.
            C[(int)iArr[i]%M]++; // ★ 나머지 값이 같은 수 처리.
            // j < i, j번째와 i번째 원소의 나머지 값이 같다면 j+1부터 i까지의 가중합은 M으로
            // 나눠 떨어진다.
            // 왜냐하면, M으로 나눈 나머지가 같은 수로 남는다면 두 수의 사이의 거리는 M의 배수이닌까.
        }
        for(int i=0; i<M ; i++){
            if(C[i] > 1) answer += (C[i] * (C[i]-1) /2); // 조합 nC2
        }
        System.out.println(answer);
        br.close();
    }
}
