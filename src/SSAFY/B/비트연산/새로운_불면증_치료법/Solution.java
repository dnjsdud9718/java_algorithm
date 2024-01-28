package SSAFY.B.비트연산.새로운_불면증_치료법;
/*
    호석이는 불면증에 걸렸다. 그래서 잠이 안 올 때의 민간요법 중 하나인 양 세기를 하려고 한다.
    호석이는 1번 양부터 순서대로 세는 것이 재미없을 것 같아서 N의 배수 번호인 양을 세기로 하였다.
    즉, 첫 번째에는 N번 양을 세고, 두 번째에는 2N번 양, ... , K번째에는 KN번 양을 샌다.
    이렇게 숫자를 세던 호석이엥게 잠은 더 오지 않고 다음과 같은 궁금증이 생겼다. 
    이전에 셋던 번호들의 각 자리수에서 0에서 9까지의 모든 숫자를 보는 것은 최소 몇 번 양을 센 시점일까??

    예를 들어 N = 1295이라고 하자.
    첫 번째로 N = 1295번 양을 센다. 현재 본 숫자는 1, 2, 5, 9이다.
    두 번째로 2N = 2590번 양을 센다. 현재 본 숫자는 0, 2, 5, 9이다.
    현재까지 본 숫자는 0, 1, 2, 5, 9이다.
    세 번째로 3N = 3885번 양을 센다. 현재 본 숫자는 3, 5, 8이다.
    현재까지 본 숫자는 0, 1, 2, 3, 5, 8, 9이다.
    네 번째로 4N = 5180번 양을 센다. 현재 본 숫자는 0, 1, 5, 8이다.
    현재까지 본 숫자는 0, 1, 2, 3, 5, 8, 9이다.
    다섯 번째로 5N = 6475번 양을 센다. 현재 본 숫자는 4, 5, 6, 7이다.
    현재까지 본 숫자는 0, 1, 2, 3, 4, 5, 6, 7, 8, 9이다.
    5N번 양을 세면 0에서 9까지 모든 숫자를 보게 되므로 호석이는 양 세기를 멈춘다.

    N (1 ≤ N ≤ 10^6)

    ★ 먼저 비트연산에 대해 몰랐다는 가정 하에 문제를 풀어보자
    1. 0~9를 표시할 수 있는 배열을 선언
    2. I(1,2,...,) * N 에 대해서 나머지 나누기와 나머지 연산을 통해 각 자리 값을 가져온 후 위에서 정의한 배열에 값 갱신
    3. 1번에서 선언한 배열의 모든 값이 0인지 아닌지 확인 후 맞다면 결과 출력, 아니면 1-3 반복

    ★ 비트 연산을 활용한 문제 해결(내가 생각한 것)
    0 ~ 9는 10비트로 표현 가능 => short => answer
    KN의 각 자리수를 읽고 v(0~9)라고 하자 => answer | (1<<v) => answer == (1<<10)-1 => stop

 */
import java.util.Scanner;
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
//      비트 연산을 몰랐을 때.
        {/*for(int ts = 1; ts <=T; ts++){
            boolean[] num = new boolean[10];
            int N = sc.nextInt();
            int k = 0;
            int M;
            while( (M = N*(++k)) > 0){
                int C = M;
                while(C != 0){
                    num[C%10] = true;
                    C /= 10;
                }
                boolean flag = true;
                for(int i=0; i<num.length; i++) if(!num[i]) flag = false;
                if(flag) break;
            }
            
            System.out.println("#"+ts+" "+(k*N));
        }*/}
//      비트 연산으로 문제 해결
        for(int ts = 1; ts <= T; ts++){
            int N = sc.nextInt();
            short answer = 0;
            int k = 0;
            while(answer != (1<<10)-1){
                int M = N*(++k);
                while(M > 0){
                    answer = (short)(answer | (1<<(M%10)));
                    M = M/10;
                }
            }
            System.out.println("#"+ts+" "+N*k);
        }
        sc.close();
    }
}
