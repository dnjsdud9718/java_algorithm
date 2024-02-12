package Q6Graph.T54;
/*
 * 1948 임계경로
 * 월드 나라는 모든 도로가 일방통행인 도로이고, 싸이클이 없다(DAG). 그런데 어떤 무수히 많은 사람들이 월드 나라의 
 * 지도를 그리기 위해서, 어떤 시작 도시로부터 도착 도시까지 출발을 하여 ☆모든 경로☆를 탐색한다고 한다. 
 * 이 지도를 그리는 사람들은 사이가 너무 좋아서 지도를 그리는 일을 다 마치고 도착 도시에서 모두 다 만나기로 하였다.
 * 그렇다고 하였을 때 이들이 만나는 시간은 출발 도시로부터 출발한 후 최소 몇시간 후에 만날 수 있는가? 즉, 마지막에
 * 도착하는 사람까지 도착을 하는 시간을 말한다.
 * 어떤 사람은 이 시간에 만나기 위하여 1분도 쉬지 않고 달려야 한다. 이런 사람들이 지나는 도로의 수를 카운트(집계) 하여라.
 * 출발 도시는 들어오는 도로가 0이고, 도착 도시는 나가는 도로가 0개이다.
 * 
 * 입력
 * 첫째 줄에는 도시의 개수 n(1 <= n <= 10,000)이 주어지고 둘째 줄에는 도로의 개수 m(1 <= m <=100,000)이 주어진다.
 * 그리고 셋째 줄에는 m+2줄까지 다음과 같은 도로의 정보가 주어진다. 처음에는 도로의 출발 도시의 번호가 주어지고 그 다음에는 도착
 * 도시의 번호, 그리고 마지막에는 이 도로를 지나는데 걸리는 시간이 주어진다. 도로를 지나가는 시간은 10,000보다 작거나
 * 같은 자연수이다.
 * 
 * 그리고 m+3줄에는 지도를 그리는 사람들이 출발하는 도시와 도착 도시가 주어진다.
 * 모든 도시는 출발 도시로부터 도달이 가능하고, 모든 도시로부터 도착 도시에 도달 가능하다.
 * 
 * 출력
 * 첫째 줄에는 이들이 만나는 시간을, 둘째 줄에는 1분도 쉬지 않고 달려야 하는 도로의 수가 몇 개인지 출력하시오
 *  Input
 *  7
    9
    1 2 4
    1 3 2
    1 4 3
    2 6 3
    2 7 5
    3 5 1
    4 6 4
    5 6 2
    6 7 5
    1 7
    Output
    12
    5

    임계 경로값 구하기 : 위상정렬 수행하며 다음 노드까지 걸리는 최대 시간을 구한다. 다음 노드까지 걸리는 최대시간은
    최댓값(현 상황에서의 다음노드까지 걸리는 최대시간, 현재노드까지 걸리는 최대시간+weight)
    u -> v라면 answer[v] = max(answer[v], answer[u]+weight)

    1분도 쉬지 않고 달려야 하는 도로의 수: 에지 뒤집기를 이용해야 한다. 역방향 그래프에서 위상정렬 수행. v -> u라면,
    if(answer[v] == answer[u]+weight) then 도로 카운트 그리고 u가 방문하지 않았다면 방문 표시 후 큐에 삽입.
 */
import java.io.*;
import java.util.*;
import java.util.stream.Stream;
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // # of City
        int M = Integer.parseInt(br.readLine()); // # of Road
        LinkedList<Node>[] list = new LinkedList[N+1]; // 순방향 그래프
        LinkedList<Node>[] reverse = new LinkedList[N+1]; // 역방향 그래프
        int inDegree[] = new int[N+1]; // 위상정렬에 사용하는 진입차수 배열
        for(int i=0; i<=N; i++) {
            list[i] = new LinkedList<>();
            reverse[i] = new LinkedList<>();
        }
        for(int i=0; i<M; i++){
            int[] input = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            list[input[0]].add(new Node(input[1], input[2])); // u -> (v, w)
            reverse[input[1]].add(new Node(input[0], input[2])); // v -> (u, w)
            inDegree[input[1]]++;
        }
        int[] SRCDST = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); // start node, dest node
        br.close();

        // 순방향 그래프에 대해 위상정렬 수행하며 임계 경로 계산
        int[] answer = new int[N+1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(SRCDST[0]);
        while(!queue.isEmpty()){
            int u = queue.poll();
            for(Node v : list[u]){
                // 출발점에서 v까지 오는데 소요되는 최대 시간 = max(현재까지 계산된 v까지 걸리는 시간, 현재까지 계산된 u까지 걸리는 시간 + 비용)
                answer[v.v] = Math.max(answer[v.v], answer[u]+v.w);
                inDegree[v.v]--; // 타겟노드 진입 차수 제거
                if(inDegree[v.v] == 0) { // 타겟노드 진입 차수가 0이면 큐에 삽입
                    queue.add(v.v);
                }
            }
        }

        // 역방향 그래프 위상 정렬 수행
        boolean[] visited = new boolean[N+1];
        queue = new LinkedList<>();
        queue.add(SRCDST[1]);
        visited[SRCDST[1]] = true;
        int cnt = 0;
        while(!queue.isEmpty()){
            int u = queue.poll();
            for(Node v : reverse[u]){
                // v까지 걸리는 시간 + 비용과 u까지 걸리는 시간이 같다면 v->u는 임계 경로에 속한다.
                if(answer[v.v] + v.w == answer[u]) {
                    cnt++; // 도로 수 증가
                    if(!visited[v.v]) { // 방문하지 않았다면 큐에 삽입, 방문 처리
                        
                        queue.add(v.v);
                        visited[v.v] = true;
                    }
                }
            }
            
        }
        System.out.println(answer[SRCDST[1]]);
        System.out.println(cnt);
        
    }
    
}
class Node{
    int v;
    int w;
    public Node(int v, int w){
        this.v = v;
        this.w = w;
    }
}