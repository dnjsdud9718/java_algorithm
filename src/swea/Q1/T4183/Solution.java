package swea.Q1.T4183;
/*
 수영대회 결승전(완전탐색 + 구현) D4
 예선전에서 승리한 삼성이는 결승전까지 지출하게 되었다. 결승전인 만큼 수영장이 아닌 바다에서 진행되었다.
 바다 전체를 사용 할 수 없기에 가로 N 세로 N만큰의 공간만 사용하여 진행하도록 하였다. 이 공간을 벗어나면 실격처리가 되므로 공간 안에서
 가장 빠른 길을 찾아야 한다. 이 공간에는 삼과 같은 지나갈 수 없는 장애물과, 주기적으로 사라졌다 나타나는 소용돌이 같은 장애물이 존재한다.
 (섬과 같은 장애물은 1로 표시, 소용돌이 같은 장애물은 2로 표시)
 소용돌이는 생성되고 2초 동안 유지되다가 1초동안 잠잠해진다. 예를 들어 0초에 생성된 소용돌이는 0초, 1초까지 유지되고 2초에 사라지게 된다.
 또한 3초, 4초에는 생성되고 5초에는 사라진다.(단, 한번 통과한 소용돌이 위에서는 머물러 있을 수 있다.)
 이런 바다에서 삼성이를 우승시키려면 어떤 경로로 보내야 될까?
 똑똑한 여러분들은 한번에 그 경로를 찾을 수 있었다. 해당 경로로 수영을 했을때 삼성이는 몇초만에 골인 할 수 있을까?
    5 //N
    0 0 0 0 0
    0 0 0 1 0
    0 0 0 1 0
    2 2 1 1 0
    0 0 0 0 0
    4 0 //시작점
    2 0 //도착점

    이 경우에는 (4,0) 에서 시작, 소용돌이가 존재하므로 이동하지 않는다 ( 0초 )
    (4,0) 아직 소용돌이가 사라지지 않았으므로 제자리에 있다 ( 1초)
    (4,0) 이제 소용돌이가 사라지는 것을 보았고 건너려고한다 ( 2초)
    (3,0) 소용돌이를 통과하였고 바다위를 수영하고 있다 (3초)
    (2,0) 도착지에 도착하였다 (4초)

    문제 해결 방법
    가중치가 1일 때 최단 경로를 구하는 BFS를 응용.
    소용돌이 처리가 중요!!! 3의 배수일 때 소용돌이를 건널 수 있다.
    큐가 아니라 우선순위 큐를 만들어 큐에서 작은 값이 먼저 나오도록 구현.

 */
import java.io.*;
import java.util.*;
import java.util.stream.Stream;
public class Solution{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for(int ts=1; ts<=T; ts++){
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
            boolean[][] visited = new boolean[N][N];
            for(int i=0; i<N; i++){
                map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            int[] start = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] end = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); 

            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->{
                return map[o1[0]][o1[1]] - map[o2[0]][o2[1]];
            });

            int[] dX = {0, 1, 0, -1}; //하 우 상 좌
            int[] dY = {1, 0, -1, 0}; //하 우 상 좌
            map[start[0]][start[1]] = 0;
            visited[start[0]][start[1]] = true;
            pq.add(start);
            boolean b = false;
            while(!pq.isEmpty()){
                int[] current = pq.poll();
                // System.out.println("---"+current[0]+" "+current[1]+" "+map[current[0]][current[1]]);
                if(current[0] == end[0] && current[1] == end[1]){
                    // bw.write("#"+ts+" "+map[end[0]][end[1]]+"\n");
                    b = true;
                    break;
                }
                
                int now = map[current[0]][current[1]];
                for(int i=0; i<4; i++){
                    int x = current[1] + dX[i];
                    int y = current[0] + dY[i];
                    if(x >= 0 && x < N && y >= 0 && y <N){
                        if(!visited[y][x]){
                            visited[y][x] = true;
                            if(map[y][x] != 1){
                                if(map[y][x] == 2){ // 이 부분이 핵심
                                    int t = now; // now + 1 시간에서 소용돌이를 건널 수 있는지 판단해야 한다.
                                    while((t+1)%3 != 0){
                                        t++;
                                    }
                                    map[y][x] = t+1;
                                }else{
                                    map[y][x] = now+1;
                                }
                                pq.add(new int[]{y,x});
                                // System.out.printf("- %d %d %d\n", y, x, map[y][x]);
                            }
                        }
                    }
                }
            }
            if(b) bw.write("#"+ts+" "+map[end[0]][end[1]]+"\n");
            else bw.write("#"+ts+" "+-1+"\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}