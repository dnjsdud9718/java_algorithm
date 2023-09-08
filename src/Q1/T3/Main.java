package Q1.T3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.io.IOException;
public class Main {
    /**
     * 11659 실버5
     * 부분합.
     */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());
        int Q = Integer.valueOf(st.nextToken());
        st = new StringTokenizer(br.readLine());

        int [] iArr = new int[N+1];
        iArr[0] = 0;
        for(int i=1; i<=N; i++){
            iArr[i] = iArr[i-1] + Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<Q; i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            System.out.println(iArr[q] - iArr[p-1]);
        }

        br.close();
    }
}
