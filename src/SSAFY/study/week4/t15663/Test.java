package SSAFY.study.week4.t15663;

import java.util.Arrays;

public class Test {
    static int N=8, M=8, CNT=0;
    static int[] src = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
    static int[] tgt = new int[M];
    public static void main(String[] args) {
        perm(0, 0);
        System.out.println(CNT);
    }

    public static void perm(int cnt, int check) {
        if (cnt == M) {
//            System.out.println(Arrays.toString(tgt));
            CNT++;
            return;

        }
        for (int i = 0; i < N; i++) {
            if ((check & (1 << i)) == 0) {
                tgt[cnt] = src[i];
                perm(cnt + 1, check | (1 << i));
            }
        }
    }
}
