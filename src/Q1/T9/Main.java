package Q1.T9;
/*
 * 12891 silver5
 * 
 * DNA 비밀번호
 * 
 * 아래 경우 생각해야 함.
 * 1(N) 1(P)
 * A
 * 1 0 0 0 (G C G T)
 * 
 */
import java.io.*;
import java.util.*;
public class Main {
    public static int calcMyArrIdx(char ch) {
        switch(ch) {
            case 'A': 
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            case 'T':
                return 3;
            default:
                return -1;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        char[] dnaArr = br.readLine().toCharArray();
        st = new StringTokenizer(br.readLine());
        int[] checkArr = new int[4];
        for(int i=0; i<4; i++) checkArr[i] = Integer.parseInt(st.nextToken());
        int [] myArr = new int[4];
        int answer = 0;
        for(int i=0; i<P; i++){
            myArr[calcMyArrIdx(dnaArr[i])]++;
        }

        if(myArr[0] >= checkArr[0] && myArr[1] >= checkArr[1] && myArr[2] >= checkArr[2] && myArr[3] >= checkArr[3]) answer++;
        int idx = 0;
        while(idx < N-P && dnaArr.length != 1){
            myArr[calcMyArrIdx(dnaArr[idx])]--;
            myArr[calcMyArrIdx(dnaArr[idx+P])]++;
            if(myArr[0] >= checkArr[0] && myArr[1] >= checkArr[1] && myArr[2] >= checkArr[2] && myArr[3] >= checkArr[3]) answer++;
            idx++;
        };

        System.out.println(answer);

        

        br.close();
    }
}
