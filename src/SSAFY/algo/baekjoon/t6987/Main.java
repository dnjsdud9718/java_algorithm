package SSAFY.algo.baekjoon.t6987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

/*
    baekjoon 6987 월드컵
    결과가 데이터로 주어진 문제 -> 이 결과의 유효성 따지는 문제.
    두 팀의 시합을 모든 경우에 대해서 진행하면서 주어진 결과 데이터를 검증하려 한다.

    모든 시합의 경우의 수
    ----------------------------> 진행(단계별로 첫 경기 진행하면서 마지막 경기까지 오면서 주어진 데이터를 검증
    마지막 경기까지 모두 문제 없이 치르면 성공, 그렇지 않다면 실패(데이터가 맞지 않다 -> 다음으로 진행하지 않는다.)
    0 0 0 0 0 1 1 1 1 2 2 2 3 3 4
    1 2 3 4 5 2 3 4 5 3 4 5 4 5 5

    매 경기에서 가능한 경우(승 무 패) 완탐 진행
 */
public class Main {
    static int[] win,lose, draw; // 주어지는 결과 데이터 <= 검증 대상 <= 인덱스는 팀 구별
    static int[][] game; // 순서대로 진행할 게임 배열 <= win, lose, draw의 인덱스와 동일한 인덱스
    static boolean result;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        win = new int[6];
        lose = new int[6];
        draw = new int[6];
        game = new int[15][2];
        int idx = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 6; j++) {
                game[idx][0] = i;
                game[idx][1] = j;
                idx++;
            }
        }
//        for (int i = 0; i < 15; i++) {
//            System.out.println(Arrays.toString(game[i]));
//        }
        // 4가지 테스트 케이스
        for (int t = 0; t < 4; t++) {
            result = false;
            int[] tmp = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int k = 0;
            int sum = 0;
            for (int i = 0; i < 6; i ++) {
                sum += win[i] = tmp[k];
                sum += draw[i] = tmp[k + 1];
                sum +=lose[i] = tmp[k + 2];
                k+=3;
            }
            if (sum != 30) {
                sb.append("0 ");
                continue;
            }
//            System.out.println(Arrays.toString(win));
//            System.out.println(Arrays.toString(draw));
//            System.out.println(Arrays.toString(lose));
//            System.out.println("------------------------");

            //dfs
            dfs(0);
            if(result) sb.append("1 ");
            else sb.append("0 ");
        }
        System.out.println(sb);
        br.close();
    }

    public static void dfs(int idx) {
        if (idx == 15) {
            result = true;
            return;
        }
        // 현재 (idx)경기 진행 -> 승, 무, 패 따져 가면서
        int teamA = game[idx][0];
        int teamB = game[idx][1];

        // when teamA win => 검증 데이터에서 win[teamA]--, lose[teamB]--
        if( win[teamA] > 0 && lose[teamB] > 0){
            win[teamA]--; lose[teamB]--;
            dfs(idx + 1); // 다음 경기 보러 간다.
            win[teamA]++; lose[teamB]++; // 원복
        }
        // when teamA lose => lose[teamA]--, win[teamB]--
        if( lose[teamA] > 0 && win[teamB] > 0){
            win[teamB]--; lose[teamA]--;
            dfs(idx + 1); // 다음 경기 보러 간다.
            win[teamB]++; lose[teamA]++; // 원복
        }
        // when draw => draw[teamA]--, draw[teamB]--
        if (draw[teamA] > 0 && draw[teamB] > 0) {
            draw[teamA]--;draw[teamB]--;
            dfs(idx + 1);
            draw[teamA]++; draw[teamB]++;
        }
    }
}
