package Q6Graph.T51;

/*
 * 1043 백준 거짓말
 * 지민이는 파티에 가서 이야기 하는 것을 좋아한다. 파티에 갈 때마다, 지민이는 지민이가 가장 좋아하는 이야기를 한다.
 * 지민이는 그 이야기를 말할 때, 있는 그대로 진실로 말하거나 엄청나게 과장해서 말한다. 당연히 과장해서 이야힉하는 것이 훨씬 더
 * 재미있기 때문에, 되도록이면 과장해서 이야기하려고 한다. 하지마, 지민이는 거짓말쟁이로 알려지기 싫어한다. 문제는
 * 몇몇 사람들은 그 이야기의 진실을 안다는 것이다. 따라서 이런 사람들이 파티에 왔을 때는, 지민이는 진실을 이야기할 
 * 수 밖에 없다. 당연히, 어떤 사람이 어떤 파티에서는 진실을 듣고, 또 다른 파티에서는 과장된 이야기를 들었을 떄도
 * 지민이는 거짓말쟁이로 알려지게 된다. 지민이는 이런 일을 모두 피해야 한다.
 * 
 * 사람의 수 N이 주어진다. 그리고 그 이야기의 진실을 아는 사람이 주어진다. 그리고 각 파티에 오는 사람들의 번호가 
 * 주어진다. 지민이는 모든 파티에 참가해야 한다. 이때, 지민이가 거짓말쟁이로 알려지지 않으면서, 과장된 이야기를 할
 * 수 있는 파티 개수의 최댓값을 구하는 프로그램을 작성하시오
 * 
 * 입력
 * 첫째 줄에 사람의 수 N과 파티의 수 M이 주어진다.
 * 둘째 줄에는 이야기의 진실을 아는 사람의 수와 번호가 주어진다. 진실을 아는 사람의 수가 먼저 주어지고 그 개수만큼
 * 사람들의 번호가 주어진다. 사람들의 번호는 1부터 N까지 수로 주어진다.
 * 
 * 셋째 줄부터 M개의 줄에는 각 파티마다 오는 사람의 수와 번호가 같은 방식으로 주어진다.
 * N, M은 50이하의 자연수이고, 진실을 아는 사람의 수는 0 이상 50이하의 정수, 각 파티마다 오는 사람의 수는 1이상 
 * 50이하의 정수이다.
 */

import java.io.*;
import java.util.*;
import java.util.stream.Stream;
public class Main {
    static int[] parent; // 유니온 파인드 
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // # of 참가자
        int M = Integer.parseInt(st.nextToken()); // # of 파티
    
        parent = new int[N+1]; 
        for(int i=1; i<=N; i++) parent[i] = i; // 조상을 자기 자신으로 초기화

        
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for(int i=0; i<T; i++){ //거짓말을 알아차릴 수 있는 사람의 조상을 0으로 초기화
            parent[Integer.parseInt(st.nextToken())] = 0;
        }

        int[][] party = new int[M][];
        for(int i=0; i<M; i++){
            party[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j=1; j<party[i].length-1; j++){
                // 해당 파티에 참석한 사람을 Union하여 같은 무리로 구분. 
                // 거짓말을 알아차릴 수 있는 사람이 있다면 공통 조상이 0이 된다.
                union(party[i][j], party[i][j+1]);                
            }
        }
        br.close();
        
        int answer = M; // 모든 파티에서 거짓말 가능하다고 가정하고 시작
        for(int i=0; i<M; i++){
            for(int j=1; j<party[i].length; j++){
                if(find(party[i][j]) == 0) { // 거짓말을 알아차릴 수 있는 사람이 존재하면 카운트 감소
                    answer--;
                    break;
                }
            }
        }
        System.out.println(answer);
    }
    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    static void union(int x, int y){
        int X = find(x);
        int Y = find(y);
        if(X > Y) parent[X] = Y;
        else parent[Y] = X;
    }
}
