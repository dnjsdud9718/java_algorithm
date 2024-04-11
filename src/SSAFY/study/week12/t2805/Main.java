package SSAFY.study.week12.t2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, MAX_TREE_HEIGHT=-1;
    static int[] trees;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        trees = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            MAX_TREE_HEIGHT = Math.max(MAX_TREE_HEIGHT, trees[i]);
        }


        int left = 0;
        int right = MAX_TREE_HEIGHT;
        int height = 0;
        long answer = MAX_TREE_HEIGHT + 1;
        long minLen = Integer.MAX_VALUE;
        while (left <= right) {
            height = (left + right) / 2;
//            System.out.println(left + " " + right + " " + height);
            long length = bs(height);
            if (length == M) {
                answer = height;
                break;
            } else if (length > M) {
                if (length < minLen) {
                    answer = height;
                    minLen = length;
                }
                left = height + 1;
            } else {
                right = height - 1;
            };
        }


        System.out.println(answer);
        br.close();
    }

    static long bs(int height) {
        long ret = 0;
        for (int i = 0; i < N; i++) {
            if(trees[i] <= height) continue;
            ret += (trees[i] - height);
        }
        return ret;
    }
}
