package Q4.T32;


/*
 * 동전 개수 구하기
 * GREEDY 알고리즘 조건: 동전 Ai는 Ai-1의 배수
 * 
 * 메모이제이션 기법도 가능하나 시간이 오래걸리네...
 * 
 */
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;


public class Main {
    public static void greedySolution(int[] arr, int N, int K){
        int answer = 0;
        for(int i = N-1; i >= 0 ; i--){
            if(arr[i] <= K){
                answer += K/arr[i];
                K = K%arr[i];
            }
        }
        System.out.println(answer);
    }
    public static void memoizationSolution(int[] D, int N, int K){
        int [] minCoins = new int[K+1];
        int [] oneCoins = new int[K+1];
        minCoins[0] = 0;
        for(int i=1; i<=K; i++) minCoins[i] = Integer.MAX_VALUE;

        for(int i=1; i<=K; i++){
            for(int j=0; j<N; j++){
                if(D[j] <= i && minCoins[i-D[j]] + 1 <= minCoins[i]){
                    minCoins[i] = minCoins[i-D[j]] + 1;
                    oneCoins[i] = D[j];
                }
            }
        }
        System.out.println(minCoins[K]);

        //사용한 동전
        int change = K;
        int nCoins = minCoins[K];
        for(int i=1; i<=nCoins; i++){
            int coin = oneCoins[change];
            change -=coin;
            System.out.printf("%d ", coin);
        }

    }
    public static void main(String[] args){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] line = br.readLine().split(" ");
            int N = Integer.parseInt(line[0]);
            int K = Integer.parseInt(line[1]);
            int [] arr = new int[N];

            for(int i=0; i<N; i++){
                arr[i] = Integer.parseInt(br.readLine());
            }
            System.out.println("그리디 알고리즘");
            greedySolution(arr, N, K);
            System.out.println("==============");
            System.out.println("메모이제이션 알고리즘");
            memoizationSolution(arr, N, K);

            
            br.close();

        }catch(IOException e){
            System.out.println(e);
        }
    }
    
}
