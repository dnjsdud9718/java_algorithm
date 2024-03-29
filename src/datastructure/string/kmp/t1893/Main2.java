package datastructure.string.kmp.t1893;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {
    static final String NO_SOLUTION = "no solution";
    static final String UNIQUE = "unique: ";
    static final String AMBIGUOUS = "ambiguous: ";
    static int T;
    static char[] A, W, S;
    static Map<Character, Integer> map = new HashMap<>();
    static int aLen, wLen, sLen;
    static Deque<Integer> queue = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            A = br.readLine().toCharArray();
            aLen = A.length;
            W = br.readLine().toCharArray();
            wLen = W.length;
            S = br.readLine().toCharArray();
            sLen = S.length;

            queue.clear();
            map.clear();
            //Hash
            for (int i = 0; i < aLen; i++) {
                map.put(A[i], i);
            }
            for (int i = 0; i < aLen; i++) { // 어떤 문자 x를 자신으로 돌아올 때까지 시프트
//                System.out.println(Arrays.toString(W));
                if (kmp(W)) {
                    queue.add(i);
                }
                for (int j = wLen-1; j >= 0; j--) {
                    W[j] = A[(map.get(W[j])+1)%aLen];
                }
            }
            if(queue.isEmpty()) sb.append(NO_SOLUTION).append("\n");
            else if (queue.size() == 1) {
                sb.append(UNIQUE).append(queue.poll()).append("\n");
            } else {
                sb.append(AMBIGUOUS);
                while (!queue.isEmpty()) {
                    sb.append(queue.poll());
                    sb.append(" ");
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
        br.close();
    }
    static boolean kmp(char[] pattern) {
        int[] pi = makePi(pattern);
        int j=0;
        int cnt = 0;
        for (int i = 0; i < sLen; i++) {
            while (j > 0 && S[i] != pattern[j]) {
                j = pi[j - 1];
            }
            if (S[i] == pattern[j]) {
                if (j == pattern.length - 1) {
                    j = pi[j];
                    cnt++;
                } else j++;
            }
        }
        if(cnt == 0 || cnt > 1) return false;
        return true;
    }

    static int[] makePi(char[] pattern) {
        int[] pi = new int[pattern.length];
        int j=0;
        for (int i = 1; i < pattern.length; i++) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = pi[j - 1];
            }
            if (pattern[i] == pattern[j]) {
                pi[i] = ++j;
            }
        }
        return pi;
    }
}
