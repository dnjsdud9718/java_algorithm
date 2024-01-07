package swea.Q1.T1;
/* 
 * Ssafy, 최적 경로 문제
 * 삼성전자의 서비스 기사인 김기사는 회사에서 출발하여 냉장고 배달을 위해 N명의 고객을 방문하고 자신의 집에 돌아가려한다.
 * 회사와 집의 위치, 그리고 각 고객의 위친는 이차원 정수 좌표(x,y)로 주어지고(0<=x<=100, 0<=y<=100)
 * 두 위치 (x1,y1)와 (x2,y2) 사이의 거리는 |x1-x2|+|y1-y2|으로 계산된다.
 * 회사의 좌표, 집의 좌표, 고객들의 좌표는 모두 다르다.
 * 회사에서 출발하여 N명의 고객을 모두 방문하고 집으로 돌아오는 경로 중 가장 짧은 것을 찾으려 한다.
 * 회사와 집의 좌표가 주어지고, 2명에서 10명 사이의 고객 좌표가 주어질 때,
 * 회사에서 출발해서 이들을 모두 방문하고 집에 돌아가는 경로 중 총 이동거리가 가장 짧은 경로를 찾는 프로그램을 작성하라.
 * 
 * 이 문제는 가장 짧은 경로를 '효율적으로' 찾는 것이 목적이 아니다.
 * 여러분은 모든 경로를 살펴서 해를 찾아도 좋다.
 * 모든 경우를 체계적으로 따질 수 있으면 정답을 맞출 수 있다.
 * 
 * ★ DFS 특정 노드에서 백트래킹 해당 노드를 방문하지 않음으로 초기화하고 나가야 한다.
 * 
 * A B C 고객 집이 있다면
 * 모든 가능한 경로 설정해 본다
 * 3! = 6가지
 * A B C
 * A C B
 * B A C
 * B C A
 * C A B
 * C B A
 */
import java.util.stream.Stream;
import java.io.*;
public class Solution {
    static int[] iArr; //입력 배열
    static int[][] memo;
    static boolean[] visited; // 방문 배열
    static int N; //입력 크기
    static int sum; 
    static int count; //DFS 깊이
    static int min; // 최솟값
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int ts=1; ts<=T; ts++){
            sum  = 0;
            min  = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            iArr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            memo = new int[N+2][N+2];
            visited = new boolean[N+2];
            // 메모이제이션
            for(int i=0; i<N+2; i++){
                for(int j=i+1; j<N+2; j++){
                    int t = Math.abs(iArr[i*2]-iArr[j*2]) + Math.abs(iArr[i*2+1]-iArr[j*2+1]);
                    memo[i][j] = t;
                    memo[j][i] = t;
                }
            }
            /*for(int i=0; i<N+2; i++){
                for(int j=0; j<N+2; j++){
                    System.out.printf("%d  ", memo[i][j]);
                }
                System.out.println();
            }*/
            count = -1;
            dfs(iArr[0], iArr[1], 0);
            System.out.printf("#%d %d\n", ts, min);
        }
        br.close();
    }
    public static void dfs(int x, int y, int idx){
        visited[idx] = true;
        count++;
        //System.out.printf("%d ", idx);
        if(count == N) { // DFS가 최대 깊이 도달했을 때, 즉, 모든 노드를 방문한 경우, 집까지의 거리 계산
            //int distance = Math.abs(x - iArr[2]) + Math.abs(y - iArr[3]);
            //sum += distance;
            sum += memo[idx][1];
            if(min > sum) min = sum;
            //sum -= distance;
            sum -= memo[idx][1];
        }
        for(int i=2; i<N+2; i++){ // 탐색
            if(!visited[i] && idx != i){ // 자기자신이 아니고 방문하지 않은 경우
                //int tmp = Math.abs(x-iArr[i*2]) + Math.abs(y-iArr[i*2+1]); //거리 계산
                //sum += tmp;
                sum += memo[idx][i];
                dfs(iArr[i*2], iArr[i*2+1], i);
                //sum -= tmp;
                sum-= memo[idx][i];
            }
        }
        visited[idx] = false;
        count--;
    }
}
