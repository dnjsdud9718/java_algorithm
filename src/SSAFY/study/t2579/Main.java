package SSAFY.study.t2579;

/*
 2579 계단 오르기
 계단 오르기 게임은 계단 아래 시작점부터 계단 꼭대기에 위치한 도착점까지 가는 게임이다. 각각의 계단에는
 일정한 점수가 쓰여 있는데 계단을 밟으면 그 계단에 쓰여 있는 점수를 얻는다.

 계단을 오르는 데는 다음과 같은 규칙이 있다.
 1. 계단은 한 번에 한 계단 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면 이어서 다음 계단이나, 다다음
 계단으로 갈 수 있다.
 2. 연속된 세 개의 계단을 모두 밟아서는 안 된다. 단, 시작점은 계단에 포함되지 않는다.
 2. 반드시 마지막 계단을 밟아야 한다.
 이 게임에서 얻을 수 있는 총점이 최대가 되는 프로그램 작성.

    6 N
    10 A1
    20 A2
    15 A3
    25 A4
    10 A5
    20 A6

 DP문제
 반복문을 돌면서 현재 위치가 마지막 계단이라 가정하고 가질 수 있는 최대 점수를 저장.
 현재 위치에 점수를 구할 때, 직전 계단을 밟고 온 경우와 그렇지 않은 경우를 구분해서 저장할 필요가 있다.
 직전 계단을 밟는 경우(0) => DP[i][0] = src[i] + DP[i-1][1]
 -> 연속된 3계단을 밟을 수 없으므로 직전 계단은 그 바로 전 계단을 밟을 수 없다.
 직전 계단을 밟지 않는 경우(1) => DP[i][1] = src[i] + Math.max(DP[i-2][0], DP[i-2][1])
 -> 직전 계단을 밟지 않으며 2번 제약을 무시할 수 있기 때문에 직전 직전 계단이 가질 수 있는 가장 큰 값을 가져온다.
 */
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] src = new int[N+1];
        for(int i=1; i<=N; i++) src[i] = sc.nextInt();
        int[][]DP = new int[N+1][2];
        DP[1][0] = DP[1][1] = src[1];
        for(int i=2; i<=N; i++){
            DP[i][0] = src[i] + DP[i-1][1];
            DP[i][1] = src[i] + Math.max(DP[i-2][0], DP[i-2][1]);
        }
        System.out.println(Math.max(DP[N][0], DP[N][1]));
        sc.close();
    }  
}
