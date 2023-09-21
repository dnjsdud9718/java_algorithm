package Q2.T20;
/**
 * Merge Sort, 합병정렬, O(NlogN)
 * Divide & Conquer
 * 재귀!!
 */
public class Merge {
    public static void main(String[] args) {
        int [] arr = new int[10];
        for(int i=0; i<10; i++) arr[i] = (int)(Math.random()*100);
        for(int x: arr) System.out.printf("%d ", x);
        System.out.println();

        sort(arr, 0, 9);

        for(int x: arr) System.out.printf("%d ", x);

    }
    public static void sort(int[] arr, int s, int e){
        if(s < e){
            int M = (s+e)/2;
            sort(arr, s, M);
            sort(arr, M+1, e);
            merge(arr, s, M, e);
            
        }
    }
    public static void merge(int[] arr, int s, int m, int e){
        int n1 = m-s+1;
        int n2 = e-m;
        int[] A = new int[n1+1];
        int[] B = new int[n2+1];
        int idx =0;
        for(int i=s; i<=m; i++) A[idx++] = arr[i];
        A[idx] = Integer.MAX_VALUE;
        idx = 0;
        for(int i = m+1; i<=e; i++) B[idx++] = arr[i];
        B[idx] = Integer.MAX_VALUE;

        int a = 0, b = 0;
        for(int i=s; i<=e; i++){
            if(A[a] <= B[b]){
                arr[i] = A[a++];
            }else arr[i] = B[b++];
        }
    }
}
