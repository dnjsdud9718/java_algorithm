package SSAFY.algo.점심식사시간_2383;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SolutionLucky{
    static int N, min;
    static Person[] people;
    static Stairs[] stairs;
    static int pIdx, sIdx;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            people = new Person[12];
            stairs = new Stairs[2];
            pIdx = sIdx = 0;
            // 사람, 계단 정보 담기
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int val = Integer.parseInt(st.nextToken());
                    if(val == 1) people[pIdx++] = new Person(i, j);
                    else if(val != 0) stairs[sIdx++] = new Stairs(i, j, val);
                }
            }
            // 거리 계산
            for (int i = 0; i < pIdx; i++) {
                for (int j = 0; j < sIdx; j++) {
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

    static void subset(int idx, int check) {
        if (idx == pIdx) {
            int A = check;
            int B = check ^ ((1 << pIdx)-1);
            int minute = 0;
            Deque<Integer> queA = new ArrayDeque<>();
            Deque<Integer> queB = new ArrayDeque<>();
            while (true) {
                /*
                    해당 로직은 t분에 계단이 full이고, 한 명이 계단에 마지막에 위치해 있어도, 대기 중인 사람이 들어가지 못하고
                    t+1분에 들어간다. 근데 정답이 될 수 있었던 이유는, 원하는 시간이 모든 사람이 아래 층에 도착한 시간이기 때문에
                    마지막 사람이 계단에서 내려온 후 1분이 필요한데 그 부분이 적용되지 않아 우연치 않게 정답이 되었다.
                 */
                // 계단 진입하기
                for (int i = 0; i < pIdx; i++) {
                    if ((A & (1 << i)) != 0 && minute > people[i].a && queA.size() < 3) {
                        queA.add(stairs[0].v);
                        A ^= (1 << i);
                    }
                }
                for (int i = 0; i < pIdx; i++) {
                    if ((B & (1 << i)) != 0 && minute > people[i].b && queB.size() < 3) {
                        queB.add(stairs[1].v);
                        B ^= (1 << i);
                    }
                }
                // 계단 내려가기
                int t = queA.size();
                for (int i = 0; i < t; i++) {
                    int p = queA.poll();
                    if(p==1) continue;
                    queA.add(p - 1);
                }
                t = queB.size();
                for (int i = 0; i < t; i++) {
                    int p = queB.poll();
                    if(p == 1) continue;
                    queB.add(p - 1);
                }

                if(A==0 && B==0 && queA.isEmpty() && queB.isEmpty()) break;
                minute += 1;
            }
            min = Math.min(min, minute+1);
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
        }
    }
}
