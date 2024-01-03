package SSAFY.sw1.최빈수구하기;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;
public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for(int ts = 1; ts <= T ; ts++){
            br.readLine();
            int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int [] jumsu = new int[101];
            for(int i=0; i<arr.length;i++) jumsu[arr[i]]++;
            
            int m = jumsu[0];
            int score = 0;
            for(int i=1; i<jumsu.length; i++){
                if(m <= jumsu[i]){
                    m = jumsu[i];
                    score = i;
                }
            }
            bw.write("#"+ts+" "+score+"\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
