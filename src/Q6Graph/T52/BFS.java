package Q6Graph.T52;

/*BFS 방식으로 풀어보자
 * DFS방식 Topology Sort는 진행과정에서 Cycle을 검사하고, BFS는 완료 후 결과를 보고 Cycle을 판단한다.
 * 1. 각 정점의 진입차수 계산
 * 2. 진입차수가 0인 정점 큐에 삽입
 * 3. 큐에서 꺼낸 노드와 연결된 간선 모두 끊는다.
 * 4. 다시 진입차수가 0인 정점을 큐에 삽입
 * 5. 3-4를 큐가 빌 때까지 반복
 * 
 * 모든 정점을 방문하기 전에 큐가 비워지면 사이클이 있는 것이다. 
 * 
 */

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;
public class BFS {
    static boolean[] visited;
    static LinkedList<Integer>[] list;
    static int[] inDegree;
    static Queue<Integer> queue;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = inputs[0];
        int M = inputs[1];
        list= new LinkedList[N+1];
        visited = new boolean[N+1];
        inDegree = new int[N+1];
        queue = new LinkedList<>();
        for(int i=0; i<=N; i++) list[i] = new LinkedList<>();
        for(int i=0; i<M; i++){
            inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            list[inputs[0]].add(inputs[1]);
            inDegree[inputs[1]]++;
        }
        for(int i=1; i<=N; i++){
            if(!visited[i] && inDegree[i] == 0) queue.offer(i);
        }
        while(!queue.isEmpty()){
            int now = queue.poll();
            bw.write(now+" ");
            for(int k: list[now]){
                inDegree[k]--;
                if(inDegree[k] == 0) queue.offer(k);
            } 
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
