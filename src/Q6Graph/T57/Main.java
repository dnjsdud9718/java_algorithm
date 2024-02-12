package Q6Graph.T57;

/*
  1854 K번째 최단경로 찾기

  봄캠프를 마친 최동환 조교는 여러 도시를 돌며 여행을 다닐 계획이다. 그런데 최 조교는, '느림의 미학'을 중요시하는 사람이라 항상 최단경로만
  이동하는 것은 별로 좋아하지 않는다. 하지만 너무 시간이 오래 걸리는 경로도 그리 매력적인 것만은 아니어서, 적당한 타협안인 'k번째 최단경로'를
  구하길 원하다. 그를 돕기 위한 프로그램을 작성해 보자.

  입력
  첫째 줄에 n, m, k가 주어진다.(1<=n<=1,000, 0<=m<=2,000,000, 1<=k<=100) n과 m은 각각 최조교가 여행을 고려하고 있는 도시들의 개수와
  도시 간에 존재하는 도로의 수이다.
  이어지는 m개의 줄에는 각각 도로의 정보를 제공하는 세 개의 정수 a,b,c가 포함되어 있다. 이것은 a->b 일 때 c의 시간이 걸린다는 의미다
  (1 <= a,b <=n, 1 <= c <=1000)
  도시의 번호는 1번부터 n번까지 연속하여 붙어 있으며, 1번 도시는 시작도시다. 두 도로의 시작점과 도착점이 같은 경우는 없다.

  출력
  n개의 줄을 출력한다. i번째 줄에 1번 도시에서 i번 도시로 가는 k번째 최단경로의 소요시간을 출력한다.
  경로의 소요시간은 경로 위에 있는 도로들을 따라 이동하는데 필요한 시간들의 합이다. i번 도시에서 i번 도시로 가는 최단경로는 0이지만,
  일반적인 k번째 최단경로는 0이 아닐 수 있음에 유의한다. 또 k번째 최단경로가 존재하지 않으면 -1을 출력한다. 최단 경로에 같은 정점이
  여러번 포함되어도 된다.
    input
    5 10 2    
    1 2 2
    1 3 7
    1 4 5
    1 5 6
    2 4 2
    2 3 4
    3 4 6
    3 5 8
    5 2 4
    5 4 1
    Output
    -1
    10
    7
    5
    14
 */

import java.io.*;
import java.util.*;
import java.util.stream.Stream;
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = inputs[0]; // # of Node
        int M = inputs[1]; // # of Edge
        int K = inputs[2]; // K번째 최단거리
        LinkedList<Node>[] list= new LinkedList[N+1];
        for(int i=0; i<=N; i++) list[i] = new LinkedList<>();
        for(int i=0; i<M; i++){
            inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            list[inputs[0]].add(new Node(inputs[1], inputs[2])); // u->v 경로 비용 w
        }

        // 다익스트라 알고리즘 최단경로를 저장하는 정수 배열을 우선순위 큐 배열(크기 K)로 변경
        // K번째 최단경로를 구하기 위해서 노드를 여러번 사용하는 경우가 발생한다.
        PriorityQueue<Integer>[] distQueue = new PriorityQueue[N+1];
        for(int i=0; i<=N; i++) distQueue[i] = new PriorityQueue<>((o1, o2) -> {
            return -(o1 - o2); //내림차순 정렬, 큰 값이 먼저 나온다.
        });

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.weight - o2.weight; // 오름차순 정렬, 최단경로 값이 낮은 것이 먼저 나온다.
        });

        pq.add(new Node(1, 0)); //weight는 최단경로 값...
        distQueue[1].add(0);
        while(!pq.isEmpty()){
            Node current = pq.poll();
            for(Node next : list[current.vertex]){
                if(distQueue[next.vertex].size() < K){ // 저장된 최소 비용 값이 k가 안될 때
                    distQueue[next.vertex].add(current.weight+next.weight); // 시작 -> current -> next 비용
                    pq.add(new Node(next.vertex, current.weight+next.weight));
                }else if(distQueue[next.vertex].peek() > current.weight+next.weight){
                    distQueue[next.vertex].poll();
                    distQueue[next.vertex].add(current.weight+next.weight);
                    pq.add(new Node(next.vertex, current.weight+next.weight));
                }
            }
        }
        for(int i=1; i<=N; i++){
            if(distQueue[i].size() == K) System.out.println(distQueue[i].poll());
            else System.out.println(-1);
        }
        br.close();
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
