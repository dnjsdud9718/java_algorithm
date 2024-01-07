package Q6.T52;
/*
 * 2252 줄세우기
 * N명의 학생들을 키 순서대로 줄을 세우려고 한다. 각 학생의 키를 직접 재서 정렬하면 간단하지만, 마땅한 방법이 없어서
 * 두 학생의 키를 비교하는 방법을 사용하기로 했다. 그나마도 모든 학생들을 비교해 본 것이 아닌, 일부 학생들만의 키를 비교
 * 해 보았다. 일부 학생들의 키를 비교한 결과가 주어졌을 때, 줄을 세우는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄 N(1 <= N <= 32,000), M(1 <= M <= 100,000)이 주어진다. M은 키를 비교한 횟수이다. 다음 M개 줄에는 키를 비교한
 * 두 학생의 번호 A, B가 주어진다. 이는 학생 A가 학생 B의 앞에 서야 한다는 의미다.
 * 학생들의 번호는 1부터 N번이다.
 * 
 * 출력 답이 여러가지인 경우에는 아무거나 출력한다.
 * 
 * DFS방식으로 해봤다...!
 * 
 */
import java.io.*;
import java.util.*;
import java.util.stream.Stream;
public class Main {
    static LinkedList<Integer>[] list;
    static boolean[] visited;
    static boolean[] finished;
    static Stack<Integer> stack;
    static boolean cycle = false;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = inputs[0];
        int M = inputs[1];
        list = new LinkedList[N+1];
        for(int i=0; i<=N; i++) list[i] = new LinkedList<>();
        for(int i=0; i<M; i++){
            inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            list[inputs[0]].add(inputs[1]);
        }
        visited = new boolean[N+1];
        finished = new boolean[N+1];
        stack = new Stack<>();
        for(int i=1; i<=N; i++){
            if(!cycle && !visited[i]) dfs(i);
        }
        if(cycle) bw.write("CYCLE!!!");
        else{
            while(!stack.isEmpty()){
                int t = stack.pop();
                bw.write(t+" ");
            }
        }
        
        bw.flush();
        bw.close();
    }
    public static void dfs(int x){
        if(cycle) return;
        visited[x] = true;
        for(int k : list[x]){
            if(!visited[k]){
                dfs(k);
            }else if(!finished[k]){
                //cycle!
                cycle = true;
                break;
            }

        }
        stack.push(x);
        finished[x] = true;
    }
}
