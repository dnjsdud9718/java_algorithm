package Q5.T43;
/*
 * 1850 gcd 구하기
 * 
 */
import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
public class Main {
   public static long gcd(long a, long b){
    // a >= b
    if(a%b == 0) return b;
    return gcd(b, a%b);
   }
   public static void main(String[] args) throws IOException{
    Scanner sc = new Scanner(System.in);
    long aLong = sc.nextLong();
    long bLong = sc.nextLong();
    sc.close();

    long result = aLong<bLong ? gcd(bLong, aLong) : gcd(aLong, bLong);
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    for(long i=0; i<result; i++) bw.write("1");
    bw.flush();
    bw.close();
   } 
}
