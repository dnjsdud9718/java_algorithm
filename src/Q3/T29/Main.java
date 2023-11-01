package Q3.T29;
/**
 * 1920 원하는 정수 찾기
 */
import java.io.*;
import java.util.*;
import java.util.stream.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br  =  new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = Integer.parseInt(br.readLine());
        int[] B = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(A);
        for(int x : B) System.out.println(binarySearch(A, 0, N-1, x));
        br.close();
    }
    public static int binarySearch(int [] arr, int s, int e, int x){
        if(s <= e){
            int i = (s+e)/2;
            if(x == arr[i]) return 1;
            else if(x > arr[i]) return binarySearch(arr, i+1, e, x);
            else return binarySearch(arr, s, i-1, x);
        }
        return 0;
    }
}
