package SSAFY.algo.상호의배틀필드1873;

/*
    문자	의미
    .	평지(전차가 들어갈 수 있다.)
    *	벽돌로 만들어진 벽
    #	강철로 만들어진 벽
    -	물(전차는 들어갈 수 없다.)
    ^	위쪽을 바라보는 전차(아래는 평지이다.)
    v	아래쪽을 바라보는 전차(아래는 평지이다.)
    <	왼쪽을 바라보는 전차(아래는 평지이다.)
    >	오른쪽을 바라보는 전차(아래는 평지이다.)


    문자	동작
    U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
    D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
    L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
    R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
    S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.

    전차가 이동을 하려고 할 때, 만약 게임 맵 밖이라면 전차는 당연히 이동하지 않는다.
    전차가 포탄을 발사하면, 포탄은 벽돌로 만들어진 벽 또는 강철로 만들어진 벽에 충돌하거나 게임 맵 밖으로 나갈 때까지 직진한다.
    만약 포탄이 벽에 부딪히면 포탄은 소멸하고, 부딪힌 벽이 벽돌로 만들어진 벽이라면 이 벽은 파괴되어 칸은 평지가 된다.
    강철로 만들어진 벽에 포탄이 부딪히면 아무 일도 일어나지 않는다.
    게임 맵 밖으로 포탄이 나가면 아무런 일도 일어나지 않는다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int H, W;
    static char[][] src;
    static char[] dir = {'^', 'v', '<', '>'};
    static char[] cmds = {'U', 'D', 'L', 'R'};
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int cy, cx, cd;
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
            cy = cx = cd = -1;
            for (int i = 0; i < H; i++) {
                String s = br.readLine();
                for (int j = 0; j < s.length(); j++) {
                    src[i][j] = s.charAt(j);
                    if (cd != -1) continue;
                    for (int k = 0; k < dir.length; k++) {
                        if (dir[k] == src[i][j]) {
                            cy = i;
                            cx = j;
                            cd = k;
                            break;
                        }
                    }
                }
            }
            br.readLine(); // # of cmd
            String cmd = br.readLine();
            for (int i = 0; i < cmd.length(); i++) {
//                System.out.print(cmd.charAt(i)+" ");
                command(cmd.charAt(i));
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

    public static void command(char c) {
        int ny, nx;
        int d = changeDir(c);
        if (d == -1) { // c == 'S'
            // 포탄 발사
            ny = cy;
            nx = cx;
            while (true) {
                ny = ny + dy[cd];
                nx = nx + dx[cd];
                if (!canGo(ny, nx)) break;
                if (src[ny][nx] == '#') break;
                if (src[ny][nx] == '*') {
                    src[ny][nx] = '.';
                    break;
                }
            }
        } else {
            cd = d;
            ny = cy + dy[cd];
            nx = cx + dx[cd];
            if(!canGo(ny,nx) || src[ny][nx] != '.') {
                src[cy][cx] = dir[cd]; // 가지 못한다면 현재 위치의 방향성을 바꾸고 나가야 한다.
                return;
            }
            src[cy][cx] = '.';
            src[ny][nx] = dir[cd];
            cy = ny;
            cx = nx;
        }
    }
    public static int changeDir(char c) {
        for (int i = 0; i < cmds.length; i++) {
            if(c == cmds[i]) return i;
        }
        return -1;
    }
    public static boolean canGo(int y, int x) {
        // 지도를 벗어날 수 없다.
        return y >= 0 && y != H && x >= 0 && x != W;
    }
}