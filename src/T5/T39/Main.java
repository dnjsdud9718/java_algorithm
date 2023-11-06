package T5.T39;

/*
 * 1747 골드5 소수&팰린드롬 수 중에서 최솟값 찾기.
 */
import java.util.Scanner;
public class Main {
    public static int solution(int N){
        int [] arr = new int[10000001]; // 1 <= N <= 10^7이닌까 N이 10^7인 경우 고려하여 배열의 크기를 10^8으로 한다.

        //eratosthenes
        for(int i=2; i<=Math.sqrt(arr.length);i++){
            if(arr[i] == 0){
                for(int j=i+i; j<arr.length;j+=i){
                    arr[j] = -1;
                }
            }
        }
        // 소수이면서 팰린드롬 찾기
        if(N==1) N++;
        for(int i=N; i<arr.length; i++){
            if(arr[i] == 0){ // get prime number
                char[] chArr = String.valueOf(i).toCharArray();
                int p=0, q=chArr.length-1;
                boolean flag = true;
                while(p<q){
                    if(chArr[p] != chArr[q]){
                        flag = false;
                        break;
                    }
                    p++; q--;
                }
                if(flag) return i;
            }
        }
        return -1;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int answer = solution(sc.nextInt());
        sc.close();
        if(answer != -1) System.out.println(answer);
    }   
}
