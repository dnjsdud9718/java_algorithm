package SSAFY.Q1.T5;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        
        for(int test_case=1; test_case<=N; test_case++) {
        	br.readLine();
        	st = new StringTokenizer(br.readLine());
        	int[] arr = new int[101];
        	for(int i=1; i<=1000; i++) {
        		int idx = Integer.parseInt(st.nextToken());
        		arr[idx]++;	
        	}
        	int max = arr[0];
        	int mIdx = 0;
        	for(int i=1; i<=100; i++) {
        		if(max <= arr[i]) {
        			max = arr[i];
        			mIdx = i;
        		}
        	}
        	wr.write("#"+test_case+" "+arr[mIdx]+"\n");
        }
        
        br.close();
        wr.flush();
        wr.close();
    }
}
