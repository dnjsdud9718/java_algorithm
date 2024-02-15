package SSAFY.algo.상호의배틀필드1873;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[] dy = {-1, 0, 1, 0}; // up(0), left(1), down(2), right(3)
    static int[] dx = {0, -1, 0, 1};
    static char road = '.';
    static char wall = '*';
    static char steel = '#';
    static char water = '-';
    static char up = '^';
    static char down = 'v';
    static char left = '<';
    static char right = '>';
    static char U = 'U';
    static char D = 'D';
    static char L = 'L';
    static char R = 'R';
    static char S = 'S';
    static int H, W;
    static char[][] src;
    static int y, x, d;

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            src = new char[H][W];
            for (int i = 0; i < H; i++) {
                String s = br.readLine();
                for (int j = 0; j < s.length(); j++) {
                    src[i][j] = s.charAt(j);
                    if (src[i][j] == up || src[i][j] == down || src[i][j] == left || src[i][j] == right) {
                        y = i; x = j;
                        switch (src[i][j]) {
                            case '^':
                                d = 0;
                                break;
                            case 'v':
                                d = 2;
                                break;
                            case '<':
                                d = 1;
                                break;
                            case '>':
                                d = 3;
                                break;
                        }
                    }
                }
            }

            int N = Integer.parseInt(br.readLine());
            String cmd = br.readLine();


            for (int k = 0; k < cmd.length(); k++) {
                for (int i = 0; i < H; i++) {
                    for (int j = 0; j < W; j++) {
                        System.out.print(src[i][j]+" ");
                    }
                    System.out.println();
                }
                simul(cmd.charAt(k));
                System.out.println("--------");
            }

            sb.append("#").append(t).append(" ");
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    sb.append(src[i][j]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
        br.close();
    }

    public static void simul(char ch) {
        int ny;
        int nx;
        switch(ch) {
            case 'U':
                ny = y + dy[0];
                nx = x + dx[0];
                if(ny < 0 || ny == H || nx < 0 || nx == W) return;
                if(src[ny][nx] == wall || src[ny][nx] == steel || src[ny][nx] == water) return;
                src[y][x] = road;
                src[ny][nx] = up;
                d = 0;
                y=ny;
                x=nx;
                break;
            case 'D':
                ny = y + dy[2];
                nx = x + dx[2];
                if(ny < 0 || ny == H || nx < 0 || nx == W) return;
                if(src[ny][nx] == wall || src[ny][nx] == steel || src[ny][nx] == water) return;
                src[y][x] = road;
                src[ny][nx] = down;
                d = 2;
                y=ny;
                x=nx;
                break;
            case 'R':
                ny = y + dy[3];
                nx = x + dx[3];
                if(ny < 0 || ny == H || nx < 0 || nx == W) return;
                if(src[ny][nx] == wall || src[ny][nx] == steel || src[ny][nx] == water) return;
                src[y][x] = road;
                src[ny][nx] = down;
                d = 3;
                y=ny;
                x=nx;
                break;
            case 'L':
                ny = y + dy[1];
                nx = x + dx[1];
                if(ny < 0 || ny == H || nx < 0 || nx == W) return;
                if(src[ny][nx] == wall || src[ny][nx] == steel || src[ny][nx] == water) return;
                src[y][x] = road;
                src[ny][nx] = down;
                d = 1;
                y=ny;
                x=nx;
                break;
            case 'S':
                while (true) {
                    ny = y + dy[d];
                    nx = x + dx[d];
                    if(ny < 0 || ny == H || nx < 0 || nx == W || src[ny][nx] == steel) break;
                    if (src[ny][nx] == wall) {
                        src[ny][nx] = road;
                        break;
                    }
                }
                break;
        }
    }
}
