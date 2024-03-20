package SSAFY.study.week9.t1436;

import java.util.Scanner;

public class Main {
    static int N, value,answer, cnt;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        value = 0;
        answer = 0;
        cnt = 0;
        if (N != 1) {
            while (cnt < N) {
                value++;
                if (check(value)) {
//                    System.out.println(answer);
                    answer = value;
                    cnt++;
                }

            }
        } else {
            answer = 666;
        }
        System.out.println(answer);
        sc.close();
    }

    public static  boolean check(int x) {
        int cnt =0;
        int max = 0;
        while (x > 0) {
            if(x%10 == 6) cnt++;
            else {
                max = Math.max(max, cnt);
                cnt = 0;
            }
            x /= 10;
        }
        max = Math.max(max, cnt);
        return max >= 3;
    }
}
