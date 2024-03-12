package SSAFY.study.week8.t4276;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static String left, right;
    static int lLen, rLen;
    static int cnt;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            left = st.nextToken();
            lLen = left.length();
            right = st.nextToken();
            rLen = right.length();
            if(left.charAt(0) == '-') break;
            cnt = 0;
            while(left.charAt(lLen-1) != '0')
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
