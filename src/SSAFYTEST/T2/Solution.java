package SSAFYTEST.T2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int test_case = 1; test_case<=T; test_case++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int min = Integer.parseInt(st.nextToken());
            int max = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());

            int[] arr = new int[1001];
            int start = Integer.MAX_VALUE;
            int end = Integer.MIN_VALUE;
            for(int i=0; i<N; i++) {
                int val = Integer.parseInt(st.nextToken()); 
                arr[val]++; 
                if(val <=start) start = val;
                if(val >= end) end = val;
            }

            arr[0] = 0;
            for(int i=1; i<arr.length; i++){
                arr[i] = arr[i]+arr[i-1];
            }
            long answer = 0;

            for(int i=start+1; i<end; i++){
                for(int j=start; j<i; j++){
                    if(arr[i]- arr[j] >= min && arr[i]- arr[j] <= max && answer < arr[i]- arr[j]) answer = arr[i]- arr[j];
                }
            }
            wr.write("#"+test_case+" "+answer+"\n");
        }

        br.close();
        wr.flush();
        wr.close();
    }
}
