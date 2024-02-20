package SSAFY.study.week4.t15663;
import java.io.*;
import java.util.*;
public class Main2 {
    // 출처 : 송도언 ehdjs7878(백준)
    static int N, M;
    static int[] src, tgt;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    // 동일한 값이 순열에 중복되어 들어가는 것을 체크하는 것이 아니다.
    // 구해진 순열이 중복인지 체크해서 중복을 없애는 것이 문제
    // 순열이 구해지고 이전 순열 전체와 비교하여 중복인지 판단하는 것은 비효율적이다.

    // 4 3 N M
    // 9 7 9 1 a1 a2 a3 a4
    //1 7 9
    //1 7 9 중복
    //1 9 7
    //1 9 9
    //1 9 7 중복
    //1 9 9 중복
    //7 1 9
    //7 1 9 중복
    //7 9 1
    //7 9 9
    //7 9 1 중복
    //7 9 9 중복
    //9 1 7
    //9 1 9
    //9 7 1
    //9 7 9
    //9 9 1
    //9 9 7
    //9 1 7 중복
    //9 1 9 중복
    //9 7 1 중복
    //9 7 9 중복
    //9 9 1 중복
    //9 9 7 중복
    //CNT:24
    // perm() 메서드 -> k-1번째까지 구해진 수열이 동일한 경우, 수열에 들어갈 k번째 값에 동일한 값이 두 번 이상 들어가면 안된다. 이미 해당 값을 가지고 구할 수 있는 수열(순열)을 모두 구했기 때문에 중복된 수열이 구해진다.
    static void search(int depth){
        if(depth == M){
            for(int i = 0; i < M; i++){
                sb.append(tgt[i]).append(' ');
            }
            sb.append('\n');
            return ;
        }
        int num = 0; // 현 depth번째가 가지고 있는 값.
        for(int i = 0; i < N; i++){
            // num이 중복되면 이미 갔던 곳을 다시 도는 것.
            if(visited[i] || num == src[i]) continue;
            visited[i] = true;
            tgt[depth] = src[i];
            num = src[i];
            search(depth + 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        src = new int[N];
        tgt = new int[M];
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            src[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(src);
        search(0);
        System.out.println(sb);
    }
}
