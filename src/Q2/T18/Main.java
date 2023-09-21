package Q2.T18;
/**
 * 11399 silver 3
 * ATM 인출 시간 계산하기
 * 
 */

 import java.io.*;
 import java.util.StringTokenizer;
 import java.util.HashMap;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i=0; i<arr.length ;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<arr.length; i++){
            int Min = i;
            for(int j=i+1; j<arr.length; j++){
                if(arr[Min] > arr[j]) Min = j;
            }
            if(arr[i] > arr[Min]){
                int tmp = arr[Min];
                arr[Min] = arr[i];
                arr[i] = tmp;
            }
        }
        int sum = 0;
        int result = 0;
        for(int i=0; i<arr.length; i++){
            sum += arr[i];
            result += sum;
        }
        System.out.println(result);

        br.close();
    }
}
