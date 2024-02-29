package SSAFY.algo.점심식사시간_2383;


import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution{
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
//            System.out.println(A + " " + B);
            int minute = 0;
            Deque<Integer> queA = new ArrayDeque<>();
            Deque<Integer> queB = new ArrayDeque<>();
            while (true) {
                minute += 1;
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
                for (int i = 0; i < pIdx; i++) {
                    // minute > people[i].a ??? minute == people[i].a면 계단 앞에 도착, 5분에 도착하면 6분에 들어간다.
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

//                System.out.println(A+" "+ B);
                if(A==0 && B==0 && queA.isEmpty() && queB.isEmpty()) break;
            }
            min = Math.min(min, minute);
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