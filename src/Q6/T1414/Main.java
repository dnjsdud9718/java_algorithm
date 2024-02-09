package Q6.T1414;
// baekjoon 1414 불우이웃돕기
//다솜이는 불우이웃 돕기 활동을 하기 위해 무엇을 할지 생각했다. 마침 집에 엄청나게 많은 랜선이
//있다는 것을 깨달았다. 마침, 랜선이 이렇게 많을 필요 없다고 느낀 다솜이는 랜선을 지역사회에 봉사하기로 했다
//다솜이의 집에는 N개의 방이 있다. 각각의 방에는 모두 한 개의 컴퓨터가 있다. 각각의
//컴퓨터는 랜선으로 연결되어 있다. 어떤 컴퓨터 A와 컴퓨터 B가 있을 때, A와 B가 서로
//랜선으로 연결되어 있거나, 또 다른 컴퓨터를 통해서 연결이 되어 있으면 서로 통신을 할 수 있다.
//다솜이는 집 안에 있는 N개의 컴퓨터를 모두 서로 연결되게 하고 싶다.
//N개의 컴퓨터가 서로 연결되어 있는 랜선의 길이가 주어질 때, 다솜이가 기부할 수 있는
//랜선의 길이의 최댓값을 출력
//첫째 줄에 컴퓨터의 개수 N이 주어진다. 둘째 줄부터 랜선의 길이가 주어진다.
//i번째 줄의 j번째 문자가 0인 경우는 컴퓨터 i와 컴퓨터 j를 연결하는 랜선이 없음을 의미한다. 그 외의 경우는 랜선의 길이를 의미한다.
//랜선의 길이는 a부터 z는 1부터 26을 나타내고, A부터 Z는 27부터 52를 나타낸다.
//N은 50보다 작거나 같은 자연수이다.

// 모든 방을 연결하는 간선의 최솟값을 구해야 한다 -> MST

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static int[][] src;
    static int[] parent;
    static int totalW, mstW, mstCnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        src = new int[N+1][N+1];
        parent = new int[N+1];
        for(int i=1; i<=N; i++) parent[i] = i;

        totalW = mstW = mstCnt = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.w - o2.w;
        });
        for (int i = 1; i <= N; i++) {
            String[] s = br.readLine().split("");
            for (int j = 1; j <= N; j++) {
                char c = s[j-1].charAt(0);
                if (Character.isDigit(c)) {
                    src[i][j] = 0;
                } else if (Character.isAlphabetic(c)) {
                    if(Character.isLowerCase(c)) src[i][j] = c-'a'+1;
                    else src[i][j] = c-'A'+27;
                }
                totalW += src[i][j];
                pq.add(new Node(i, j, src[i][j]));
//                System.out.print(src[i][j]+" ");
            }
//            System.out.println();
        }
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if(current.w == 0) continue;
            int x = find(current.u);
            int y = find(current.v);
            if(x!=y){
                mstW += current.w;
                union(x, y);
                mstCnt++;
            }
        }
        if (mstCnt == N - 1) {
            System.out.println(totalW - mstW);
        }else System.out.println(-1);
        br.close();
    }
    public static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x > y) parent[x] = y;
        else parent[y] =x;
    }
}
class Node{
    int u;
    int v;
    int w;
    public Node(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}
