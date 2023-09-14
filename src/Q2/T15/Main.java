package Q2.T15;

import java.util.*;
/*
 * 2750 브론즈1
 * 수 정렬하기 1 
 */

import java.io.*;

public class Main { 
   public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.valueOf(br.readLine());
        int[] arr = new int[N];
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(br.readLine());

        for(int i=N-1; i>0; i--){
            for(int j=0; j<i; j++){
                if(arr[j] > arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }

        for(int x : arr) wr.write(x + "\n");
        wr.flush();
        wr.close();
        br.close();
   }
}
