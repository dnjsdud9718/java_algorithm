package SSAFY.algo.baekjoon.t17281;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    17281 야구 백준
    타선을 선정 -> 순열
    단, 0번 선수는 항상 4번 타자 -> N!을 (N-1)!로 줄일 수 있다.
    
 */
public class Main2 {
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
            for (int j = 0; j < 9; j++) { // 등번호라고 생각하자. 0번 ~ 8번 (타순이 아니다)
                hitters[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        tgt = new int[9];
        tgt[3] = 0;
        answer = 0;
        perm(0, (1<<0));
        System.out.println(answer);
        br.close();

    }

    public static void perm(int cnt, int check) {
        if (cnt == 9) {
            int score = play(tgt);
            answer = Math.max(answer, score);
//            System.out.println(Arrays.toString(tgt));
            return;
        }
        if (cnt == 3) {
            perm(cnt + 1, check);
            return;
        }
        for (int i = 1; i < 9; i++) {
            if((check &(1<<i))!=0) continue;
            tgt[cnt] = i;
            perm(cnt + 1, check | (1 << i));
        }
    }
    public static int play(int[] line) { // line 타순
        int score = 0;
        int[] base = new int[8]; // H -> 1 -> 2 -> 3 -> H -> H -> H -> H
        int player = 0; // 타순,  line[player] : player번 타순의 타자의 위치(등번호)
        for (int i = 0; i < N; i++) { // N이닝 동안 진행.
            int out = 3;
            Arrays.fill(base, 0);
            while (out > 0) {
                if(hitters[i][line[player]] == 0 ) out--;
                else{
                    base[0] = 1; // 진루 확정
                    for (int j = 3; j >= 0; j--) {
                        base[j + hitters[i][line[player]]] = base[j];
                        base[j] = 0;
                    }
                    for (int j = 4; j < 8; j++) {
                        score += base[j];
                        base[j] = 0;
                    }
                }
                player = (player + 1) % 9;
            }
        }
        return score;
    }

}
