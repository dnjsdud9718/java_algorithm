package SSAFY.Q1.T3;
//1859. 백만 장자 프로젝트
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int test_case = Integer.parseInt(br.readLine());
        for(int i=1; i<= test_case; i++){
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());
            long answer = 0;
            for(int j=0; j<N; j++){
                arr[j] = Integer.parseInt(st.nextToken());
            }
            int max = arr[N-1];
            for(int j=N-2; j >= 0; j--){
                if(max > arr[j]) answer += max-arr[j];
                else max = arr[j];
            }

            wr.write("#"+i+" "+answer+"\n");
        }

        br.close();
        wr.flush();
        wr.close();
    }
}
