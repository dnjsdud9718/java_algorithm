package SSAFY.algo.최적경로1247;

/*
    김대리는 회사에서 출발하여 냉장고 배달을 위해 N명의 고객을 방문하고 자신의 집으로 돌아가려 한다.
    회사와 집의 위치, 그리고 고객의 위치는 이차원 정수 좌표(x,y)로 주어지고 (0 <= x <= y <= 100)
    두 위치 (x1,y1), (x2,y2) 사이의 거리는 |x1-x2| + |y1-y2|로 계산된다.
    회사, 집, 각 고개들의 좌표는 모두 다르다.
    회사에서 출발하여 N명의 고객을 모두 방문하고 집으로 돌아오는 경로 중 가장 짧은 경로를 찾고자 한다.
    이 문제는 가장 짧은 경로를 ‘효율적으로’ 찾는 것이 목적이 아니다.
    여러분은 모든 가능한 경로를 살펴서 해를 찾아도 좋다.
    모든 경우를 체계적으로 따질 수 있으면 정답을 맞출 수 있다.
    고객의 수 N은 2≤N≤10

    풀이 -> 백트레킹
    S(회사) E(집)
    A,B,C <= 고객
    경우의 수
    S -> A -> B -> C -> E
    S -> A -> C -> B -> E
    S -> B -> A -> C -> E
    S -> B -> C -> A -> E
    S -> C -> A -> B -> E
    S -> C -> B -> A -> E
    ★★★ 모든 경우는 항상 회사에서 출발하여 모든 고객을 방문하고 집에 돌아온다
    
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N; // 고객의 수
    static int[] y; // y좌표 배열
    static int[]x; // x좌표 배열
    static int sy, sx, ey, ex; // 회사 좌표, 집 좌표
    static boolean[][] visited; // 방문 처리 배열
    static int min; // 최솟값을 구해야 한다.
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            y = new int[N + 2];
            x = new int[N + 2];
            visited = new boolean[101][101];
            for (int i = 0; i < N+2; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                x[i] = a;
                y[i] = b;
            }
            sy = y[0];
            sx = x[0];
            ey = y[1];
            ex = x[1];

//            System.out.println(Arrays.toString(y));
//            System.out.println(Arrays.toString(x));
            min = Integer.MAX_VALUE;
            dfs(sy, sx, 0, 0);

            sb.append("#").append(t).append(" ").append(min).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

//    백트레킹 -> 방문처리가 중요하다.
//    가지치기 할 수 있네.
//    distance를 미리 구해서 사용하는 것도 좋겠다(메모이제이션)
    public static void dfs(int cy, int cx, int sum, int cnt) {
        visited[cy][cx] = true; // 들어올 때 방문처리
        if (cnt == N) { // 시작좌표에서 총 N번 이동하면 모든 고객을 방문했다.
            // 마지막 고객에서부터 집까지 거리 계산
            sum = sum + Math.abs(cy - ey) + Math.abs(cx - ex);
            // 해당 경로가 최단 경로이면 min을 갱신
            min = Math.min(min, sum);
            //return 안 적었으닌까 문제없이 진행되었네...
//            return;
        }
        for (int i = 2; i < N+2; i++) { // 방문 가능한 고객을 확인한다.
            int ny = y[i];
            int nx = x[i];
            if (!visited[ny][nx]) {
                // 현재 좌표에서 다음 좌표까지의 거리 계산
                int t = Math.abs(ny - cy) + Math.abs(nx - cx);
                if(sum + t > min) continue;
                dfs(ny, nx, sum + t, cnt + 1);
            }
        }
        // 나갈 때 방문 해제 -> 다시 방문할 수 있기 때문이다.
        visited[cy][cx] = false;
    }

}
