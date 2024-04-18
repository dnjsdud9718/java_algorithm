package SSAFY.study.week13.t21177;

import java.io.*;
import java.util.*;

/*
    가지고 있는 카드의 합이 score가 된다
    하지만 수가 연속되는 카드를 소지 시 해당 시퀀스의 최솟값만 적용


*/
public class Main
{
    static int N, sum;
    static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        sum = arr[0];
        for(int i=1; i<N; i++){
            if(arr[i]-arr[i-1] > 1){
                sum+=arr[i];
            }
        }
        System.out.println(sum);
        br.close();
    }
}
