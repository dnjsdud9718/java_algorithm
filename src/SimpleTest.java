import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SimpleTest {
    static int N, R;
    static int[] src;
    static int[] tgt;
    static int tgtIdx;
    static int[] index; // for combination
    public static void main(String[] args) {
        N=4;
        src = new int[]{4, 2, 1, 6};
//        nPn
//        Arrays.sort(src);
//        while (true) {
//            System.out.println(Arrays.toString(src));
//            if(!np(src)) break;
//        }
//        nCr
//        R = 2;
//        index = new int[N];
//        tgt = new int[R];
//        Arrays.sort(src);
//        for(int i=N-R; i<N; i++) index[i] = 1;
//        while (true) {
//            tgtIdx = 0;
//            for (int i = 0; i < N; i++) {
//                if(index[i] == 1) tgt[tgtIdx++] = src[i];
//            }
//            System.out.println(Arrays.toString(tgt)+" - " +Arrays.toString(index));
//            if(!np(index)) break;
//        }
        R = 3;
        Arrays.sort(src);
        swapNP(src, 0);
    }
//    next permutation -> nPn
//    np를 응용해서 nCr도 가능하다
    public static boolean np(int[] array){
        int i=N-1;
//        step1 -> 뒤에서부터 꼭대기 찾기(i)
        while(i > 0 && array[i-1] >= array[i]) --i;
//        i == 0 -> 사전 순 가장 큰 순열
        if(i == 0) return false;
//        step2 -> i-1번째 요소 값과 swap할 j찾기
//        j -> N-1 ~ i 중 i-1번째 요소보다 큰 수 중 가장 작은 값의 위치
//        위 조건을 만족하는 수 반드시 하나 존재 -> i번째 요소는 항상 i-1번째 요소보다 크다.
//        i ~ N-1까지 항상 내림차순 정렬된 상태다
        int j = N-1;
        while(array[i-1] >= array[j]) j--;
        swap(array, i - 1, j);
        //step3 -> i ~ N-1 오름차순 정렬
        int k = N-1;
        while(i < k) swap(array, i++, k--);
        return true;
    }

    public static void swapNP(int[] array, int idx) {
        if (idx == R) {
            for(int i=0; i<R; i++){
                System.out.print(array[i] + " ");
            }
            System.out.println();
            return;
        }
        for (int i = idx; i < N; i++) {
            swap(array, idx, i);
            swapNP(array, idx + 1);
            swap(array, idx, i);
        }

    }
    public static void swap(int[] array,int i, int j){
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
}

