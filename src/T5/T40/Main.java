package T5.T40;
/*
 * 1016 제곱이 아닌 수 찾기, gold1
 * 어떤 수 X가 1보다 큰 제곱수로 나눠떨어지지 않을 때 이 수를 '제곱이 아닌 수'라고 가정해보자. 여기서 제곱수는 정수의 제곱이다.
 * min과 max값이 주어질 때 min보다 크거나 같고, max보다 작고나 같은 값 중 '제곱이 아닌 수'가 몇개 있는지 출력하시오.
 * 1 <= min <= 10^12 min <=max<=min+10^6
 * 
 * max-min  <= 1,10^6
 * 
 * 
 */
import java.util.Scanner;
public class Main {
    public static long solution(long min, long max){
        long count = 0;
        boolean[] arr = new boolean[(int)(max-min+1)]; //initialized by false;
        //왜 √max까지만 반복문을 되는지는 이제 충분히 이해했다
        //√max보다 큰 수의 제곱은 범위를 벗어난다.
        for(long i=2; i<=Math.sqrt(max);i++){
            long pow = i*i; // i를 int형으로 하면 안된다. i*i의 범위가 정수 범위를 초과할 수 있기 때문에.. 업캐스팅되기 전에...
            /*  
                아래 코드 문제는 arr.length만큼 하나하나 체크한다. 최대 10^6만큼 for문을 돈다.
                for(int j=0; j<arr.length; j++){
                    long val = min+j;
                    if(val%pow == 0) arr[j] = true;
                }
                따라서 더 슬기로운 방식이 요구된다...

                어떤 x가 y로 나눠떨어진다는 것은 x는 y의 배수!!!
                따라서, min<=x<=max인 x가 어떤 제곱수 y로 나눠떨어진다는 것은 x는 제곱수 y의 배수!!!!
                
                pow는 pow < min or min <= pow <= max이고 min과 max 사이의 pow의 배수를 계속 찾아갈 때,
                startIdx = min/pow;
                만약 pow < min이라면 pow*startIdx는 min 보다 작거나 같은 pow의 배수 중 min에 가장 근사한 값.
                pow < min일 때, min % pow == 0 이면 pow * startIdx == min이다.
                pow < min일 때, min % pow != 0 이면 startIdx를 1증가하여 pow*startIdx가 min보다 큰값 중 min에 가장 근사한 값으로 만든다.
                만약 pow > min이면 min/pow = 0이 되고, min != 0이라면 나머지가 0일 수 없다. 
                startIdx = 0이면 pow*startIdx가 0이 되기 때문에 1증가 시켜준다. 
            */
            long startIdx = min/pow;
            if(min%pow != 0) startIdx++; 
            for(long j = startIdx; j*pow<=max; j++){
                arr[(int)(j*pow-min)] =true;
            }

        }
        for(boolean x: arr) if(!x) count++;
        return count;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long min = sc.nextLong();
        long max = sc.nextLong();

        System.out.println(solution(min, max));
        sc.close();
    }
}
