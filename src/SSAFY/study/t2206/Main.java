package SSAFY.study.t2206;
/*
 2206 벽 부수고 이동하기
 NxM 행렬로 표현되는 맵이 있다. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다. 당신은 (1,1)에서(N,M)의
 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다. 최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이때 시작하는 칸과 끝
 나는 칸도 포함해서 센다.
 만약에 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동하여도 된다(제약 조건)

 한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸이다.
 맵이 주어졌을 때, 최단 경로를 구하는 프로그램을 작성하시오.

 입력
 첫째 줄에 N(1 <= N <= 1,000), M(1 <= M <= 1,000)이 주어진다. 다음 N개의 줄에 M개의 숫자로 맵이 주어진다. (1,1)과 (N,M)은 항상 0이라고 가정

 출력
 첫째 줄에 최단 거리 출력, 불가능 시 -1 출력

 5
 00000
 11110
 00000
 01111 
 00010

 -> 17

 BFS
 어떤 좌표(x,y)에 도착했을 때, 고려할 요소
 1. (x,y)가 벽인가 아닌가
 2. (x,y)에 도달하기 전 경로에서 벽을 부수고 왔나 그렇지 않은가
 3. (x,y)에 도달했을 때, 현재 경로의 상태(벽을 부수고 온 경우:1, 그렇지 않은 경우: 0)와 동일한 경로가 이전에 존재하였는가
 일반적인 BFS 방문처리 시 문제가 생긴다 -> 좌표(x,y)를 방문처리할 경우, 이후에 도달 가능한 경로가 도달하지 못하는 상황 발생
 가능한 경우의 수 8가지
 1. (x,y)가 벽, (x,y) 도달 전 벽 부섰다, 현재 경로 상태와 동일한 이미 먼저 방문한 경로 존재 
 -> STOP
 2. (x,y)가 벽, (x,y) 도달 전 벽 부섰다, 현재 경로 상태와 동일한 이미 먼저 방문한 경로 존재 X
 -> STOP
 3. (x,y)가 벽, (x,y) 도달 전 벽 안 부섰다, 현재 경로 상태와 동일한 이미 먼저 방문한 경로 존재
 -> STOP
 4.★★★★ (x,y)가 벽, (x,y) 도달 전 벽 안 부섰다, 현재 경로 상태와 동일한 이미 먼저 방문한 경로 존재 X ★★★
 -> BFS
 5. (x,y)가 벽X, (x,y) 도달 전 벽 부섰다, 현재 경로 상태와 동일한 이미 먼저 방문한 경로 존재 
 -> STOP
 6.★★★★ (x,y)가 벽X, (x,y) 도달 전 벽 부섰다, 현재 경로 상태와 동일한 이미 먼저 방문한 경로 존재 X  ★★★
 -> BFS
 7. (x,y)가 벽X, (x,y) 도달 전 벽 안 부섰다, 현재 경로 상태와 동일한 이미 먼저 방문한 경로 존재
 -> STOP
 8.★★★★ (x,y)가 벽X, (x,y) 도달 전 벽 안 부섰다, 현재 경로 상태와 동일한 이미 먼저 방문한 경로 존재 X  ★★★
 -> BFS
 4,6,8에 대해서만 조건 처리하면 되겠다.
★★★★ (x,y)가 벽, (x,y) 도달 전 벽 안 부섰다, 현재 경로 상태와 동일한 이미 먼저 방문한 경로 존재 X ★★★
★★★★ (x,y)가 벽X, (x,y) 도달 전 벽 부섰다, 현재 경로 상태와 동일한 이미 먼저 방문한 경로 존재 X  ★★★
★★★★ (x,y)가 벽X, (x,y) 도달 전 벽 안 부섰다, 현재 경로 상태와 동일한 이미 먼저 방문한 경로 존재 X  ★★★
(x,y)가 벽인 경우, 도달 전 상태: 벽 안 부섰다, 현재 상태와 동일한 이미 존재하는 경로 여부 파악

(x,y)가 벽인 아닌 경우, 도달 전 상태 무시, 현재 상태와 동일한 이미 존재하는 경로 여부 파악
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;
import java.util.*;
public class Main {
    static int[][] D = {
        {0,1}, // right
        {1,0}, // down
        {0,-1}, // left
        {-1,0} // up
    };
    static int N;
    static int M;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputs[0];
        M = inputs[1];
        int[][] map = new int[N][M];
        for(int i=0; i<N; i++){
            char[] row = br.readLine().toCharArray();
            for(int j=0; j<M; j++){ 
                map[i][j] = row[j]-'0';
                // System.out.print(map[i][j]);
            }
            // System.out.println();
        }
        System.out.println(bfs(map));
        
        br.close();
    }   
    public static int bfs(int[][] map){
        Queue<int[]> queue = new LinkedList<>();
        int [][][] visited = new int[N][M][2];
        queue.add(new int[]{0,0,0}); // 시작위치까지 오면서 벽을 만나지 않았다.
        visited[0][0][0] = 1; // 시작위치까지 오면서 벽을 만나지 않았다. 
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int y = current[0];
            int x = current[1];
            int wall = current[2];
            if(y==N-1 && x==M-1){
                return visited[N-1][M-1][wall];
            }
            for(int d=0; d<D.length; d++){
                int ny = y+D[d][0];
                int nx = x+D[d][1];
                if(ny >= 0 && nx >= 0 && ny < N && nx < M){
                    // (x,y)가 벽인 아닌 경우, 도달 전 상태 무시, 현재 상태와 동일한 이미 도달했던 경로 여부 파악
                    if(map[ny][nx] == 0 && visited[ny][nx][wall] == 0){
                        visited[ny][nx][wall] = visited[y][x][wall]+1;
                        queue.add(new int[]{ny,nx,wall});
                    }                   
                    // (x,y)가 벽인 경우, 도달 전 상태: 벽 안 부섰다, 현재 상태와 동일한 이미 도달했던 경로 여부 파악
                    // 왜 마지막 조건은 필요 없는가??? visited[ny][nx][wall] == 0
                    if(map[ny][nx] == 1 && wall == 0 && visited[ny][nx][wall] == 0){
                        visited[ny][nx][wall+1] = visited[y][x][wall]+1;
                        queue.add(new int[]{ny,nx, wall +1});
                    }
                    
                }
            }
        }
        return -1;
    }
}
