package Q6Graph.T1194;

// 1194 달이 차오른다. 가자
// 민식이는 미로 탈출 시도. 미로는 직사각형이고, 미로는 다음과 같이 구성되어져 있다.
// 빈 칸: 언제나 이동할 수 있다 ->  '.'
//벽: 절대 이동할 수 없다 -> '#'
//열쇠: 언제나 이동할 수 있다. 이 곳에 처음 들어가면 열쇠를 잡는다. ('a', 'b', 'c', 'd','e','f')
//문: 대응하는 열쇠가 있을 때만 이동할 수 있다.('A', 'B', 'C', 'D', 'E', 'F')
//민식의 현재 위치: 빈 곳이고, 민식이가 현재 서 있는 곳이다. ('0')
//출구: '1'
//
////한 번의 움직임은 현재 위치에서 수평이나 수직으로 한 칸 이동하는 것이다.
//미로 탈출하는데 걸리는 이동 횟수 최솟값을 구하는 프로그램 작성
//N -> 세로, M -> 가로
//같은 타입의 열쇠가 여러 개일 수도 있고, 문도 마찬가지다. 그리고, 문에 대응하는 열쇠가 없을 수도 있다.
//'0'은 1개, '1'은 적어도 한 개 있다. 열쇠는 여러 번 사용할 수 있다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

// 열쇠를 가지고 문에 도달하는 경우, 열쇠를 안 가지고 문에 도달하는 경우를 고려해야 한다.
public class Main {
    static int N, M; // row size, column size
    static int[][] src;
    static int wall = '#';
//    static int road = '.';
    static int exit = '1';
    static int start = '0';
    static int sr, sc;
    static int[][][] visited;
    static int[] dr = {1, 0 , -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        src = new int[N][M];
        for(int i=0; i<N; i++){
            src[i] = Stream.of(br.readLine().split("")).mapToInt(c -> (int)c.charAt(0)).toArray();
        }
        //  시작점 찾기 -> '0'
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(src[i][j] == start){
                    sr = i;
                    sc = j;
                }
            }
        }
        Queue<Point> queue = new LinkedList<>();
//      내가 현재 어떤 키셋을 가지고 움직이는 지 생각.
        visited = new int[N][M][(1<<6)]; // 모든 key set에 대해 고려해야 한다. 키는 'a','b','c,'d','e','f'
        queue.add(new Point(sr, sc, 0));
        answer = -1;

        while(!queue.isEmpty()){
            Point current = queue.poll();
            if(src[current.row][current.col] == exit){
                answer = visited[current.row][current.col][current.key];
                break;
            }
            for(int i=0; i<4; i++){
                int nr = current.row + dr[i];
                int nc = current.col + dc[i];
//              미로를 벗어나거나 벽을 마주했을 경우 -> 다음으로...
                if(nr < 0 || nr == N || nc < 0 || nc == M || src[nr][nc] == wall) continue;
                char next = (char)src[nr][nc];
                // 문
                if(next >= 'A' && next <= 'F'){
                    // key를 가지고 있나?
                    // key 가지고 있다.
                    if((current.key & (1<<(next-'A'))) != 0) {
                        // 해당 키 셋을 가지고 이미 방문한 적이 있다. -> 현재 조건과 똑같은 이전의 탐색이 존재 -> 더 이상 탐색할 필요 없다.
                        if (visited[nr][nc][current.key] != 0) continue;
                        // 진행
                        visited[nr][nc][current.key] = visited[current.row][current.col][current.key] + 1;
                        queue.add(new Point(nr, nc, current.key));
                    }
//                  해당 key를 가지고 있지 않다 -> 더 이상 탐색 불가
                }
                // 키
                else if(next >= 'a' && next <= 'f'){
                    // 해당 키 이미 가지고 있는가? 참고-> 동일한 키가 여러 곳에 있을 수 있다.
                    // 없다.
                    if((current.key & (1<<(next-'a'))) == 0) {
//                      여기가 중요하다 -> 키 셋이 변경되었기 때문에 visited[nr][nc][변경된 key set]에 값을 넣어줘야 한다.
                        visited[nr][nc][(current.key | (1 << (next - 'a')))] = visited[current.row][current.col][current.key] + 1;
//                      큐에 삽입 시 -> 현재 키 셋에 키를 추가해야 한다.(current.key | (1 << (next - 'a')))
                        queue.add(new Point(nr, nc, (current.key | (1 << (next - 'a')))));
                    }else{ // 해당 키를 가지고 있다. -> 같은 타입의 열쇠가 여러 개 있을 수 있다.
                        if(visited[nr][nc][current.key] != 0) continue; // 이미 해당 키 셋을 가지고 방문한 적이 있다.
                        visited[nr][nc][current.key] = visited[current.row][current.col][current.key]+1;
                        queue.add(new Point(nr, nc, current.key));
                    }
                }else{ // 그 외
                    if(visited[nr][nc][current.key] != 0) continue;
                    visited[nr][nc][current.key] = visited[current.row][current.col][current.key]+1;
                    queue.add(new Point(nr, nc, current.key));
                }
                
            }
        }
        System.out.println(answer);
        br.close();
    }
}
class Point{
    int row;
    int col;
    int key;
    public Point(int row, int col, int key) {
        this.row = row;
        this.col = col;
        this.key = key;
    }
}

