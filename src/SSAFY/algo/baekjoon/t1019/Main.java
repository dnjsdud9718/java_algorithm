package SSAFY.algo.baekjoon.t1019;

import java.util.Scanner;

public class Main {
    static int start, end;
    static long ten;
    static long[] numbers = new long[10];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        start = 1;
        end = sc.nextInt();
        ten = 1;
        while (start <= end) {
            while (start % 10 != 0 && start <= end) {
                calc(start, ten);
                start++;
            }
            if(start > end) break;
            while (end % 10 != 9 && start <= end) {
                calc(end, ten);
                end--;
            }
            for (int i = 0; i < 10; i++) {
                numbers[i] += (end / 10 - start / 10 + 1) * ten;
            }
            start /= 10;
            end /= 10;
            ten *= 10;

        }
        for (int i = 0; i < 10; i++) {
            sb.append(numbers[i]).append(" ");
        }
        System.out.println(sb);
    }

    static void calc(long x, long ten) {
        while (x > 0) {
            numbers[(int)x%10] += ten;
            x /= 10;
        }
    }
}
