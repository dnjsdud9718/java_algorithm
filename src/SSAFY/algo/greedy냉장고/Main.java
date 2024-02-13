package SSAFY.algo.greedy냉장고;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, CNT;
    static class Item {
        int start;
        int end;

        public Item(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Item> pq = new PriorityQueue<>((o1, o2) -> {
            return o1.end - o2.end;
        });
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.add(new Item(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Item t = pq.poll();
        int s = t.start;
        int e = t.end;
        CNT = 1;
        while (!pq.isEmpty()) {
            Item i = pq.poll();
            if (e < i.start) {
                CNT++;
                s = i.start;
                e = i.end;
            }
        }
        System.out.println(CNT);
        br.close();


    }
}
