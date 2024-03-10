package SSAFY.study.week7.t12867;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M; // N : 차원(1<= N <= 1,000,000,000), M : 여행 길이(1<=M<=50)
    static int key;
    static HashMap<Integer, Integer>[] hashMap;
    static int[] src;
    static String move;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        hashMap = new HashMap[M + 1];
        src = new int[M];
        for (int i = 0; i <= M; i++) hashMap[i] = new HashMap<>();
        hashMap[0].put(0, 1);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            key = Integer.parseInt(st.nextToken());
            src[i] = key;
            hashMap[0].put(key, 0);
        }
        move = br.readLine();
        for (int i = 1; i <= M; i++) {
            hashMap[i] = new HashMap<>(hashMap[i - 1]);
            key = src[i - 1];
            char dir = move.charAt(i - 1);
            if (dir == '+') {
                hashMap[i].put(key, hashMap[i].get(key) + 1);
            } else {
                hashMap[i].put(key, hashMap[i].get(key) - 1);
            }
            Set<Integer> keys = hashMap[i].keySet();
            for (int j = i - 1; j >= 0; j--) {
                Iterator<Integer> iterator = keys.iterator();
                boolean dup = true;
                while (iterator.hasNext()) {
                    int key = iterator.next();
                    if (!Objects.equals(hashMap[i].get(key), hashMap[j].get(key))) {
                        dup = false;
                        break;
                    }
                }
                if (dup) {
                    System.out.println(0);
                    return;
                }
            }
        }
        System.out.println(1);
        br.close();
    }
}