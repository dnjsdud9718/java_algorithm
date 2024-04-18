package SSAFY.study.week13.t16953;

import java.util.*;
import java.io.*;

public class Main{
    // 홀수일 때 고려해야겠다...
    static int A, B, count;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        count = 1;
        while( A < B){
            if(B%10 == 1) B/=10;
            else if(B%2 != 0) break;
            else B/=2;
            count++;
        }
        if(A == B) System.out.println(count);
        else System.out.println(-1);

        br.close();
    }
}
