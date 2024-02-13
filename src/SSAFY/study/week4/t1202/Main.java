package SSAFY.study.week4.t1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int [] c;
    static Item[] items;
    static PriorityQueue<Item>[] pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        items = new Item[N + 1];
        K = Integer.parseInt(st.nextToken());
        c = new int[K];
        pq = new PriorityQueue[K];
        for (int i = 0; i < K; i++) {
            pq[i] = new PriorityQueue<>();
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            items[i] = new Item(M, V);
        }
        for (int i = 0; i < K; i++) {
            int C = Integer.parseInt(br.readLine());
            c[i] = C;
            for (Item it : items) {
                if(C >= it.weight) pq[i].add(it);
            }
        }

        br.close();
    }

    static class Item implements Comparator<Item> {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.value - o2.value;
        }
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
