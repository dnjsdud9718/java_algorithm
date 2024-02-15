package SSAFY.study.week2.t9095;
/*
 백준 : 9095 1,2,3 더하기
 정수 4를 1,2,3의 합으로 나타내는 방법은 총 7가지가 있다. 합을 나타낼 떄는 수를 1개 이상 사용해야 한다.
 1+1+1+1
 1+1+2
 1+2+1
 2+1+1
 2+2
 3+1
 1+2
 정수 N이 주어졌을 대, N을 1,2,3의 합으로 나타내는 방법의 수를 구하는 프로그램 작성

 노트에 정리 했음.
    DP를 고려하지 않고 문제 해결 -> 이후 DP로 최적화할 수 있구나 생각하고 프로그램을 수정하는 것!! -> 사고의 흐름!!
 */
import java.util.Scanner;
public class Main {
    public static int[] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        dp = new int[11];
        dp[1] = 1;
        // for(int i=2; i<dp.length; i++) dp[i] = simpleRecursive(i); // 최적화 대상
        for(int i=2; i<dp.length; i++) dp[i] = recursiveWithMemoization(i);
        // for(int i=1; i<=10; i++) System.out.println(dp[i]);
        for(int test_case = 1; test_case <= T; test_case++){
            int N = sc.nextInt();
            System.out.println(dp[N]);

        }
        sc.close();
    }   
    public static int recursiveWithMemoization(int x){
        if(x==0) return 1;
        else if(x < 0) return 0;
        int first = (x-1 > 0 && dp[x-1] > 0) ? dp[x-1] : recursiveWithMemoization(x-1);
        int second = (x-2 > 0 && dp[x-2] > 0) ? dp[x-2] : recursiveWithMemoization(x-2);
        int third = (x-3 > 0 && dp[x-3] > 0) ? dp[x-3] : recursiveWithMemoization(x-3);
        return first + second + third;
    }
    // 재귀함수 -> 피보나치 수열 연산과 유사.
    public static int simpleRecursive(int x){
        if(x == 0) return 1;
        else if(x < 0) return 0;
        // 아래 부분을 최적화 할 수 있지 않을까?? => 반복되는 함수 호출이 발생하닌까!!
        else return simpleRecursive(x-1) + simpleRecursive(x-2) + simpleRecursive(x-3);
    }
}
