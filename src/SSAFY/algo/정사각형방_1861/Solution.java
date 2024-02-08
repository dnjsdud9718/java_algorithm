package SSAFY.algo.정사각형방_1861;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[][] map;
    static int T, N, NO, count;
    static int memoi[][];
    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            memoi = new int[N][N]; // memoi 초기화
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 풀이
            NO = 0;
            count = 1;

            // 모든 좌표에서 다 시도해보고 그 각각의 결과 중 최선을 선택
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int no = map[i][j];
                    int cnt = dfs(i, j);

                    // 문제처리
                    if (cnt > count) {
                        count = cnt;
                        NO = no;
                    } else if (cnt == count) {
                        NO = Math.min(NO, no);
                    }

                }
            }
            sb.append("#").append(t).append(" ").append(NO).append(" ").append(count).append("\n");
        }
        System.out.println(sb.toString());
    }

    // visit 필요 없음
    // y,x 좌표, no: 시작 번호, cnt 방문 횟수
    static int dfs(int y, int x) {
        // 이미 다른 dfs에 의해 memoi가 계산되어 있다면 그걸 재사용
        if (memoi[y][x] != 0) {
            return memoi[y][x];
        }

        // 이후 조건에 맞는 더 갈 수 있는 곳 방문
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if (ny >= 0 && nx >= 0 && ny < N && nx < N && map[y][x] + 1 == map[ny][nx]) {
                return memoi[y][x] = dfs(ny, nx) + 1; // 현재칸의 +1 값이 다음칸의 값(전개가 가능한경우)
            }
            // 모든 방의 번호가 다르기 때문에, 1차이가 나는 방을 찾으면 break 해도 됨

        }
        return memoi[y][x] = 1; // 더이상 y,x에서 갈 곳이 없다.
    }

}
