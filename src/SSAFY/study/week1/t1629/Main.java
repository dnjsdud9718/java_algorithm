package SSAFY.study.week1.t1629;
/* 백준 1219 곱셈
 * DP로 풀려 했는데 java.lang.OutOfMemoryError: Java heap space 발생한다.
 */
import java.io.*;
import java.util.stream.Stream;
public class Main {
    static long A;
    static long B;
    static long C;
    public static void main(String[]  args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int [] ABC = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        A = ABC[0];
        B = ABC[1];
        C = ABC[2];

        long answer = f(B);
        System.out.println(answer);
        br.close();
    }
    
    public static long f(long B){
        if(B == 1) return A%C;
        long t = f(B/2);
        if(B%2 == 0){
            return t * t % C;
        }else{
            return (t*t%C) * A%C;
        }
    }
}
