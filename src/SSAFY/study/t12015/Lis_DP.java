package SSAFY.study.t12015;

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
지금 풀이는 O(N^2)이다. 이는 나중에 이분탐색(O(NlonN))을 이해하기 위한 보조 공부
DP를 이용해 푼다.

예제 입력
6
10 20 10 30 20 50
*/

import java.util.Scanner;
import java.util.StringTokenizer;
public class Lis_DP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        StringTokenizer st = new StringTokenizer(sc.nextLine());
        int[] A = new int[N];
        int[] D = new int[N];
        for(int i=0; i<N; i++) A[i] = Integer.parseInt(st.nextToken());
        D[0] = 1;
        for(int i=1; i<N; i++){
            for(int j=0; j<i; j++){
                if(A[j] < A[i]) D[i] = Math.max(D[i], D[j]+1);
            }
        }
        int max = -1;
        int idx = -1;
        for(int x : D) System.out.print(x+" ");
        System.out.println();
        for(int i=0; i<N; i++) if(D[i] > max) {
            max = D[i];
            idx = i;
        }
        System.out.println("max length: " + max);
        // LIS 출력해 보자: 여러 개가 있을 수 있지만 그 중 하나.
        for(int i=N-1; i >= 0; i--){
            if(D[i] == max){
                System.out.print(A[i]+" ");
                max -= 1;
            }
        }
        sc.close();
    }   
}
