package Q6Graph.T56;
/*
  1916 최소비용 구하기
  N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 M개의 버스가 있다. 우리는 A번째 도시에서 B번째 도시까지 가는데 드는
  버스 비용을 최소화 시키려고 한다. A번째 도시에서 B번째 도시까지 가는데 드는 최소비용을 출력하라. 도시의 번호는 1부터 N까지다.

  입력
  첫째 줄에 도시의 개수(1<=N<=1,000)이 주어지고 둘째 줄에는 벗의 개수 M(1<=M<=100,000)이 주어진다. 그리고 셋째 줄부터 M+2줄까지 다음과
  같은 버스의 정보가 주어진다. 먼저 처음에는 그 버스의 출발 도시 번호가 주어진다. 그리고 그 다음에는 도착지의 도시 번호가 주어지고 또 그 버스
  비용이 주어진다. 버스 비용은 0보다 크거나 같고, 100,000보다 작은 정수다.

  그리고 M+3째 줄에는 우리가 구하고자 하는 구간 출발점의 도시번호와 도착점의 도시 번호가 주어진다. 출발점에서 도착점을 갈 수 있는 
  경우만 입력으로 주어진다.

  출력: 첫째 줄에 출발 도시에서 도착도시까지 가는데 드는 최소 비용을 출력한다.

  다익스트라!!!!
  어떤 출발 노드에서 모든 다른 노드까지의 이동 거리의 최소비용을 계산하는 알고리즘이다.
  최단 거리는 다른 최단거리의 합으로 계산되기 때문에 다이나믹 프로그래밍 개념이 포함된다.

  1. 시작 노드 택
  2. 시작 노드로부터 최소비용 계산
  3. 방문하지 않은 노드 중 최소비용을 가진 노드 택, 방문 처리
  4. 선택된 노드의 인접 노드로 가는 최소비용 갱신
  5. 3~4를 반복

  우선순위 큐와 인접 리스트를 사용하면 O(ElogV) 시간 복잡도를 가진다.
  방문 처리와 최소비용 갱신이 중요하다.
 */

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        LinkedList<Node> [] list= new LinkedList[N+1]; // 인접 리스트
        int[] D = new int[N+1]; // 최소비용 배열
        boolean[] visited = new boolean[N+1]; // 방문 배열
        for(int i=0; i<=N; i++) {
            list[i] = new LinkedList<>();
            D[i] = Integer.MAX_VALUE; // 최소비용 배열 최댓값으로 초기화
        }

        int[] inputs;
        for(int i=0; i<M; i++){
            inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            list[inputs[0]].add(new Node(inputs[1], inputs[2])); // u -> v로 가는 비용 w
        }

        inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        br.close();
        int start = inputs[0];
        int end = inputs[1];
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.weight - o2.weight; // 최소비용을 가진 것이 먼저 나오도록, 여기서 말하는 비용은 시작점에서의 최소 거리.
        });
        pq.add(new Node(start, 0)); // 여기선 weight가 아니라 최소경로값을 넣는다!!!!
        D[start] = 0;
        while(!pq.isEmpty()){
            Node current = pq.poll();
            if(!visited[current.vertex]){ // 해당 노드가 이미 방문 처리 되었다면 최소비용이 결정되었다는 뜻이다.
                visited[current.vertex] = true;
                for(Node next : list[current.vertex]){
                    D[next.vertex] = Math.min(D[next.vertex], D[current.vertex]+next.weight); // 비용 갱신
                    if(D[next.vertex] == D[current.vertex] + next.weight){ // 비용이 갱신되었다면 큐에 추가.
                        pq.add(new Node(next.vertex, D[next.vertex]));
                    }
                }
            }
        }
        System.out.println(D[end]);
    }    
}
class Node{
    int vertex;
    int weight;
    public Node(int vertex, int weight){
        this.vertex = vertex;
        this.weight = weight;
    }
}