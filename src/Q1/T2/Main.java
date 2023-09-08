package Q1.T2;
/**
 * 1546 브론즈1
 */
import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] iArr = new int[N];
        long max = 0;
        for(int i=0; i<N; i++){
            iArr[i] = sc.nextInt();
            if(max <= iArr[i]) max = iArr[i];
        }
        double answer = 0;
        for(int i=0; i<N; i++){
            answer = answer +  ((double)iArr[i]/max * 100);
        }
        System.out.println(answer/N);
        sc.close();
    }    
}
