package Q5.T42;

/*
 * 1934 최소 공배수 구하기
 * 유클리드 호제법을 이용해 최대 공약수를 구한다.
 * gcd(270, 192)
 * 270%192 = 78
 * 192%78 = 36
 * 78%36 = 6
 * 36%6= 0, 따라서 gcd(270, 192) = 6
 * 최소공배수 = 270*192 / 6;
 */
import java.util.Scanner;
public class Main {
    public static int euclidean(int a, int b){
        // a <= b
        if(a%b == 0) return b; 
        return euclidean(b, a%b);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        for(int i=0; i<testCase; i++){
            int aInt = sc.nextInt();
            int bInt = sc.nextInt();
            int gcd = aInt<bInt ? euclidean(bInt, aInt) : euclidean(aInt, bInt);
            System.out.println(aInt * bInt / gcd);
        }
        sc.close();
    }
}
