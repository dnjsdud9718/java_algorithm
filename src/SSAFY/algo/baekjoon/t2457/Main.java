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
        // 부분합
        for (int i = 1; i < 13; i++) days[i] += days[i - 1];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        // 매일 꽃이 피어있어야 하는 구간. 3/1 ~ 11/30
        start = convertToDays(3, 1);
        end = convertToDays(11, 30);


        pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int sM = Integer.parseInt(st.nextToken());
            int sD = Integer.parseInt(st.nextToken());
            int eM = Integer.parseInt(st.nextToken());
            int eD = Integer.parseInt(st.nextToken());
            Flower flower = new Flower(convertToDays(sM, sD), convertToDays(eM, eD));
            // 선택될 수 없는 꽃을 미리 거른다.
            // flower.end(꽃이 진 상태), flower.start(꽃이 핀 상태)
            // 3월 1일에 꽃이 졌다면 들어갈 수 없다.
            //         3.1                       11.30
            // |--------|--------------------------|--------
            if(flower.end <= start || flower.start > end) continue;
            pq.add(flower);
        }
        // answer
        answer = 0;
        // 풀이
        Flower current = new Flower(0, start);
        Flower next = current;
        while (!pq.isEmpty()) {
            // 현재 선택된 꽃이 11월30 이후에 지면 더 이상 탐색을 할 필요가 없다.
            // 11.30
            //   |-----------------------(선택된 꽃이 11월 30일 이후에 진다)
            if(current.end > end) break;
            // 현재 선택된 꽃이 지는 시기보다 일찍 피는 꽃 중에 지는 시기가 가장 늦은 꽃 선택(그리디 전략)
            // 경계값 고려하는 것이 중요 -> 현재 선택된 꽃이 지는 시기(current.end)에 피는 꽃을 선택 -> 꽃이 끊기지 않고 정원에 핀다.pq.peek().start == current.end
            while (!pq.isEmpty() && pq.peek().start <= current.end ) {
                Flower tmp = pq.poll();
                if(tmp.end > next.end) next = tmp;
            }
            // 조건을 만족하는 꽃을 선택하지 못했다. -> 도중에 끊기는 구간 발생.
            if(current.equals(next)) {
                System.out.println(0);
                return;
            }
            current = next;
            answer++;
        }
        // 가능한 모든 선택 이후 조건을 만족하지 못하는 경우.
        //          11.30
        // -----------|
        if(current.end <= end) System.out.println(0);
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
            if(start != o.start) return start - o.start; // 꽃이 빨리 피는 순서
            return -1 * (end - o.end); // 꽃이 늦게 지는 순서
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
