package SSAFY.algo.baekjoon.t6987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

/*
    baekjoon 6987 월드컵
    월드컵 조별 최종 예선에서는 6개국으로 구성된 각 조별로 동일한 조에 소속된 국가들과 한 번씩, 각 국가별로 총 5번의 경기를 치른다.
    조별리그가 끝난 후, 기자가 보내온 각 나라의 승, 무승부, 패의 수가 가능한 결과인지를 판별하려고 한다.
    네 가지의 결과가 주어질 때 각각의 결과에 대하여 가능하면 1, 불가능하면 0을 출력하는 프로그램을 작성하시오.
    입력
    첫째 줄부터 넷째 줄까지 각 줄마다 6개국의 결과가 나라별로 승, 무승부, 패의 순서로 빈칸을 하나 사이에 두고 18개의 숫자로 주어진다.
    승, 무, 패의 수는 6보다 작거나 같은 자연수 또는 0이다.
    출력
    입력에서 주어진 네 가지 결과에 대하여 가능한 결과는 1, 불가능한 결과는 0을 빈칸을 하나 사이에 두고 출력한다.
 */
public class Main2 {
    static final int TS = 4;
    static final int TEAM = 6;
    static int[] src;
    static int[][] matches;
    static int[] win;
    static int[] draw;
    static int[] lose;
    static boolean result;
    static StringBuilder sb = new StringBuilder();
    static int t;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (t = 0; t < TS; t++) {
            win = new int[TEAM];
            draw = new int[TEAM];
            lose = new int[TEAM];
            src = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            // input을 정리하자
            int sum = 0;
            for (int i = 0; i < TEAM; i++) {
                sum += win[i] = src[i * 3];
                sum += draw[i] = src[i * 3 + 1];
                sum += lose[i] = src[i * 3 + 2];
            }
            if (sum != 30) {
                sb.append("0 ");
                continue;
            }

        //      모든 팀은 같은 조에 소속된 팀과 반드시 한 번 경기를 치른다.
        //      0 0 0 0 0 1 1 1 1 2 2 2 3 3 4
        //      1 2 3 4 5 2 3 4 5 3 4 5 4 5 5
            matches = new int[15][2];
            int idx = 0;
            for (int i = 0; i < TEAM - 1; i++) {
                for (int j = i + 1; j < TEAM; j++) {
                    matches[idx][0] = i;
                    matches[idx][1] = j;
                    idx++;
                }
            }
            // 완탐 + 백트래킹을 하면서 모든 경우를 분석해야 한다.
            result = false;
            dfs(0, 0);
            if (result) {
                sb.append(1).append(" ");
            } else sb.append(0).append(" ");
        }
        System.out.println(sb.toString());
        br.close();
    }
    public static void dfs(int idx, int sum) {
        if (idx == matches.length) {
//            System.out.println("t = " + t);
//            System.out.println("sum = " + sum);
            if(sum == 30) result = true;
            return;
        }

        int teamA = matches[idx][0];
        int teamB = matches[idx][1];
        if (win[teamA] > 0 && lose[teamB] > 0) {
            win[teamA]--; lose[teamB]--;
            dfs(idx + 1, sum+2);
            win[teamA]++; lose[teamB]++;
        }
        if (draw[teamA] > 0 && draw[teamB] > 0) {
            draw[teamA]--; draw[teamB]--;
            dfs(idx+1, sum+2);
            draw[teamA]++; draw[teamB]++;
        }
        if (win[teamB] > 0 && lose[teamA] > 0) {
            win[teamB]--; lose[teamA]--;
            dfs(idx + 1, sum+2);
            win[teamB]++; lose[teamA]++;
        }
    }
}
