package SSAFY.study.week11.t1644;

import java.util.Scanner;

/*
    if a <= b && a * b = c then a <= √c
 */
public class Main2 {
    static int N;
    static boolean[] eratos;
    static int[] arr;
    static int aLen, prev, current, sum, answer;
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        N = sc.nextInt();
        eratos = new boolean[N + 1];
        arr = new int[N+1];
        aLen = 0;
        for (int i = 2; i <= Math.sqrt(N); i++) {
            if (!eratos[i]) {
                for (int j = i + i; j <= N; j += i) {
                    eratos[j] = true;
                }
            }
        }
        for (int i = 2; i <= N; i++) {
            if (!eratos[i]) {
                arr[aLen++] = i;
            }
        }
        prev = 0;
        current = 0;
        sum = 0;

        while (prev <= current && current <= aLen) {
//            System.out.println(prev + " " + current + " " + sum);
            if (N == sum) {
                answer++;
                sum += arr[current++];
                sum -= arr[prev++];
            } else if (N < sum) {
                sum -= arr[prev++];
            } else { // N > sum
                sum += arr[current++];
            }
//            System.out.println(prev + " " + current + " " + sum);
//            System.out.println("----------------------------");
        }
        System.out.println(answer);
        sc.close();
    }
}
