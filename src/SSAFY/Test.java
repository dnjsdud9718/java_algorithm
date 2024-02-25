package SSAFY;

import java.util.Arrays;
import java.util.concurrent.ConcurrentNavigableMap;

public class Test {
    static int N, M, COUNT;
    static int[] tgt;
    static int[] array;
    public static void main(String[] args) {
        array = new int[]{5,1,4,2 };
        N = array.length;
        M = 2;
        tgt = new int[M];
        COUNT = 0;
        Arrays.sort(array);
//        perm(0, 0);
//        perm2(0);
//        comb(0, 0);
//        comb2(0, 0);
        subset(0, 0);
//        while(true){
//            System.out.println(Arrays.toString(array));
//            COUNT++;
//            if(!np2())break;
//        }
        System.out.println(COUNT);
    }

    public static void subset(int idx, int check) {
        if (idx == N) {
            System.out.print("{ ");
            for (int i = 0; i < N; i++) {
                if((check & (1<<i)) != 0) System.out.print(array[i]+" ");
            }
            System.out.println(" }");
            COUNT++;
            return;
        }
        subset(idx + 1, check | (1 << idx));
        subset(idx+1, check);
    }
    public static void comb2(int srcIdx, int tgtIdx) {
        if (tgtIdx == M) {
            System.out.println(Arrays.toString(tgt));
            COUNT++;
            return;
        }
        for (int i = srcIdx; i < N; i++) {
            tgt[tgtIdx] = array[i];
            comb2(i, tgtIdx + 1);
        }
    }
    public static void comb(int srcIdx, int tgtIdx) {
        if (tgtIdx == M) {
            System.out.println(Arrays.toString(tgt));
            COUNT++;
            return;
        }
        for (int i = srcIdx; i < N; i++) {
            tgt[tgtIdx] = array[i];
            comb(i + 1, tgtIdx + 1);
        }
    }
    public static boolean np2() {
        int i = N - 1;
        while(i>0 && array[i-1] >= array[i]) --i;
        if(i==0) return false;
        int j = N-1;
        while(array[i-1] >= array[j]) --j;
        swap(i-1, j);
        int k=N-1;
        while (i < k) {
            swap(i++, k--);
        }
        return true;
    }
    public static boolean np(int[] array){
        int i = N-1;
        // step1 -> 뒤에서 부터 꼭대기 i 찾기
        while(i > 0 && array[i-1] >= array[i]) --i;
        // i == 0의 의미 -> 가장 큰 오름차순 순열
        if(i==0) return false;
        // step2 -> i-1과 교환할 j 찾기 -> j는 맨뒤에서 꼭대기(i)까지 중 i-1번째 값보다 큰 수 중 가장 작은 값.
        // 꼭대기부터 맨 뒤는 내림차순 된 상태다
        int j = N-1;
        while(array[i-1]>=array[j]) --j;
        // swap(i-1, j)
        swap(array, i-1, j);
        //step4 -> 꼭대기(i)부터 마지막까지 오름차순 정렬
        int k = N-1;
        while(i < k){
            swap(array, i++, k--);
        }
        return true;
    }

    public static void swap(int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
    public static void perm2(int cnt) {
        if (cnt == M) {
            System.out.println(Arrays.toString(tgt));
            COUNT++;
            return;
        }
        for (int i = 0; i < N; i++) {
            tgt[cnt] = array[i];
            perm2(cnt + 1);
        }
    }
    public static void perm(int cnt, int check) {
        if (cnt == M) {
            System.out.println(Arrays.toString(tgt));
            COUNT++;
            return;
        }
        for (int i = 0; i < N; i++) {
            if((check & (1<<i)) != 0) continue;
            tgt[cnt] = array[i];
            perm(cnt + 1, check | (1 << i));
        }
    }

    public static void swap(int[] array, int i, int j){
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
}