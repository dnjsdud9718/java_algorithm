package SSAFY.Q1.T7;

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;
public class Solution{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        for(int test_case=1; test_case <= 10 ; test_case++){
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] arr = new int[100];
            for(int i=0; i<100; i++) arr[i] = Integer.parseInt(st.nextToken());
            for(int i=0; i<N; i++){
                int min = Integer.MAX_VALUE;
                int minIdx = -1;
                int max = Integer.MIN_VALUE;
                int maxIdx = -1;
                for(int j=0; j <100; j++){
                    if(min > arr[j]){
                        min = arr[j];
                        minIdx = j;
                    }
                    if(max < arr[j]){
                        max = arr[j];
                        maxIdx = j;
                    }
                }
                arr[minIdx]++;
                arr[maxIdx]--;      
            }
            int min = Integer.MAX_VALUE;
            int minIdx = -1;
            int max = Integer.MIN_VALUE;
            int maxIdx = -1;
            for(int j=0; j <100; j++){
                if(min > arr[j]){
                    min = arr[j];
                    minIdx = j;
                }
                if(max < arr[j]){
                    max = arr[j];
                    maxIdx = j;
                }
            }
            wr.write("#"+test_case+" "+(arr[maxIdx]-arr[minIdx])+"\n");                     
        }
        wr.flush();
        wr.close();
        br.close();
    }
}
