package SSAFY.study.week2.t1074;

import java.util.Scanner;

public class Main2 {
    static int N, R, C, cnt, ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        R = sc.nextInt();
        C = sc.nextInt();
        cnt = 0;
        ans = 0;
        if (R != 0 || C != 0) {
            N = (1 << N);
            int x = 0;
            int y = 0;
            while (true) {
                if (N == 1) break;
                N /= 2;
                if (R < y + N && C < x + N) {
                    ;
                } else if (R < y + N && C >= x + N) {
                    ans += N*N*1;
                    x += N;
                } else if (R >= y + N && C < x + N) {
                    ans += N * N * 2;
                    y += N;
                } else {
                    ans += N * N * 3;
                    y += N;
                    x += N;
                }
            }
        }
        System.out.println(ans);
        sc.close();
    }
}
