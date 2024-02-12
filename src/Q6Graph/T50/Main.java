package Q6Graph.T50;

/*
 * 여행 가자 1976 골드 5 
 * 동환이는 친구들과 함께 여행을 가려고 한다. 한국에는 도시가 N개 있고 임의의 두 도시 사이에 길이 있을 수도,
 * 없을 수도 있다. 동환이는 여행 계획이 주어졌을 때 이 계획대로 여행할 수 있는지를 알아보려 한다.
 * 물론 중간에 다른 도시를 경유해 여행할 수도 있다. 예를 들어 도시가 5개 있고, A-B, B-C, A-D, B-D, E-A의 길이 있고,
 * 동환이의 여행 계획이 E,C,B,C,D라면 E-A-B-C-B-C-B-D라는 여행 경로를 이용해 계획대로 여행할 수 있다. 도시의
 * 개수와 도시 간의 연결 여부가 주어져 있고, 동환이의 여행 계획에 속한 도시들이 순서대로 주어졌을 떄 계획대로 여행이
 * 가능한지를 판별하는 프로그램을 작성하시오.
 * 
 * 1번째 줄에 도시의 수 N(<=200)이 주어진다. 2번째 줄에 여행 계획에 속한 도시들의 수 M(<=1000)이 주어진다. 다음
 * N개의 줄에는 N개의 정수가 주어진다. i번째 줄의 j번째 tnsms i번 도시와 j번 도시의 연결 정보를 의미한다. 1이면 
 * 연결된 것이고, 0이면 연결되지 않은 것이다. A와 B가 연결됐으면 B와 A도 연결된 것이다. 마지막 줄에는 여행 계획이
 * 주어진다. 도시 번호는 1에서 N까지 차례대로 매겨져 있다.
 * 
 * 3
 * 3
 * 0 1 0
 * 1 0 1
 * 0 1 0
 * 1 2 3
 * 답 : YES
 */
import java.io.*;
import java.util.stream.Stream;

public class Main {
    static int[] parent;
    static int[][] path;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // # of cities
        int M = Integer.parseInt(br.readLine()); // # of paths
        parent = new int[N+1];
        for(int i=1; i<=N; i++) parent[i] = i;
        path = new int[N+1][];
        for(int i=1; i<=N; i++){
            path[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int[] plan = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        br.close();
        for(int i=1; i<=N; i++){
            for(int j=i+1; j<=N; j++){
                if(path[i][j-1] == 1) union(i, j);
            }
        }
        int p = find(plan[0]);
        boolean flag = true;
        for(int i=1; i<plan.length; i++){
            if(p != find(plan[i])) {
                flag = false;
                break;
            }
        }
        if(!flag) System.out.println("NO");
        else System.out.println("YES");
    }    
    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    static void union(int x, int y){
        int X = find(x);
        int Y = find(y);
        if(X < Y) parent[Y] = X;
        else parent[X] = Y;
    }
}
