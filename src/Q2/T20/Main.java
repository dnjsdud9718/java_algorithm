package Q2.T20;
/**
 * 1517 버블소트에서 swap이 몇번일어 났는가?? 골드1
 * 
 * result type을 long으로 해야되는 문제!!...!!!!
 */
import java.io.*;
import java.util.StringTokenizer;
public class Main {
    private static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) arr[i] = Integer.valueOf(st.nextToken());
        result = 0;
        sort(arr, 0, N-1);
        for(int x : arr) System.out.printf("%d ", x);
        System.out.println();
        System.out.println(result);

        br.close();
    }
    public static void sort(int[] arr, int s, int e){
        if(s < e) {
            int m = (s+e)/2;
            sort(arr, s, m);
            sort(arr, m+1, e);
            merge(arr, s, m, e);
        }
    }
    public static void merge(int[] arr, int s, int m, int e) {
        int n1 = m-s+1;
        int n2 = e-m;
        int[] A = new int[n1+1];
        int[] B = new int[n2+1];
        A[n1] = B[n2] = Integer.MAX_VALUE;
        for(int i=0; i<n1; i++) A[i] = arr[s+i];
        for(int i=0; i<n2; i++) B[i] = arr[m+1+i];

        for(int k=s, i=0, j=0; k<e+1; k++){
            if(A[i] <= B[j]) arr[k] = A[i++];
            else {
                arr[k] = B[j++];
                if(k < m+j){
                    result += m + j - k;
                }
            }
        }
    }
}
