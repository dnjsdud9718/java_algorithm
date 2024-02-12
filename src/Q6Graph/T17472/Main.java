package Q6Graph.T17472;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/*
 17472 다리 만들기 2
 섬으로 이뤄진 나라가 있고, 모든 섬을 다리로 연결하려고 한다. 이 나라의 지도는 N X M 크기의 이차원 격자로 나타낼 수 있고, 격자의 각 칸은 
 땅이거나 바다이다.
 섬은 연결된 땅이 상하좌우로 붙어 있는 덩어리를 말한다.
 다리는 바다에만 건설할 수 있고, 다리의 길이는 다리가 격자에서 차지하는 칸의 수이다. 다리를 연결해서 모든 섬을 연결하려고 한다. 섬 A에서
 다리를 통해 섬 B로 갈 수 있을 때, 섬 A와 B를 연결되었다고 한다. 다리의 양 끝은 섬과 인접한 바다 위에 있어야 하고, 한 다리의 방향이
 중간에 바뀌면 안된다. 또, 다리의 길이는 2이상이어야 한다. 다리의 방향이 중간에 바뀌면 안되기 때문에, 다리의 방향은 가로 또는 세로가 
 될 수 밖에 없다. 방향이 가로인 다리는 다리의 양 끝이 가로 방향으로 섬과 인접해야 하고, 방향이 세로인 다리는 세로 방향으로 양 끝이
 섬과 인접해야 한다.
 섬 A와 B를 연결하는 다리가 중간에 섬 C와 인접한 바다를 지나가는 경우에 섬 C는 A와 B와 연결되어있는 것이 아니다.
 다리가 교차하는 경우가 있을 수도 있다. 교차하는 다리의 길이를 계산할 때는 각 칸이 각 다리의 길이에 모두 포함되어야 한다.
 나라의 정보가 주어졌을 때, 모든 섬을 연결하는 다리 길이의 최솟값을 구하라.
    1 <= N, M <=10 
    3 <= NxM <=100
    2 <= 섬의 개수 <=6
    7 8 N M
    0 0 0 0 0 0 1 1
    1 1 0 0 0 0 1 1
    1 1 0 0 0 0 0 0
    1 1 0 0 0 1 1 0
    0 0 0 0 0 1 1 0
    0 0 0 0 0 0 0 0
    1 1 1 1 1 1 1 1 출력 -> 9

    1. 섬의 넘버링 -> DFS
    2. 모든 간선 찾기(가로, 세로)
    3. MST
    4. 출력
 */
public class Main {
    static int N, M;
    static int[][] src;
    static StringTokenizer st;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0 , -1};
    static PriorityQueue<Node> pq;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        src = new int[N][M];
        for(int i=0; i<N; i++){
            src[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        // 1. 섬 번호 넘버링
        int num = 2;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(src[i][j] == 1){
                    dfs(i, j, num++);
                }
            }
        }
        // MST에서 사용하는 parent[]
        parent = new int[num];
        for(int i=2; i<num; i++) parent[i] = i;
        // MST에서 사용하는 우선순위 큐
        pq = new PriorityQueue<>((o1,o2)->{
            return o1.w - o2.w;
        });
        // 2. 모든 간선 찾기
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(src[i][j] != 0){
                    for(int k=0; k<4; k++){
                        int nr = i+dr[k];
                        int nc = j+dc[k];
                        if(nr < 0 || nr == N || nc < 0 || nc == M || src[nr][nc] != 0) continue;            
                        //해당 방향으로 다리를 연결 시도.
                        findBridge(i, j, src[i][j], 0, k);
                    }
                }
            }
        }
        // 3. MST
        int answer = 0;
        int cnt = 0;
        while(!pq.isEmpty()){
            Node c = pq.poll();
            int u = find(c.u);
            int v = find(c.v);
            if(u != v){
                cnt++;
                answer += c.w;
                union(u,v);
            }
        }
        // 모든 섬을 연결해야 한다.
        if(answer == 0 || cnt != num-3) System.out.println(-1);
        else System.out.println(answer);
        // while(!pq.isEmpty()){
        //     Node n = pq.poll();
        //     System.out.println(n.u+" "+n.v+" "+n.w);
        // }
        br.close();
    }
    public static int find(int x){
        if(x == parent[x]) return x;
        return (parent[x] = find(parent[x]));
    }
    public static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x > y){
            parent[y] = x;
        }else{
            parent[x] = y;
        }
    }
    public static void findBridge(int r, int c, int start, int weight, int dir){
        int nr = r + dr[dir];
        int nc = c + dc[dir];
        if(nr < 0 || nr == N || nc < 0 || nc == M || src[nr][nc] == start) return;
        if(src[nr][nc] == 0) findBridge(nr, nc, start, weight+1, dir);
        else{ // 연결되는 섬을 find!
            if(weight >= 2) pq.add(new Node(start, src[nr][nc], weight));
        }
    }
    public static void dfs(int r, int c, int num){
        src[r][c] = num;
        for(int i=0; i < 4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr < 0 || nr == N || nc < 0 || nc == M || src[nr][nc] == 0 || src[nr][nc] == num) continue;
            dfs(nr, nc, num);
        }
    }
}
class Node{
    int u;
    int v;
    int w;
    public Node(int u, int v, int w){
        this.u = u;
        this.v = v;
        this.w = w;
    }
}
