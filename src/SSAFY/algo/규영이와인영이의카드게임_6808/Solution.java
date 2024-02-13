package SSAFY.algo.규영이와인영이의카드게임_6808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Solution {
    static int N, T, W, L;
    static int[] src;
    static int[] A;
    static int[] B;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            src = new int[20];
            for (int i = 0; i < 9; i++) {
                src[Integer.parseInt(st.nextToken())] = 1;
            }
            A = new int[9];
            B = new int[9];
            int a=0, b = 0;
            for (int i = 1; i <= 18; i++) {
                if(src[i] == 1) A[a++]=i;
                else B[b++]=i;
            }

            W = L = 0;
            // next permutation
            // 1. sort
            Arrays.sort(B);
            do{
                int sumA = 0;
                int sumB = 0;

                for (int i = 0; i < 9; i++) {
                    if(A[i] > B[i]) sumA += (A[i]+B[i]);
                    else sumB += (A[i]+B[i]);
                }
                if(sumA > sumB)  W++;
                else L++;
            }while(np(B));


            sb.append("#").append(t).append(" ").append(W).append(" ").append(L).append("\n");
        }

        System.out.println(sb.toString());
        br.close();
    }

    public static boolean np(int[] array) {
        // 꼭대기 찾기
        int i = array.length-1;
        while(i > 0 && array[i-1] >= array[i]) i--;
        if(i==0) return false;
        // swap할 j 찾기
        int j = array.length-1;
        while(array[i-1] >= array[j]) j--;

        // swap
        swap(array, i - 1, j);

        // 오름차순 정렬
        int e = array.length-1;
        while (i < e) {
            swap(array, i++, e--);
        }
        return true;
    }

    public static void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
}
