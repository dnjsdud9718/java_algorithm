package Q2.T21;

import java.util.Queue;
import java.util.LinkedList;
public class Radix{
    public static void main(String[] args) {
        int arr[] = new int[20];
        for(int i=0; i<20; i++) arr[i] = (int)(Math.random() * 1000);
        for(int x: arr) System.out.printf("%d ", x);
        System.out.println();

        radix(arr, 3);

        for(int x: arr) System.out.printf("%d ", x);
    }
    public static void radix(int[] arr, int N){
        int[] tmp = new int[arr.length];
        int jarisu = 1;
        for(int i=0; i<N; i++){
            int[] bucket = new int[10];
            for(int j=0; j<arr.length; j++){
                bucket[(arr[j]/jarisu)%10]++;
            }
            for(int j=1; j<10; j++){
                bucket[j] += bucket[j-1];
            }
            for(int j=arr.length-1; j>=0; j--){
                tmp[bucket[(arr[j]/jarisu)%10]-1] = arr[j];
                bucket[(arr[j]/jarisu)%10]--;
            }
            for(int j=0; j<arr.length; j++) arr[j] = tmp[j];
            jarisu *= 10;
        }
    }
}