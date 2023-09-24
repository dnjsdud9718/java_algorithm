package Q3.T23;
/**
 * 2023 신기한 소수 찾기
 */

import java.util.Scanner;
import java.io.*;
public class Main {
    private static int N;
    private static BufferedWriter bw;
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = sc.nextInt();
        sc.close();

        for(int i=1; i<10; i++) {
            if(i==2 || i == 3 || i==5 || i==7) dfs(i, 1);
        }

        bw.flush();
        bw.close();
    }
    public static void dfs(int n, int l) throws IOException{
        if(isPrime(n)){
            if(l == N) bw.write(n+ "\n");//System.out.println(n);
            else {
                for(int i=0; i<10; i++){
                    dfs(n*10+i, l+1);
                }
            }
        }
    }

    public static boolean isPrime(int n){
        for(int i=2; i*i <= n; i++){
            if(n%i == 0) return false;
        }
        return true;
    }
}
