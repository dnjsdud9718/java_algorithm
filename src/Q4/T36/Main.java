package Q4.T36;
/**
 * 1541 최솟값을 찾는 괄호 배치 찾기
 * 더하기 부분을 크게 괄호로 묶어 먼저 계산 후 빼기 연산 실시
 * 가장 처음과 마지막 문자는 숫자.
 * 
 * ☆ -를 기준으로 split
 */
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Main {
    public static void main(String[] args) {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] sArr = br.readLine().split("-");
            int sum = 0;
            for(int i=0; i<sArr.length; i++){
                String[] num = sArr[i].split("\\+");
                int tmp = 0;
                for(int j=0; j<num.length; j++){
                    tmp += Integer.parseInt(num[j]);
                }
                if(i==0) sum += tmp;
                else sum -=tmp;
            }
            System.out.println(sum);
            br.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
