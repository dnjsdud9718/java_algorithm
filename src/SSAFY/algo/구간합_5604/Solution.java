package SSAFY.algo.구간합_5604;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Solution {
    static int T;
    static long ten, start, end, sum;
    static long[] numbers;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            start = Long.parseLong(st.nextToken());
            end = Long.parseLong(st.nextToken());
            if(start == 0) start = 1;
            ten = 1;
            numbers = new long[10];
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
                if(start > end) break;
                for (int i = 1; i < 10; i++) {
                    numbers[i] += (end / 10 - start / 10 + 1) * ten;
                }
                start /= 10;
                end /= 10;
                ten *= 10;
            }
            sum = 0;
            for (int i = 1; i <= 9; i++) sum += (i*numbers[i]);
            sb.append("#").append(t).append(" ").append(sum).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
    static void calc(long x, long ten) {
        while (x > 0) {
            numbers[(int)(x%10)] += ten;
            x /= 10;
        }
    }
}
