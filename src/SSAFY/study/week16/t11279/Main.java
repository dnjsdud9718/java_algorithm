package SSAFY.study.week16.t11279;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static int N;
    static PriorityQueue<Integer> pq = new PriorityQueue((o1, o2) -> {
        return (Integer) o2 - (Integer) o1;
    });
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            if (x == 0) {
                if (pq.isEmpty()) {
                    sb.append(0).append("\n");
                } else {
                    sb.append(pq.poll()).append("\n");
                }
            } else {
                pq.add(x);
            }
        }
        System.out.println(sb);
        sc.close();
    }
}

