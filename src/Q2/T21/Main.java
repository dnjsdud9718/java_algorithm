package Q2.T21;

import java.io.BufferedReader;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.valueOf(br.readLine());
        int arr[] = new int[N];
        for(int i=0; i<N; i++) arr[i] = Integer.valueOf(br.readLine());

        radix(arr, 5);

        for(int x : arr) bw.write(x+ "\n");
        bw.flush();
        bw.close();
        br.close();
    }
    public static void radix(int[] arr, int M) {
        int jarisu = 1;
        int[] output = new int[arr.length];

        for(int i=0; i<M; i++) {
            int bucket[] = new int[10];
            for(int j=0; j<arr.length; j++){ // 자리수 카운트
                bucket[(arr[j]/jarisu)%10]++;
            }
            for(int j=1; j<10; j++) { // 부분합, 위치(인덱스) 구한다...자료구조 시간에 배웠음...
                bucket[j] += bucket[j-1];
            }
            for(int j=arr.length-1; j>=0; j--){
                output[bucket[arr[j]/jarisu%10]-1] = arr[j];
                bucket[arr[j]/jarisu%10]--;
            }
            for(int j=0; j<arr.length; j++) arr[j] = output[j];
            jarisu *= 10;
        }
    }
}
