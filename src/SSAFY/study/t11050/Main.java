package SSAFY.study.t11050;
// 자연수 N과 정수 K가 주어졌을 때 이항 계수 NCK를 구하는 프로그램 작성
// 1<=N<=10, 0<=K<=N

import java.util.Scanner;

public class Main {
    static int N, K, answer;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        answer=0;
        comb(0, 0);
        System.out.println(answer);
        sc.close();
    }  
    public static void comb(int srcIdx, int tgtIdx)  {
        if(tgtIdx == K){
            answer++;
            return;
        }
        for(int i=srcIdx; i<N ; i++){
            comb(i+1, tgtIdx+1);
        }
    }
}
