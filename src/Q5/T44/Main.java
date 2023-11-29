package Q5.T44;
/* 백준 1033 공책에 정리. */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
class Node{
    int b, p , q;
    public Node(int b, int p, int q){
        this.b = b;
        this.p = p;
        this.q = q;
    }
    public int getB(){return b;}
    public int getP(){return p;}
    public int getQ(){return q;}
}

public class Main{
    public static ArrayList<Node>[] A;    
    public static long[] D;
    public static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        A = new ArrayList[N];
        D = new long[N];
        visited = new boolean[N];
        for(int i=0; i<N; i++) A[i] = new ArrayList<Node>();
        long lcm = 1; // least common multiple 최소공배수
        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            A[a].add(new Node(b, p, q));
            A[b].add(new Node(a, q, p));
            lcm *= (p*q/gcd(p,q));
        }
        D[0] = lcm;
        DFS(0);
        long mgcd = D[0];
        for(int i=1; i<N; i++){
            mgcd = gcd(mgcd, D[i]); // 최대공약수를 구한다. DFS로 구한 배열 D값을 나누어 최솟값을 구하기 위해서.
        }
        for(int i=0; i<N; i++) bw.write(Long.toString(D[i]/mgcd)+ " ");
        br.close();
        bw.flush();
        bw.close();
    }
    public static long gcd(long a, long b){
        return b==0 ? a : gcd(b, a%b);
    }
    public static void DFS(int current){
        visited[current] = true;
        for(Node i : A[current]){
            int next = i.getB();
            if(!visited[next]){
                D[next] = D[current] * i.getQ()/i.getP();
                DFS(next);
            }
        }
    }
}