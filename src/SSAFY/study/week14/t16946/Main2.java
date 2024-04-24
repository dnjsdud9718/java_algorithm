package SSAFY.study.week14.t16946;

import java.util.*;
import java.io.*;
import java.util.stream.Stream;

public class Main2
{
    static final int MOD = 10;
    static int N, M, num;
    static int[] counts;
    static int [][] origin;
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    static Deque<int[]> queue = new ArrayDeque<>();
    static Deque<int[]> queue2 = new ArrayDeque<>();
    static Set<Integer> set = new HashSet<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        origin = new int[N][M];
        counts = new int[2000000]; // 군집 0의 갯수를 저장할 것.
        for(int i=0; i<N; i++){
            int[] tmp = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            for(int j=0; j<M; j++){
                int  n = tmp[j];
                if(n == 1) {
                    origin[i][j] = 1; //벽
                }else{
                    queue2.add(new int[]{i, j});
                }
            }
        }
        num = 1;
        while (!queue2.isEmpty()) {
            int[] next = queue2.poll();
            if(origin[next[0]][next[1]] != 0) continue;
            ++num;
            int cnt = 0;
            queue.add(new int[]{next[0],next[1]});
            origin[next[0]][next[1]] = num;
            while(!queue.isEmpty()){
                int[] cur = queue.poll();
                cnt++;
                for(int d=0; d<4; d++){
                    int nr = cur[0] + dr[d];
                    int nc = cur[1] + dc[d];
                    if(nr < 0 || nr == N || nc < 0 || nc == M || origin[nr][nc] != 0) continue;
                    origin[nr][nc] = num;
                    queue.add(new int[]{nr,nc});
                }
            }
            counts[num] = cnt;
        }
        // 여기까지 오면 origin[][] 배열에 군집 넘버링 완료

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(origin[i][j] == 1){ // 원본 배열의 값이 1인 경우 이동 가능 수 계산
                    int sum = 1;
                    set.clear();
                    for(int d = 0; d<4; d++){
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if(nr < 0 || nr == N || nc < 0 || nc == M || origin[nr][nc] == 1 || set.contains(origin[nr][nc]) ) continue;
                        set.add(origin[nr][nc]);
                        sum += counts[origin[nr][nc]];
                    }
                    sb.append(sum%MOD);
                } else sb.append(0);
            }
            sb.append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}

