package Q3.T31;

import java.util.Scanner;

/* 1300 배열에서 K번째 원소 찾기 gold2
 * 현재 문제에서 논리상 K번째 요소는 K보다 클 수 없다.
 * N번째 행이 N의 배수로 구성되기 때문이다.'
 * 
 * NOTE에 정리.,.!
 */

public class Main {
    private static int answer;
    private static int N;
    private static int K;
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        answer = 0;

        binarySearch(1, K);
        System.out.println(answer);
        sc.close();

    
    }
    public static void binarySearch(int s, int e){
        if(s<=e){
            int middle = (int)Math.ceil((double)(s+e)/2);
            System.out.println(s+ " " + e + " " + (double)(s+e) + " " + (double)(s+e)/2);
            int cnt = 0;
            for(int i=1; i<=N; i++){
                cnt += Math.min(N, (int)(middle/i));
            }
            if(cnt >= K) {
                answer = middle;
                binarySearch(s, middle-1);
            }else{
                binarySearch(middle+1, e);
            }
        }
    }
}
