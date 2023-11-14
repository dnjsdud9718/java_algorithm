package Q5.T41;

import java.util.Scanner;

/*
 * 11689 오일러 피 함수 구현하기
 * P[N] = 1~N사이에 N과 서로서인 자연수의 개수
 * 공식은 노트에 정리했음...
 *  
 */
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();
        long result = N;
        for(int p=2; p<= Math.sqrt(N); p++){
            if(N%p == 0) result -= (result/p);

            while(N%p == 0) N /= p;
        }
        if(N > 1) result -= (result/N);
        System.out.println(result);
        sc.close();
    }
}
