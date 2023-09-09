package Q1.T9;

/**
 * 12891 silver 5
 * DNA 비밀번호
 * 이번에는 다른 방법으로 해보자.
 * dnaArr의 길이를 사용하지 않는 방법
 * 
 */

import java.io.*;
import java.util.*;
public class Main2 {
    public static int getMyArrayIdx(char ch) {
        switch(ch){
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            case 'T':
                return 3;
            default :
                return -1;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());
        int P = Integer.valueOf(st.nextToken());

        char[] dnaArr = br.readLine().toCharArray();
        st = new StringTokenizer(br.readLine());
        int [] checkArr = new int[4];
        int [] myArr = new int[4];

        for(int i=0; i<4; i++) checkArr[i] = Integer.valueOf(st.nextToken());
        for(int i=0; i<P; i++) myArr[getMyArrayIdx(dnaArr[i])]++;

        int answer = 0;
        if(myArr[0] >= checkArr[0] && myArr[1] >= checkArr[1] && myArr[2] >= checkArr[2] && myArr[3] >= checkArr[3]) answer++;

        for(int i=P; i<N; i++){
            myArr[getMyArrayIdx(dnaArr[i])]++;
            myArr[getMyArrayIdx(dnaArr[i-P])]--;
            if(myArr[0] >= checkArr[0] && myArr[1] >= checkArr[1] && myArr[2] >= checkArr[2] && myArr[3] >= checkArr[3]) answer++;
        }

        System.out.println(answer);




        br.close();
    }
}
