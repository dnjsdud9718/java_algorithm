package SSAFY.Q1.T2;
//18662. 등차수열 만들기
/*
 * 세 개의 실수 x,y,z가 등차수열을 이룬다는 것은, y - x = z - y라는 것과 동치이다.
 * 세 정수 a,b,c가 주어진다. 당신은 음이 아닌 실수 x을 정한 뒤, 세 정수 중 하나에서 x만큼을 더하거나 뺄 수 있다.
 * 당신은 이러한 작업을 정확히 한 번 하여 a,b,c가 등차수열을 이루도록 하려고 한다. 
 * 이것이 가능하도록 하는 가장 작은 x의 값이 얼마인지 구하는 프로그램을 작성하라.
 */
import java.io.*;
import java.util.StringTokenizer;
public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            double aT = Math.abs((2*b-c)-a);
            double bT = Math.abs((double)(a+c)/2-b);
            double cT = Math.abs((2*b-a)-c);
            double answer;
            if(aT <= bT && aT <= cT) answer = aT;
            else if(bT <= aT && bT <= cT) answer = bT;
            else answer = cT;
            if(answer == 0.0) bw.write("#"+test_case+" "+0+"\n");
            else bw.write("#"+test_case+" "+String.format("%.1f", answer)+"\n");
            
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
