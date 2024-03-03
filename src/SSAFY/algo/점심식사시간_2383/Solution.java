package SSAFY.algo.점심식사시간_2383;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
/*
    N * N 크기의 정사각형 모양의 방에 사람들이 모여 있다.
    점심을 먹기 위해 아래 층으로 내려가야 하는데, 밥을 빨리 먹기 위해 최대한 빠른 시간 내에 내려가야 한다.
    반 안의 사람들은 P로, 계단 입구를 S라고 한다.
    이동 완료 시간은 모든 사람들이 계단을 내려가 아래 층으로 이동을 완료한 시간.
    사람들이 아래층으로 이동하는 시간은 계단 입구까지 이동 시간과 계단을 내려가는 시간이 포함된다.

    1. 계단 입구까지 이동 시간 -> |PR - SR| + |PC - SC|
    2. 계단을 내려가는 시간
          -> 계단을 내려가는 시간은 계단 입구에 도착한 후부터 계단을 완전히 내려갈 때까지의 시간이다.
          -> 계단 입구에 도착하면, 1분 후 아래칸으로 내려 갈 수 있다.
          -> 계단 위에는 최대 3명까지 동시에 올라갈 수 있다.
          -> 이미 계단에 3명이 내려가고 있는 경우, 그 중 한 명이 계단을 완전히 내려갈 때까지 계단 입구에서 대기해야 한다.
          -> 계단마다 길이 K가 주어지며, 계단에 올라간 후 완전히 내려가는데 K분 걸린다.

    사람의 위치와 계단 입구의 위치 및 계단의 길이 정보가 표시된 N*N 크기의 지도가 주어진다.
    이때, 모든 사람들이 계단을 내려가 이동이 완료되는 시간이 최소가 되는 경우를 찾고,
    그 때의 소요시간을 출력하는 프로그램을 작성하라.

    t분에 A계단에 세 명이 올라가 있고, 그 중 한명이 마지막 계단에 위치해 있다. 그리고 t분 전(t-1분)에 계단 앞에 도착하여 대기 중인 사람이 있다면
    그 사람은 t분에 계단에 들어갈 수 있다.
    코드 순서를 구성할 때, 계단 내려가기 이후, 계단 진입하기를 수행하는 것이 바람직한 것 같다.
    앞서 내가 짠 코드는 내가 생각한 로직처럼 동작하지 않았지만 정답이 된 이유는 minute(분)을 업데이트하는 위치 때문이다. 의도하지 않았으나
    정답이 되었다.
    1. 계단 진입하기, 2. 계단 내려가기 순서로 했을 때, 위 같은 상황에 대기 중인 사람이 들어가지 못했으나, 최종 도착시간 1을 더해주는 것이 빠졌기
    때문에 정답이 되었다.
 */
public class Solution{
    static int N, min;
    static Person[] people;
    static Stairs[] stairs;
    static int pLen, sLen;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            people = new Person[10];
            stairs = new Stairs[2];
            pLen = sLen = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int v = Integer.parseInt(st.nextToken());
                    if (v == 1) people[pLen++] = new Person(i, j);
                    else if (v != 0) stairs[sLen++] = new Stairs(i, j, v);
                }
            }
            // 거리 계산
            for (int i = 0; i < pLen; i++) {
                for (int j = 0; j < sLen; j++) {
                    if(j==0) people[i].a = Math.abs(people[i].r - stairs[j].r) + Math.abs(people[i].c - stairs[j].c);
                    else people[i].b = Math.abs(people[i].r - stairs[j].r) + Math.abs(people[i].c - stairs[j].c);
                }
            }
            min = Integer.MAX_VALUE;
            subset(0, 0);
            sb.append("#").append(t).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    static void logic(int A) {
        int B = A ^ ((1 << pLen) - 1); // A는 0번 계단을 이용하는 그룹, B는 1번 계단을 이용하는 그룹
        int minute = 1;
        int t;
        Deque<Integer> queA = new ArrayDeque<>();
        Deque<Integer> queB = new ArrayDeque<>();
        while (true) {
            // 계단 내려가기
            t = queA.size();
            for (int i = 0; i < t; i++) {
                int person = queA.poll();
                if(person == 0) continue;
                queA.add(person - 1);
            }
            t = queB.size();
            for (int i = 0; i < t; i++) {
                int person = queB.poll();
                if(person == 0) continue;
                queB.add(person - 1);
            }
            // 계단 진입하기
            for (int i = 0; i < pLen; i++) {
                if ((A & (1 << i)) != 0 && minute > people[i].a && queA.size() < 3) {
                    A ^= (1 << i);
                    queA.add(stairs[0].v - 1); // 진입한 시간에 한칸 내려간다.
                }
            }
            for (int i = 0; i < pLen; i++) {
                if ((B & (1 << i)) != 0 && minute > people[i].b && queB.size() < 3) {
                    B ^= (1 << i);
                    queB.add(stairs[1].v - 1); // 진입한 시간에 한칸 내려간다.
                }
            }
            if(A==0 && B ==0 && queA.isEmpty() && queB.isEmpty()) break;
            minute += 1;
        }
        min = Math.min(min, minute);
        return;
    }
    static void subset(int idx, int check) {
        if (idx == pLen) {
            logic(check);
            return;
        }
        subset(idx + 1, check | (1 << idx));
        subset(idx + 1, check);
    }
    static class Stairs {
        int r, c, v;
        public Stairs(int r, int c, int v) {
            this.r = r;
            this.c = c;
            this.v = v;
        }
    }
    static class Person {
        int r, c, a, b;
        public Person(int r, int c) {
            this.r = r;
            this.c = c;
            this.a = 0;
            this.b = 0;
        }
    }
}