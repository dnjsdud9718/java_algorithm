package SSAFY.study.week4.t1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    baekjoon 1202 보석 도둑
    그리디, 우선순위 큐, 정렬, 구현
    세계적인 도둑 상덕이는 보석점을 털기로 결심했다.
    상덕이가 털 보석점에는 보석이 총 N개 있다. 각 보석은 무게 Mi와
    가격 Vi를 가지고 있다. 상덕이는 K개 가방을 가지고 있고, 각 가방에
    담을 수 있는 최대 무게는 Ci다 가방에는 최대 한 개 보석만 넣을 수 있다.
    상덕이가 훔칠 수 있는 보석의 최대 가격을 구하는 프로그램 작성

    (1<=N, K<= 300,000)
    (0 <Mi, Vi <= 1,000,000)
    (1 <= Ci <= 1000,000,000)
    모든 숫자는 양의 정수

    정렬
    -> 보석은 무게 기준 오름차순
    -> 가방은 무게 기준 오름차순
    우선순위 큐(가격이 비싼 것이 우선순위가 높다)
    -> 현재 바라보고 있는 가방이 담을 수 있는 모든 보석을 추가.
    그리디
    -> 우선순위 큐의 top에 있는 원소를 가방(현재 바라보고 있는)에 집어 넣는다.
    -> 다음 가방을 바라본다.
 */
public class Main{
    static int N, K; // N: 보석의 수, K: 가방의 수
    static Jewelry[] jewelry;
    static int[] bags;
    static PriorityQueue<Integer> pq; // 가격만 가지고 있으면 되겠네
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        jewelry = new Jewelry[N];
        bags = new int[K];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewelry[i] = new Jewelry(w, v);
        }
        Arrays.sort(jewelry);
        for (int i = 0; i < K; i++) {
            int w = Integer.parseInt(br.readLine());
            bags[i] = w;
        }
        Arrays.sort(bags);
//        System.out.println(Arrays.toString(bags));
//        System.out.println(Arrays.toString(jewelry));
        pq = new PriorityQueue<>(Collections.reverseOrder());
        int idx = 0;
        long sum = 0; // overflow 조심.
        for (int i = 0; i < K; i++) {
            // i번째 가방 넣을 수 있는 모든 보석을 우선순위 큐에 넣는다.
            while (idx < N && jewelry[idx].w <= bags[i]) {
                pq.add(jewelry[idx].v);
                idx++;
            }
            if (!pq.isEmpty()) {
                sum += pq.poll();
            }
        }
        System.out.println(sum);
        br.close();

    }
    static class Jewelry implements Comparable<Jewelry> {
        int w;
        int v;

        public Jewelry(int w, int v){
            this.w = w;
            this.v = v;
        }

        @Override
        public int compareTo(Jewelry o) {
            return w - o.w;
        }

        @Override
        public String toString() {
            return "Jewelry{" +
                    "w=" + w +
                    ", v=" + v +
                    '}';
        }
    }
}