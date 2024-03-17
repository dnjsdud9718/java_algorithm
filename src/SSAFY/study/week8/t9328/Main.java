package SSAFY.study.week8.t9328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    baekjoon 9328 열쇠
    삼차원 방문처리는 키의 수가 크기 때문에 불가능...다른 방법 필요
    만약 특정 문에 대한 키를 가지지 못한 상태에서 문에 도착 시 상태 저장이 필요

 */
public class Main {
    static int T, R, C, answer, key, prevKey;
    static int[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static String s;
    static Deque<int[]> queue = new ArrayDeque<>();
    static final int MAX_DOOR = 10_000;
    static Door[] doors; // key가 없어서 열지 못한 문들만 저장할 것.
    static int dLen; // doors 배열의 길이
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map = new int[R][C];
            for (int i = 0; i < R; i++) {
                s = br.readLine();
                for (int j = 0; j < C; j++) {
                    char c = s.charAt(j);
                    if(c == '*') map[i][j] = 1; // 벽
                    else if(c == '.') map[i][j] = 0; // 빈 곳
                    else if(c == '$') map[i][j] = 2; // 문서
                    else map[i][j] = (int) c; // 키 또는 문
                }
            }
            s = br.readLine();
            key = 0; // 내가 가지고 있는 키를 비트 마스킹으로 관리.
            if (s.charAt(0) != '0') {
               for(int i=0; i<s.length();i++){
                   int k = s.charAt(i)-'a';
                   key = key | (1 << k);
               }
            }
            queue.clear();
            doors = new Door[MAX_DOOR + 1];
            dLen = 0;
            answer = 0;
            // map의 가장 자리에 대한 처리. bfs를 위해 배열에 담을 것이다.
            // 방문한 위치에 대해서 벽으로 처리할 것이다(visited 배열 대신), 방문한 곳 다시 방문할 필요 없다.
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if(i != 0 && j != 0 && i != R-1 && j != C-1) continue;
                    // 벽
                    if(map[i][j] == 1) continue;
                    // 빈 공간
                    else if(map[i][j] == 0) {
                        map[i][j] = 1;
                        queue.add(new int[]{i, j});
                    }
                    // 문서
                    else if (map[i][j] == 2) {
                        answer++;
                        map[i][j] = 1;
                        queue.add(new int[]{i, j});
                    }
                    // 열쇠
                    else if (map[i][j] >= 97 && map[i][j] <=122) { // a~z : 97~122
                        key = key | (1 << (map[i][j] - 97)); // 키 추가.
                        map[i][j] = 1;
                        queue.add(new int[]{ i, j});
                    }
                    // 문
                    else if (map[i][j] >= 65 && map[i][j] <= 90) { // A~Z : 65~90
                        if ((key & (1 << (map[i][j] - 65))) != 0) { // 해당 문에 대한 키 보유
                            map[i][j] = 1;
                            queue.add(new int[]{i, j});
                        } else { // 해당 문에 대한 키 미보유 -> doors배열로 관리
                            doors[dLen++] = new Door(i, j, map[i][j],false);
                        }
                    }
                }
            }
            bfs(); // bfs 1회 수행.
            prevKey = 0;
            // bfs() 수행 전과 후 키가 바뀌지 않았다면, 더 이상 갈 수 있는 경로가 없는 것.
            // bfs() 수행 후 키가 갱신 되었다면, 해당 키 집합을 가지고 문을 열 수 있는 문들을 탐색(doors배열 탐색)
            while (prevKey != key) {
                prevKey = key;
                for (int i = 0; i < dLen; i++) {
                    if(doors[i].open) continue;
                    Door door = doors[i];
                    if ((key & (1 << (door.val - 65))) != 0) {
                        door.open = true;
                        map[door.r][door.c] = 1;
                        queue.add(new int[]{door.r, door.c});
                    }
                }
                bfs();
            }

//            System.out.println("answer = " + answer);
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
    static void bfs() {
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(nr < 0 || nr == R || nc < 0 || nc == C || map[nr][nc] == 1) continue;
                if (map[nr][nc] == 0) {
                    map[nr][nc] = 1;
                    queue.add(new int[]{nr, nc});
                } else if (map[nr][nc] == 2) {
                    answer++;
                    map[nr][nc] = 1;
                    queue.add(new int[]{nr, nc});
                } else if (map[nr][nc] >= 97 && map[nr][nc] <= 122) {
                    key = key | (1 << (map[nr][nc] - 97));
                    map[nr][nc] = 1;
                    queue.add(new int[]{nr, nc});
                } else if (map[nr][nc] >= 65 && map[nr][nc] <= 90) {
                    if ((key & (1 << (map[nr][nc] - 65))) != 0) {
                        map[nr][nc] = 1;
                        queue.add(new int[]{nr, nc});
                    } else {
                        doors[dLen++] = new Door(nr, nc, map[nr][nc], false);
                    }
                }
            }
        }
    }

    static class Door {
        int r, c, val;
        boolean open;

        public Door(int r, int c, int val, boolean open) {
            this.r = r;
            this.c = c;
            this.val = val;
            this.open = open;
        }
    }
}
