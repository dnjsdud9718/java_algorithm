package SSAFYTEST.T1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.ArrayList;
public class Solution {
    public static void main(String[] args) throws Exception, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int test_case = 1; test_case <= T; test_case++){
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            ArrayList<Integer> arr = new ArrayList<>();
            for(int i=0; i<N; i++) arr.add(Integer.parseInt(st.nextToken()));
            Collections.sort(arr);
            //for(Object x: arr.toArray())System.out.printf("%d,", (int)x);
            long answer = 0;
            while(arr.size() > 1){
                int cur = arr.remove(arr.size()-1);
                answer += cur;
                int max = Integer.MIN_VALUE;
                int maxIdx = -1;
                for(int i=0; i<arr.size(); i++){
                    if(max <= arr.get(i) && arr.get(i) < cur){
                        max = arr.get(i);
                        maxIdx = i;
                    } 
                }
                if(maxIdx != -1) arr.remove(maxIdx);
            }
            if(!arr.isEmpty())answer += arr.get(0);
            wr.write("#"+test_case+" "+answer+"\n");
        }

        br.close();
        wr.flush();
        wr.close();
    }
}
