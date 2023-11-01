package Q3.T25;

import java.io.*;
import java.util.*;
public class Main {
    private static LinkedList<Integer> [] list;
    private static int[] dfsNum; //discover time
    private static int[] lowNum; //back edge
    private static int seq;
    private static Stack<Integer> stack;
    private static PriorityQueue<PriorityQueue<Integer>> pq;
    private static BufferedWriter bw;
    private static int count;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        list = new LinkedList[N+1];
        for(int i=1; i<=N; i++) list[i] = new LinkedList<>();
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list[u].add(v);
        }
        br.close();

        dfsNum = new int[N+1];
        lowNum = new int[N+1];
        seq = 1;
        stack = new Stack<>();
        count = 0;
        pq = new PriorityQueue<>((o1, o2) -> {
            return o1.peek() - o2.peek();
        });

        for(int i=1; i<=N; i++){
            if(dfsNum[i] == 0) {
                scc(i);
            }
        }
        bw.write(count+ "\n");
        while(!pq.isEmpty()){
            PriorityQueue<Integer> t = pq.poll();
            while(!t.isEmpty()){
                bw.write(t.poll()+" ");
            }
            bw.write("-1\n");
        }
        bw.flush();
        bw.close();
    }
    public static int scc(int u) {
        dfsNum[u] = lowNum[u] = seq++; //처음에 백엣지는 자기 자신
        stack.add(u); // 방문했음을 기록.
        for(Object x : list[u].toArray()){
            if(dfsNum[(int)x] == 0){
                lowNum[u] = Math.min(lowNum[u], scc((int)x));
            }else if(dfsNum[u] > dfsNum[(int)x] && stack.contains((int)x)) { // is back edge
                lowNum[u] = Math.min(lowNum[u], dfsNum[(int)x]);
            }
        }
        if(dfsNum[u] == lowNum[u]) {
            PriorityQueue<Integer> pq2 = new PriorityQueue<>();
            int x;
            while((x = stack.pop()) != u){
                pq2.add(x);
            }
            pq2.add(u);
            pq.add(pq2);
            count++;
        }
        return lowNum[u];
    }
}