package Q3.T28;
/**
 * 1167
 * 
 */

import java.util.*;
import java.io.*;

class Node {
    private int node;
    private int weight;
    public Node(int node, int weight){
        this.node = node;
        this.weight = weight;
    }
    public int getNode() {return this.node;}
    public int getWeight(){return this.weight;}
}
public class Main {
    private static LinkedList<Node>[] list;
    private static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new LinkedList[N+1];
        StringTokenizer st;
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            list[idx] = new LinkedList<>();
            while(st.hasMoreTokens()){
                int node = Integer.parseInt(st.nextToken());
                if(node == -1) break;
                int weight = Integer.parseInt(st.nextToken());
                list[idx].add(new Node(node, weight));
            }
        }

        int[] result = bfs(1);
        result = bfs(result[0]);
        System.out.println(result[1]);
        br.close();

    }
    public static int[] bfs(int start){
        boolean visited[] = new boolean[N+1];
        int dist[] = new int[N+1];
        visited[start] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()) {
            int i = queue.poll();
            for(Node x : list[i]){
                if(!visited[x.getNode()]){
                    visited[x.getNode()] = true;
                    queue.add(x.getNode());
                    dist[x.getNode()] = dist[i] + x.getWeight();
                }
            }
        }

        int max = Integer.MIN_VALUE;
        int idx = 0;
        for(int i=1; i<=N; i++){
            if(dist[i] > max) {
                max = dist[i];
                idx = i;
            }
        }
        return new int[]{idx, max};
    }
}
