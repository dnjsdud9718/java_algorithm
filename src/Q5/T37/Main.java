package Q5.T37;
/*
 * 1929 소수 구하기
 * 
 *  M<= x <= N 사이의 소수 구하기.
 */
import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
public class Main {
   public static int isPrime(int x){
        for(int i=2; i*i <= x; i++){ // N = a*b -> a or b is less or equal than √N
            if(x%i == 0) return -1;
        } 
        return x;
   }
   public static boolean[] isPrimeWithEratos(int s, int e){
        boolean [] arr = new boolean[e+1];
        for(int i=2; i*i<=e; i++){ // i<=e 보다 다 범위 좁히기. i*i <= e
            if(!arr[i]){
                for(int j= i+i; j<=e; j+=i) arr[j] = true;
            }
        }
        return arr;
   }
   public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int M = sc.nextInt();
        int N = sc.nextInt();
        sc.close();
        try{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            for(int i= M; i<=N; i++){
                if(isPrime(i) == i && i!=1) bw.write(i+" ");
            }
            bw.write('\n');
            boolean [] arr = isPrimeWithEratos(M, N);
            for(int i=M ; i<=N; i++) if(!arr[i] && i != 1) bw.write(i+" ");
            bw.flush();
            bw.close();
        }catch(IOException e){
            System.out.println(e);
        }
   } 
}
