package SSAFY.permutation;

import java.util.Arrays;
import java.util.Scanner;
import java.util.zip.CRC32;

public class Combination_NP {
    static int N, K, COUNT = 0;
    static int[] src;
    static int[] tgt;
    static int[] index;
    // 조합에 NP적용
//    5C3
//    0 0 1 1 1 ~ 1 1 1 0 0
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        src = new int[N];
        tgt = new int[K];
        index = new int[N];
        for (int i = 0; i < N; i++) src[i] = i + 1;
        for(int i=N-K; i < N; i++) index[i] = 1;
        System.out.println(Arrays.toString(index));
        while(true){
            int tgtIdx = 0;
            for(int i=0; i<K; i++){
                if (index[i] == 1) {
                    tgt[tgtIdx++] = src[i];
                }
            }
            System.out.println(Arrays.toString(tgt)+ " : " + Arrays.toString(index));
            if(!np(index)) break;
        }
        sc.close();
    }

    static boolean np(int[] array) {
        int i = N-1;
        while(i > 0 && array[i-1] >= array[i]) --i;
        if(i==0) return false;
        int j = N-1;
        while(array[i-1]>= array[j]) --j;

        swap(array, i - 1, j);

        int k = N-1;
        while(i < k){
            swap(array, i++, k--);
        }
        return true;
    }

    static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
