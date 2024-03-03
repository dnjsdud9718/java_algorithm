package SSAFY.algo.baekjoon.t17281;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main3 {
    static int[] tgt = new int[9];
    static int tgtIdx=0;
    static int N;
    static int[][] innings;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        innings = new int[N][9];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                innings[i][j] = Integer.parseInt(st.nextToken()); // i번째 이닝 j번 선수(타순x)의 타석
            }
        }
        br.close();
        tgt[3] = 0;
        perm(0, (1 << 0));
        System.out.println(max);

    }

    static void play() {
        // 게임 시뮬레이션
        int out; // 아웃 카운트
        int order = 0; // 0번 타자 타순
        int player; // 타자
        int heat;
        int base1, base2, base3;
        int score = 0;
        for (int i = 0; i < N; i++) { // 각 이닝
            base1 = base2 = base3 = 0;
            out = 3;
            while (out != 0) {
                player = tgt[order];
                order = (order + 1) % 9;
                heat = innings[i][player];
                switch (heat) {
                    case 1:
                        if (base3 == 1) {
                            score++;
                            base3 = 0;
                        }
                        if (base2 == 1) {
                            base3 = 1;
                            base2 = 0;
                        }
                        if (base1 == 1) {
                            base2=1;
                        }
                        base1 = 1;
                        break;
                    case 2:
                        if (base3 == 1) {
                            score++;
                            base3 = 0;
                        }
                        if (base2 == 1) {
                            score++;
                            base2 = 0;
                        }
                        if (base1 == 1) {
                            base3 = 1;
                            base1 = 0;
                        }
                        base2 = 1;
                        break;
                    case 3:
                        if (base3 == 1) {
                            score++;
                            base3 = 0;
                        }
                        if (base2 == 1) {
                            score++;
                            base2 = 0;
                        }
                        if (base1 == 1) {
                            score++;
                            base1 = 0;
                        }
                        base3 = 1;
                        break;
                    case 4:
                        if(base3==1) score++;
                        if(base2==1) score++;
                        if(base1==1) score++;
                        score++;
                        base1 = base2 = base3 = 0;
                        break;
                    default:
                        out--;
                }
            }
        }
        max = Math.max(max, score);
    }
    static void perm(int cnt, int check) {
        if (cnt == 9) {
            play();
            return;
        }
        if(cnt == 3) {
            perm(cnt + 1, check);
            return;
        }
        for (int i = 1; i < 9; i++) {
            if((check & (1<<i))!=0) continue;
            tgt[cnt] = i;
            perm(cnt + 1, check | (1 << i));
        }
    }
}
