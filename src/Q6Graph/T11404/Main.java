package Q6Graph.T11404;
// 백준 11404
// 문제
// n(2 ≤ n ≤ 100)개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 m(1 ≤ m ≤ 100,000)개의 버스가 있다.
// 각 버스는 한 번 사용할 때 필요한 비용이 있다.

// 모든 도시의 쌍 (A, B)에 대해서 도시 A에서 B로 가는데 필요한 비용의 최솟값을 구하는 프로그램을 작성하시오.

// 입력
// 첫째 줄에 도시의 개수 n이 주어지고 둘째 줄에는 버스의 개수 m이 주어진다.
// 그리고 셋째 줄부터 m+2줄까지 다음과 같은 버스의 정보가 주어진다. 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다.
// 버스의 정보는 버스의 시작 도시 a, 도착 도시 b, 한 번 타는데 필요한 비용 c로 이루어져 있다.
// 시작 도시와 도착 도시가 같은 경우는 없다. 비용은 100,000보다 작거나 같은 자연수이다.

// 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다. (시작 시 중요한 고려 사항)

// 출력
// n개의 줄을 출력해야 한다. i번째 줄에 출력하는 j번째 숫자는 도시 i에서 j로 가는데 필요한 최소 비용이다.
// 만약, i에서 j로 갈 수 없는 경우에는 그 자리에 0을 출력한다.

import java.io.*;
import java.util.stream.Stream;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        long[][] grid = new long[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(i!=j) grid[i][j] = Integer.MAX_VALUE;
            }
        }
        for(int i=0 ; i<M; i++){
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int u = input[0]-1;
            int v = input[1]-1;
            int w = input[2];
            grid[u][v] = Math.min(grid[u][v], w);
        }
        //플로이드 와샬
        for(int k=0; k<N; k++){
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    grid[i][j] = Math.min(grid[i][j],  grid[i][k]+grid[k][j]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(grid[i][j] >= Integer.MAX_VALUE) grid[i][j] = 0;
                sb.append(grid[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
}
