package Q1.T5;

import java.util.*;
/**
 * 
 * 10986 GOLD3
 * 
 * iArr 을 long 타입으로 선어해야 하는 이유.
 * 극단적으로 원소 값이 모두 10^9이라고 생각하자 배열의 최대 크기는 10^6
 * 다중합을 진행하면 정수형으로 못담는 값이 당연히 발생한다.10^9 * 10^6
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        long [] iArr = new long[N+1];
        long [] C = new long[M];
        long answer = 0;

        iArr[0] = 0;
        for(int i=1; i<=N; i++){
            iArr[i] = iArr[i-1] + sc.nextLong();
            int reminder = (int) (iArr[i]%M);
            if(reminder == 0) answer ++;
            C[reminder]++;
        }

        for(int i=0; i<M; i++){
            if(C[i] > 1){
                answer = answer + (C[i] * (C[i]-1) / 2);
            }
        }

        System.out.println(answer);
        

        sc.close();
    }
}
