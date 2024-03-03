package SSAFY.algo.baekjoon.t16637;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, nLen, oLen, max;
    static String s;
    static int[] num;
    static char[] oper;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        s = br.readLine();
        num = new int[20];
        oper = new char[20];

        nLen = oLen = 0;
        for (int i = 0; i < N; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num[nLen++] = c - '0';
            } else {
                oper[oLen++] = c;
            }
        }
        max = Integer.MIN_VALUE;
        subset(0, 0);
        System.out.println(max);

//        System.out.println(Arrays.toString(num));
//        System.out.println(Arrays.toString(oper));


        br.close();
    }

    static void subset(int idx, int check) {
        if (idx == oLen) {
            if (inner(check)) {
//                print(check);
                // 계산
                logic(check);
            }
            return;
        }
        subset(idx + 1, check | (1 << idx));
        subset(idx + 1, check);
    }

    static void logic(int check) {
        int[] copNum = Arrays.copyOf(num, nLen);
        // 비트마스크가 1인 친구들 먼저 계산해준다.
        for (int i = 0; i < oLen; i++) {
            if ((check & (1 << i)) != 0) {
                int result = 0;
                switch(oper[i]) {
                    case '*':
                        result = copNum[i] * copNum[i + 1];
                        break;
                    case '+':
                        result = copNum[i] + copNum[i + 1];
                        break;
                    case '-':
                        result = copNum[i] - copNum[i + 1];
                        break;
                }
                copNum[i] = result;
            }
        }
        // 비트마스크가 0인 친구들을 계산해준다.
        for (int i = 0; i < oLen; i++) {
            if ((check & (1 << i)) != 0) {
                copNum[i + 1] = copNum[i];
            } else {
                int result = 0;
                switch(oper[i]) {
                    case '*':
                        result = copNum[i] * copNum[i + 1];
                        break;
                    case '+':
                        result = copNum[i] + copNum[i + 1];
                        break;
                    case '-':
                        result = copNum[i] - copNum[i + 1];
                        break;
                }
                copNum[i+1] = result;
            }
        }
        max = Math.max(max, copNum[nLen - 1]);
    }


    // 중첩 괄호 체크
    static boolean inner(int check) {
        int prev = (check & 1) != 0 ? 1 : 0;
        for (int i = 1; i < oLen; i++) {
            int c = (check & (1 << i)) != 0 ? 1 : 0;
            if(c == 1 && 1 == prev) return false;
            prev = c;
        }
        return true;
    }

    static void print(int check) {
        for (int i = oLen - 1; i >= 0; i--) {
            System.out.print((check & (1<<i)) != 0 ? 1 : 0);
            System.out.print(" ");
        }
        System.out.println();
    }
}
