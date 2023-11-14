package Q5;

import java.util.Scanner;

/*
 * 오일러의 피
 * 오일러 피 함수 P[N]의 정의는 1부터 N까지 범위에서 N과 서로소인 자연수의 개수
 * 서로소: 1 외에 공통된 약수를 갖지 않는 둘 이상의 양의 정수. 두 수의 최대 공약수가 1, 최소공배수가 두 수의 곱.
 * 
 * 참고 : 두 집합들의 교집합이 공집합(Disjoint Set)일 때, 그 집합들을 서로소라고 한다. 확률론에서 어떤 사건들에 해당하는 집합들이 서로소이면, 그 사건
 * 들은 배반사건이다. 
 * 
 */

public class EulerProductFormula {
        public static void main(String[] args){
            Scanner sc = new Scanner(System.in);
            long n = sc.nextLong();
            sc.close();
            //result는 서로소의 개수를 저장하고, n은 소인수 구성을 표현한다.
            long result = n;
            //Math.sqrt를 하는 이유
            //우리는 지금 n의 소인수를 찾고 있다. n은 소인수의 곱으로 이뤄져 있다.
            // a<=b이고 x = a*b일 때, a <= √x 이다.
            // p는 n을 구성하는 소인수 중 가장 작은 수이기 때문에 √n보다 작거나 같아야 한다.
            for(long p=2; p <= Math.sqrt(n); p++) {
                if(n%p == 0) { // p는 n의 소인수.
                    result = result - result/p; //오일러 피 연산
                    // p가 n의 소인수이기 때문에 위에서 필요한 연산을 수행했다. 
                    // 그럼 소인수 구성요소 중 p는 활용했기 때문에 다음 소인수를 찾아야 한다.
                    // 따라서 n(나눠지는 수) % p(나누는 수)가 나눠 떨어지지 않을 때까지 계속 계산해서 p^k를 없앤다.
                    while(n%p == 0) n /= p;
                    // 변경된 n에 대한 새로운 소인수를 찾아야 한다.
                }
            }
            
            if(n > 1) {
                result = result - result/n;
            }
            System.out.println(result);
        }
}
