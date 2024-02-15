package SSAFY.study.week2.t2252;
/*
 백준 : 2252 줄 세우기
 N명의 학생들을 키 순서대로 줄을 세우려고 한다. 각 학생의 키를 직접 재서 정렬하면 간단하겠지만, 마땅한 방법이 없어서 두 학생의 키를 비교하는 방
 법을 사용하기로 했다. 그나마도 모든 학생들을 비교해 본 것이 아니고, 일부 학생들의 키만을 비교했다.

 일부 학생들의 키를 비교한 결과가 주어졌을 때, 줄을 세우는 프로그램을 작성하시오.

 입력
 N (1<=N <=32,000)학생 번호 , M(1<=M <=100,000)비교한 횟수
 A B -> A학생이 B학생보다 앞에서야 한다.

 위상정렬 BFS
 in-degree가 0인 학생이 큐에 들어간다.
 */
import java.io.*;
import java.util.*;
import java.util.stream.Stream;
public class Main {
    public static List<Integer> list[];
    public static int degree[];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int [] inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = inputs[0];
        int M = inputs[1];
        degree = new int[N+1];
        list = new ArrayList[N+1];
        for(int i=1;i <= N; i++) list[i] = new ArrayList<>();
        for(int i=0; i<M ;i++){
            inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int u = inputs[0];        
            int v = inputs[1];
            list[u].add(v);
            degree[v]++; // 진입 차수 계산
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<=N; i++) if(degree[i] == 0)  q.add(i); // 진입 차수가 0인 노드 추가.
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){
            int cur = q.poll();
            sb.append(cur).append(" ");
            for(int x : list[cur]){
                degree[x]--;
                if(degree[x]==0){
                    q.add(x);
                }
            }
        }
        System.out.println(sb.toString());
        br.close();
    }    
}
