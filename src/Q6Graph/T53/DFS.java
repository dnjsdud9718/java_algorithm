package Q6Graph.T53;

//1516 DFS로 풀 수 있을까?
/*
 * 개인적 생각
 * 1 -> 2 -> 3
 *          >
 * 4 -> 5 /
 * 
 * 위상정렬을 DFS로 구현하면 임의의 노드에서 시작. (진입 차수가 0이 아닌 곳에서 시작 가능)
 * 위 예시에서 2에서 시작한다면...2와 3은 방문 처리되고, 1에서 시작 된다면 2와 3을 방문하지 못하기 때문에 문제 발생.
 * 
 * 문제 해결 1. DFS로 위상정렬하고 순서를 스택에 저장. 2. 스택에서 값을 하나씩 가져와 u -> v로 갈 때, 현재 v가
 * 가지고 있는 소요 시간과 u를 거치는 소요 시간 중 큰 값을 저장.
 * 
 * 위상 정렬 문제에서 dfs()가 구현이 쉽다고 생각되나 이러한 문제 때문에 bfs()를 사용하는 것이 유용한 것 같다.
 */
import java.io.*;
import java.util.*;
public class DFS {
    static boolean[] visited;
    static LinkedList<Integer>[] list;
    static int[] duration;
    static int[] answer;
    static Stack<Integer> stack;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        duration = new int[N+1];
        answer = new int[N+1];
        list = new LinkedList[N+1];
        for(int i=0; i<=N; i++) list[i] = new LinkedList<>();
        for(int i=1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            duration[i] = Integer.parseInt(st.nextToken());
            while(!!st.hasMoreTokens()){
                int t = Integer.parseInt(st.nextToken());
                if(t != -1) list[t].add(i);
            }
        }
        stack = new Stack<>();
        for(int i=1; i<=N; i++) {
            if(!visited[i]) dfs(i);
        }
        while(!stack.isEmpty()){
            int u = stack.pop();
            for(int v : list[u]){
                answer[v] = Math.max(answer[v], answer[u]+duration[u]);
            }
        }
        for(int i=1; i<=N; i++) bw.write(answer[i]+duration[i]+"\n");
        br.close();
        bw.flush();
        bw.close();
    }
    public static void dfs(int u){
        visited[u] = true;
        for(int v : list[u]){
            if(!visited[v]) dfs(v);
        }
        stack.add(u);
    }
}
