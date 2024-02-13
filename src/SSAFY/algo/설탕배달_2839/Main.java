package SSAFY.algo.설탕배달_2839;

import java.util.Scanner;
/*
    2839 설탕 배달 -> Greedy
    N : 29
    5 5 5 5 3 3 3 <- 해
    어떻게 구하냐?
    N이 5로 나눠 떨어지지 않는다면 3을 빼준다 -> 뒤에서부터 빼주는 느낌.

    N : 31
    5 5 5 5 5 3 3
    31%5 = 1 -> 31-3 = 28 (3을 빼준다)
    28%5 == 3 -> 28-3 = 25 (3을 빼준다)
    25%5 == 0 -> finish (5로 나눠 떨어진다.)

    N : 4
    4%5 == 4 -> N = 4-3 = 1
    1%5 == 1 -> N = 1-3 = -2
    N < 0 -> finish -> answer => -1
    
    Dynamic Programming 가능
 */
public class Main {
    static int N, answer;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
//        answer = 0;
//        while (true) {
//            if (N < 0) {
//                answer = -1;
//                break;
//            }
//            // N%5 == 0
//            if(N%5 == 0){
//                answer += N/5;
//                break;
//            }
//            else{ // 3을 빼준다.
//                answer++;
//                N-=3;
//            }
//        }
//        System.out.println(answer);
        if(N <= 5){
            if(N == 3 || N ==5) answer = N;
            else answer = -1;
        } else answer = dp(N);
        System.out.println(answer);
        sc.close();
    }
    public static int dp(int x){
        int[] D = new int[x + 1];
        for(int i=0; i<=x; i++) D[i] = 5000;
        D[3]=1;
        D[5]=1;
        for(int i=6; i<=x; i++){
            D[i] = Math.min(D[i - 3], D[i - 5])+1;
        }
        return (D[x] > 5000) ? -1 : D[x];
    }
}
