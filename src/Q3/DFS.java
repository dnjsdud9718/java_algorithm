package Q3;

import java.util.*;
public class DFS {
    private static int[] visited;
    private static LinkedList<Integer> [] list;
    public static void main(String[] args){
        int N = 6;
        list = new LinkedList[N+1];
        for(int i=1; i<=N; i++ ) list[i] = new LinkedList<>();
        list[1].add(4);
        list[2].add(1); list[2].add(3);
        list[4].add(2); list[4].add(5); list[4].add(6);
        list[5].add(6);
        list[6].add(3); list[6].add(5);

        visited = new int[N+1];
        for(int i=1; i<=N; i++){
            if(visited[i] == 0){
                dfs(i);
            } 
        }

    }
    public static void dfs(int i){
        visited[i] = 1;
        System.out.println(i);
        for(Object x : list[i].toArray()){
            if(visited[(int)x] != 1){
                dfs((int)x);
            }
        }
    }
}
