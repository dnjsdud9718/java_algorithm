package SSAFY.algo.baekjoon.t17281;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    static int N, answer = 0;
    static int[][] hitters;
    static int[] tgt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        hitters = new int[N][9];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                hitters[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        tgt = new int[9];
        answer = 0;
        perm(1, 1);
        System.out.println(answer);
        br.close();

    }

    public static void perm(int cnt, int check) {
        if (cnt == 9) {
            int[] line = makeBattingLine();
            int score = play(line);
            answer = Math.max(answer, score);
            return;
        }
        for (int i = 1; i < 9; i++) {
            if((check &(1<<i))!=0) continue;
            tgt[cnt] = i;
            perm(cnt + 1, check | (1 << i));
        }
    }
    public static int play(int[] line) {
        int out, player, sum=0;
        int one, two, three;
        player = 0;
        for (int i = 0; i < N; i++) {
            out = 3;
            one = two = three = 0;
            while (out > 0) {
                if(hitters[i][line[player]] == 0) out--;
                else if (hitters[i][line[player]] == 4) {
                    sum+=1;
                    if (one == 1) {
                        sum+=1;
                        one = 0;
                    }
                    if (two == 1) {
                        sum+=1;
                        two = 0;
                    }
                    if (three == 1) {
                        sum += 1;
                        three = 0;
                    }
                } else if (hitters[i][line[player]] == 3) {
                    if (one == 1) {
                        sum+=1;
                        one = 0;
                    }
                    if (two == 1) {
                        sum+=1;
                        two = 0;
                    }
                    if (three == 1) {
                        sum += 1;
                    }
                    three = 1;
                } else if (hitters[i][line[player]] == 2) {
                    if (three == 1) {
                        sum += 1;
                        three = 0;
                    }
                    if (two == 1) {
                        sum+=1;
                        two = 0;
                    }
                    if (one == 1) {
                        one = 0;
                        three = 1;
                    }
                    two = 1;
                }else if (hitters[i][line[player]] == 1) {
                    if (three == 1) {
                        sum += 1;
                        three = 0;
                    }
                    if (two == 1) {
                        two = 0;
                        three = 1;
                    }
                    if (one == 1) {
                        one = 0;
                        two = 1;
                    }
                    one = 1;
                }
                player = (player+1)%9;
            }
        }
        return sum;
    }
    public static int[] makeBattingLine() {
        int[] t = new int[9];
        for (int i = 1; i < 9; i++) {
            if(i==3) t[i] = tgt[0];
            if (i < 4) {
                t[i - 1] = tgt[i];
            } else {
                t[i] = tgt[i];
            }
        }
        return t;
    }

}
