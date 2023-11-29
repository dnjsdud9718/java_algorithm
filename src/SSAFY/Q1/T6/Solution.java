package SSAFY.Q1.T6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
    public static boolean isPalindrome(String s){
        char[] cArr = s.toCharArray();
        int i=0;
        int j=s.length()-1;
        boolean b = true;
        while(i<j){
            if(cArr[i] != cArr[j]) {
                b = false;
                break;
            }
            i++; j--;
        }
        return b;
    }
    public static String reverse(String s){
        String r = "";
        for(int i = s.length()-1; i>= 0; i--){
            r = r + s.charAt(i);
        }
        return r;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int test_case=1; test_case<=T; test_case++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            long answer = 0;
            String[] arr = new String[N];
            boolean[] bArr = new boolean[N];
            boolean b = false;
            for(int i=0; i<N; i++){
                arr[i]=br.readLine();
                if(isPalindrome(arr[i])) {
                    b = true;
                    bArr[i] = true;
                }
            }
            for(int i=0; i < N; i++){
                if(!bArr[i]){
                    String r = reverse(arr[i]);
                    for(int j=i+1; j<N; j++){
                        if(r.equals(arr[j])){
                            bArr[i] = bArr[j] = true;
                            answer += M;
                            break;
                        }
                    }
                }
            }
            if(b) answer += M;
            wr.write("#"+test_case+" "+answer+"\n");
        }
        br.close();
        wr.flush();
        wr.close();
    }
}
