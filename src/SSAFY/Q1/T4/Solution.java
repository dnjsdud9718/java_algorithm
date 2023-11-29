package SSAFY.Q1.T4;
//1206. [S/W 문제해결 기본] 1일차 - View
import java.io.*;
import java.util.StringTokenizer;
public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        for(int test_case = 1; test_case<=10; test_case++){
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] arr = new int[N];
            long sum = 0;
            for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
            for(int i=2; i<N-2; i++){
                int left = arr[i-2] > arr[i-1] ? arr[i-2] : arr[i-1];
                int right = arr[i+2] > arr[i+1] ? arr[i+2] : arr[i+1];
                if(left >= right && arr[i] > left){
                    sum += arr[i] - left;
                }else if(right >= left && arr[i] > right){
                    sum += arr[i] -right;
                }
            }
            wr.write("#"+test_case+" "+sum+"\n");
        }
        br.close();
        wr.flush();
        wr.close();
    }
}
