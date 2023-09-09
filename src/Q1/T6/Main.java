package Q1.T6;



/*
 * 2018 실버5
 * 투 포인터 사용 O(N)
 * 종료 조건 설정이 중요하네: right가 N이 된다면 결국 연속된 합이 N이 될 수 있는 조건은 N 단독 하나 뿐이다.
 * 따라서 초기 cnt값에 1을 주고 while문 종료 조건으로 right != N을 둔다.
 * 
 */

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        
        int left=1, right=1;
        int sum = 1; //1에서 시작
        int cnt = 1; // N 자기 자신에 대한 count

        while(right != N){ // right가 N이 되는 
            if(sum == N) {
                //System.out.println(left + "," + right);
                cnt++;
                sum += (++right) - left++;
            }else if(sum < N){
                sum += (++right);
            }else{
                sum -= left++;
            }
        }
        System.out.println(cnt);
        sc.close();
    }
}
