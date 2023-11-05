package T5.T38;

/*
 * 1456 거의소수 나머지 합 구하기. silver
 * 어떤 수가 소수의 N제곱(N>=2)꼴일 때, 그 수를 거의 소수라고한다.
 * 두 정수 A와 B가 주어지면, A보다 크거나 같고, B보다 작거나 같은 거의 소수가 몇 개인지 출력하시오.
 * 
 * 어떤 소수 x에 대해서 x^n이 A <= x^n <= B를 만족하는지 알아야 한다.
 * 주의  x^n이 long 범위를 벗어날 수 있다...
 * 따라서 다음과 같이 비교해야 한다. A/x^(n-1) <= x <= B/x^(n-1)
 * 
 * sqrtE를 사용하는 두 가지 이유
 * 1. E과 소수인지 알고 싶다면 √E까지만 필요, 왜냐하면 E=a*b 이고 a<=b라면 a <= √E이기 때문이다.
 * 2. sqrtE보다 큰 수의 제곱은 E의 범위를 초과한다. 
 * 
 * 이 문제는 자료형의 범위에 대한 이해가 필요하고, 타입 캐스팅을 적절히 잘 해줘야 한다. 
 */
import java.util.Scanner;
public class Main {
    public static void eratosthenes(long A, long B){
        long sqrtE = (long)Math.sqrt((double)B);
        // sqrtE <= 10^7을 만족하므로 int로 타입 캐스팅 가능하다.
        long [] arr = new long[(int)sqrtE + 1];
        //에라토스테네스의 체
        for(long i = 2; i <= sqrtE; i++){
            if(arr[(int)i] == 0){
                for(long j=i+i; j<=sqrtE; j+=i){
                    arr[(int)j] = -1;
                }
            }
        }
        
        //거의 소수 찾기
        int cnt =0;
        for(long i = 2; i<=sqrtE; i++){
            if(arr[(int)i] == 0) {
                long j=i;
                //i <= (double)B/(double)j && (double)A/(double)j <= i 하면 안된다.
                // 앞조건 만족하고 뒷조건 만족하지 않을 시, i^n을 키워야 한다.
                while(i <= (double)B/(double)j){
                    if((double)A/(double)j <= i) cnt++;
                    j*=i;
                }
            }
        }
        System.out.println(cnt);
    }
   
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //long -> 약 9 * 10^19 양의 정수를 담을 수 있다. 따라서 10^14 담을 수 있다.
        long A=sc.nextLong(); 
        long B=sc.nextLong();
        sc.close();
        
        eratosthenes(A, B);
       
    }

}
