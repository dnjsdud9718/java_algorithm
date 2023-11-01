package Q3.T27;

/**
 * 2178
 * 미로 탐색하기
 * 
 */
import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    private static int N;
    private static int M;
    public static void main(String[] args) throws IOException{
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        int [][] miro = new int[N][M];
        for(int i=0; i<N; i++){
            String s = br.readLine();
            miro[i] = Stream.of(s.split("")).mapToInt(Integer::parseInt).toArray();
        }

        bfs(miro, new int[]{0,0});
        System.out.println(miro[N-1][M-1]);
        br.close();
    }
    public static void bfs(int [][] map, int[] start){
        boolean[][] visited = new boolean[N][M];
        int [] dX = new int[]{1, 0, -1, 0}; //axis x 우 상 좌 하
        int [] dY = new int[]{0, -1, 0, 1};  //axis y
        visited[start[0]][start[1]] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()) {
            int [] current = queue.poll();
            for(int i=0; i<4; i++){
                int x = dX[i] + current[1];
                int y = dY[i] + current[0];
                if(x >= 0 && x < M && y >= 0 && y < N){
                    if(!visited[y][x] && map[y][x] != 0){
                        visited[y][x] = true; 
                        queue.add(new int[]{y,x});
                        map[y][x] = map[current[0]][current[1]]+1;
                    }
                }
            }

        }

    }
}
