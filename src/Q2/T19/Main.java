package Q2.T19;

/**
 * 11004 silver 5
 * K번째 수
 * ★ pivot을 잘 설정하는 것이 중요 (lt + rt) /2
 * 퀵정렬은 최악의 경우 O(n2)!!!
 */

import java.io.*;
import java.util.StringTokenizer;
public class Main {
    private static int K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken())-1;

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());

        sort(arr, 0, N-1);
        //for(int x: arr) System.out.printf("%d ", x);
        System.out.println(arr[K]);
        br.close();
    }
    public static void sort(int[] arr, int lt, int rt) {
        if(lt < rt) {
            int start = lt-1;
            int end = rt;
            int M = (int)(lt+rt)/2; // ★★★★★★★★★★★★★★★★★★★
            swap(arr, M, rt);
            int pivot = arr[rt];
            do{
                do{
                    start++;
                }while(start < rt && arr[start] < pivot);
                do {
                    end--;
                }while(end >= lt && arr[end] > pivot);
                if(start < end) {
                    swap(arr, start, end);
                }
            }while(start < end);

            if(start < rt){
                swap(arr, start, rt);
            }
            
            if(start > K){
                sort(arr, lt, start-1);
            }else if(start < K){
                sort(arr, start+1, rt);
            }            
        }
    }
    public static void swap(int[] arr, int a, int b){
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
