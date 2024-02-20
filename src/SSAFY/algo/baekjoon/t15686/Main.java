package SSAFY.algo.baekjoon.t15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<Point> home; // 집 좌표 저장하는 리스트
    static List<Point> chicken; // 치킨집 저장하는 리스트
    static int[] tgt; // 조합
    static int[][] dist; // 집 - 치킨집 사이 거리 계산 (행: 집, 열: 치킨집)
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        home = new ArrayList<>();
        chicken = new ArrayList<>();
        tgt = new int[M];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int val = Integer.parseInt(st.nextToken());
                if(val == 1){
                    home.add(new Point(i, j));
                }else if(val == 2){
                    chicken.add(new Point(i, j));
                }
            }
        }
        dist = new int[2 * N][13]; // 문제에 제시된 집과 치킨집의 최대 크기 고려
        for (int i = 0; i < home.size(); i++) {
            Point h = home.get(i);
            for (int j = 0; j < chicken.size(); j++) {
                Point c = chicken.get(j);
                // i번째 집과 j번째 치킨집 사이의 거리 계산
                dist[i][j] = Math.abs(h.y - c.y) + Math.abs(h.x - c.x);
            }
        }
        ans = Integer.MAX_VALUE;
        // M개의 치킨집을 택하고, 해당 치킨집들과 집들 사이의 최단거리의 합을 구하고  ans를 갱신할지 말지 결정.
        comb(0, 0);
        System.out.println(ans);
        br.close();
    }

    public static void comb(int srcIdx, int tgtIdx) {
        if (tgtIdx == M) {
            int sum = 0;
            for (int i = 0; i < home.size(); i++) {
                int k = Integer.MAX_VALUE;
                for (int j = 0; j < M; j++) {
                    k = Math.min(k, dist[i][tgt[j]]); // M개 치킨 집 중에서 i번째 집과 거리가 가장 가까운 치킨집 사이의 거리 택
                }
                sum += k; // 도시의 치킨 거리
            }
            // 도시의 치킨 거리 갱신
            ans = Math.min(ans, sum);
            return;
        }
        for (int i = srcIdx; i < chicken.size(); i++) {
            tgt[tgtIdx] = i; // tgt 배열에는 인덱스가 들어간다.
            comb(i + 1, tgtIdx + 1);
        }
    }
    static class Point{
        int y, x;
        public Point() {}
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
