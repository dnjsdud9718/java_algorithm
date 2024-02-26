package SSAFY.algo.baekjoon.t2457;

/*
    공주님의 정원 -> 그리디
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, answer;
    static int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static PriorityQueue<Flower> pq;
    static int start, end;
    public static void main(String[] args) throws IOException {
        for (int i = 1; i < 13; i++) days[i] += days[i - 1];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        // 매일 꽃이 피어있어야 하는 구간.
        start = convertToDays(3, 1);
        end = convertToDays(12, 1);


        pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int sM = Integer.parseInt(st.nextToken());
            int sD = Integer.parseInt(st.nextToken());
            int eM = Integer.parseInt(st.nextToken());
            int eD = Integer.parseInt(st.nextToken());
            Flower flower = new Flower(convertToDays(sM, sD), convertToDays(eM, eD));
            if(flower.end <= start || flower.start >= end) continue;
            pq.add(flower);
        }
        // answer
        answer = 0;
        // 풀이
        Flower current = new Flower(0, start);
        Flower next = current;
        while (!pq.isEmpty()) {
//            System.out.println(current);
            if(current.end >= end) break;
            // 현재 피어 있는 꽃보다 일찍 피는 꽃 중에 가장 늦게 지는 꽃
            while (!pq.isEmpty() && pq.peek().start <= current.end ) {
                Flower tmp = pq.poll();
                if(tmp.end > next.end) next = tmp;
            }
            if(current.equals(next)) {
                System.out.println(0);
                return;
            }
            current = next;
//            System.out.println(current);
            answer++;
        }
        if(current.end < end) System.out.println(0);
        else System.out.println(answer);
        br.close();
    }
    static int convertToDays(int m, int d) {
        return days[m-1] + d;
    }
    static class Flower implements Comparable<Flower>{
        int start, end;

        public Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Flower o) {
            if(start != o.start) return start - o.start;
            return -1 * (end - o.end);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Flower flower = (Flower) o;
            return start == flower.start && end == flower.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }

        @Override
        public String toString() {
            return "Flower{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
