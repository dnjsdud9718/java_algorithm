package SSAFY.algo.파리퇴치_2001;
/*
 SWEA
 2001 파리퇴치
 NxN 배열 안의 숫자는 해당 영역에 존재하는 파리의 개수를 의미한다.
 크기가 MxM인 파리채를 한 번 내리쳐 최대한 많은 파리를 죽이고자 한다.

    1 T
    5 2 N M
    1 3 3 6 7
    8 13 9 12 8
    4 16 11 12 6
    2 4 1 23 2
    9 13 4 7 3

    1. 4차원 배열 이용하는 방법
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;
public class Solution {
    static int answer, N, M;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int ts=1; ts<=T; ts++){
            int[] inputs =Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            N = inputs[0];
            M = inputs[1];
            // bruteForce
            // int[][] map = new int[N][N];
            // for(int i=0; i<N; i++){
            //     map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            // }
            int[][] map = new int[N+1][N+1];
            for(int i=1; i<=N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=1; j<=N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            answer = Integer.MIN_VALUE;
            // bruteForce(map);
            subTotal(map);
            sb.append("#").append(ts).append(" ").append(answer).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
    public static void subTotal(int[][] map){
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                map[i][j] = map[i][j] + map[i-1][j] + map[i][j-1] - map[i-1][j-1];
            }
        }
        for(int i=M; i<=N; i++){
            for(int j=M; j<=N; j++){
                answer = Math.max(answer, map[i][j]-map[i-M][j]-map[i][j-M]+map[i-M][j-M]);
            }
        }
    }
    public static void bruteForce(int[][] map){
        int L = N-M+1;
        for(int i=0; i<L; i++){
            for(int j=0; j<L; j++){
                int sum = 0;
                for(int p=i; p<i+M; p++){
                    for(int q=j; q<j+M; q++){
                        sum += map[p][q];
                    }
                }
                answer = Math.max(answer, sum);
            }
        }
    }
}
