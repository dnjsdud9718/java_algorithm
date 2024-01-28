package SSAFY.B.비트연산.이진수_표현;

/*
 정수 N, M이 주어질 때, M의 이진수 표현의 마지막 N비트가 모두 1로 켜져 있는지
 아닌지를 판별하여 출력하라.

 예제 입력
    5 -> T : # of test case
    4 0 : N M
    4 30 : N M
    4 47 : N M
    5 31 : N M
    5 62 : N M

 */

import java.util.Scanner;
public class Solution {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int T = sc.nextInt();
            for(int test_case = 1; test_case <= T; test_case++){
                int N = sc.nextInt();
                int M = sc.nextInt();
                // 1번째 비트에서 N번째 비트까지 연속된 비트를
                // 각각에 대해 해당
                // 비트가 켜져 있는지 확인하는 가정
                // 결국 각 비트에 대해서 &연산으로 켜져있는지 확인하는데
                // 한 번에 할 수 없을까?? -> 다음 코드에 적용!
                // boolean flag = true;
                // for(int i=0; i<N; i++){
                //     if((M&(1<<i)) != (1<<i)) {
                //         flag = false;
                //         break;
                //     }
                // }
                // if(flag) System.out.println("#"+test_case+" ON");
                // else System.out.println("#"+test_case+" OFF");


                // 연속된 N비트를 한번에 검사
                // (1<<3) - 1 
                //  (1<<3) => 1000 - 1 =? 0111
                //  M & 0111 => 연속된 3개 비트가 모두 켜져있다면 결과는 0111이다.
                if((M & ((1<<N)-1)) == (1<<N)-1){
                    System.out.println("#"+test_case+" ON");
                }else{
                    System.out.println("#"+test_case+" OFF");
                }
            }
            sc.close();
        }
}
