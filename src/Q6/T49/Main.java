package Q6.T49;
import java.util.StringTokenizer;
/*
 * 백준 1717 집합의 표현, Union-Find
 * 초기에 N+1개의 집합 {0}, {1}, {2},...,{n}이 있다. 여기에 합집합 연산과 같은 집합에 포함되어 있는지를 확인하는 
 * 연산을 수행하려 한다. 집합을 표현하는 프로그램을 작성하시오.
 * 
 * 첫째 줄에 n,m이 주어진다. m은 입력으로 주어지는 연산의 개수이다. 다음 m개 줄에는 각각의 연산이 주어진다. 합집합은
 * 0 a b의 형태로 입력이 주어진다. 이는 a가 포함되어 있는 집합과 b가 포함되어 있는 집합을 합친다는 의미다.
 * 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산은 1 a b의 형태로 입력이 주어진다. 
 * 이는 a와 brk 같은 집합에 포함되어 있는지를 확인하는 연산이다.
 * 
 * Union-Find에서 자주 실수하는 부분
 * find연산을 수행할 때 재귀 함수에서 나오면서 탐색한 모든 노드의 대표 노드값을 이번 연산에서 발견한 대표 노드로 변경해야
 * 한다. union연산에서 선택된 노드끼리 연결하는 것이 아닌 선택된 노드의 대표 노드끼리 연결해야 한다.
 */
import java.util.stream.Stream;
import java.io.*;
import java.util.*;
public class Main {
    static int[] parent;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] input = new int[M][3];
        for(int i=0; i<M; i++){
            input[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        br.close();

        parent = new int[N+1];
        for(int i=0; i<N+1; i++) parent[i] = i;
        
        for(int i=0; i<M; i++){
            if(input[i][0] == 0){ // union
                union(input[i][1], input[i][2]);
            }else{ // find
                if(find(input[i][1]) == find(input[i][2])) System.out.println("YES");
                else System.out.println("NO");
            }
        }
        
    }
    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    static void union(int x, int y){
        int X = find(x);
        int Y = find(y); 
        if(X < Y) parent[Y] = X; //대표 노드끼리 연결해야 한다. 그래야 find() 에서 재귀에서 나올 때 대표노드를 정확히 업데이트 가능하다. 
        else if(X > Y) parent[X] = Y;
    }
}
