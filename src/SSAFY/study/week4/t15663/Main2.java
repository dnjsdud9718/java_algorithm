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
