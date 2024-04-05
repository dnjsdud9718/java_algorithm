package SSAFY.study.week11.t16692;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2 {
    static int N, K;
    static int[] customers;
    static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
        if(o1[1] == o2[1]) return o1[0] - o2[0];
        return o1[1] - o2[1];
    });
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            pq.add(new int[]{i, 0});
        }

        K = Integer.parseInt(st.nextToken());
        customers = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            customers[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < K; i++) {
            int[] cashier = pq.poll();
            cashier[1] += customers[i];
//            customers[i] = cashier[0];
            sb.append(cashier[0] + 1).append(" ");
            pq.add(new int[]{cashier[0], cashier[1]});
        }
//        System.out.println(Arrays.toString(customers));
        System.out.println(sb);
        br.close();
    }
}
