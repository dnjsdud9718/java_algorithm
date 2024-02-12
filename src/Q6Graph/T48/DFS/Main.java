package Q6Graph.T48.DFS;

import java.util.*;
public class Main {
    static int[] sender = {0,0,1,1,2,2};
    static int[] receiver = {1,2,0,2,0,1};
    static boolean[][] visited;
    static boolean[] answer;
    static int[] volume;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        volume = new int[3];
        for(int i=0; i<3; i++) volume[i] = sc.nextInt();
        sc.close();

        visited = new boolean[201][201];
        answer = new boolean[201];
        dfs(0,0);
        for(int i=0; i<201; i++) if(answer[i]) System.out.printf("%d ", i);
    }
    public static void dfs(int x, int y){
        int z = volume[2] - x - y;
        visited[x][y] = true;
        if(x == 0) answer[z] = true;

        for(int i=0; i<6; i++){
            int[] now = {x,y,z};
            now[receiver[i]] += now[sender[i]];
            now[sender[i]] = 0;

            if(now[receiver[i]] > volume[receiver[i]]){
                now[sender[i]] = now[receiver[i]] - volume[receiver[i]];
                now[receiver[i]] = volume[receiver[i]];
            }
            if(!visited[now[0]][now[1]]){
                dfs(now[0], now[1]);
            }
        }
    }
}
