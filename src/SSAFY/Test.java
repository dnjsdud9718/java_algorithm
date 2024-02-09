package SSAFY;

import java.util.Arrays;

public class Test {
    static int N;
    public static void main(String[] args) {
        int[] array = new int[]{1,2,3};
        N = array.length;
        Arrays.sort(array);
        while(true){
            System.out.println(Arrays.toString(array));
            if(!np(array))break;
        }        
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
    public static void swap(int[] array, int i, int j){
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
}