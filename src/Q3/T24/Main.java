package Q3.T24;

/**
 * 13023 친구관계 파악하기
 * 
 * 아래 그래프가 중요!!!!
 *  s -> ㅇ - ㅇ - ㅇ - ㅇ
 *             \  /
 *              ㅇ
 * 특정 노드에서 백트레킹 시에 해당 노드를 방문하지 않음으로 표시해야 한다.!!!!
 */
import java.io.*;
import java.util.*;
public class Main {
    private static LinkedList<Integer>[] list;
    private static int visited[];
    private static boolean arrive;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());
        int M = Integer.valueOf(st.nextToken());

        list = new LinkedList[N]; 
        for(int i=0; i<N; i++) list[i] = new LinkedList<Integer>();
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list[u].add(v);
            list[v].add(u);
        }
        
        br.close();

        visited = new int[N];
        for(int i=0; i<N; i++){ // 모든 노드가 시작점 역할을 수행해 봐야 된다. 
            dfs(i, 0);
            if(arrive){
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }
    public static void dfs(int i, int n){
        if(n == 4 || arrive){
            arrive = true;
            return;
        }
        visited[i] = 1;
        for(Object x : list[i].toArray()){
            if(visited[(int)x] == 0) {
                dfs((int)x, n+1);
            }
        }
        visited[i] = 0; //☆ 백트레킹 시에 다시 방문할 수 있어야 한다. 
    }
}
