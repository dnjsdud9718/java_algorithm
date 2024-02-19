package SSAFY.B형.week2.중위순회;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    BST -> in-order
    8   // 첫 번째 케이스의 N
    1 W 2 3
    2 F 4 5
    3 R 6 7
    4 O 8
    5 T
    6 A
    7 E
    8 S
 */
public class Solution {
    static StringBuilder sb = new StringBuilder();
    static String ans;
    static int N;
    static char[] bst;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int t = 1; t <= 10; t++) {
            N = Integer.parseInt(br.readLine());
            bst = new char[N + 1];
            for (int i = 1; i <= N; i++) {
                String s = br.readLine().split(" ")[1];
                bst[i] = s.charAt(0);
            }
//            System.out.println("Arrays.toString(bst) = " + Arrays.toString(bst));
            ans = "";
            inOrder(1);
//            System.out.println();
            sb.append('#').append(t).append(' ').append(ans).append('\n');
        }
        System.out.println(sb.toString());
        br.close();
    }
    public static void inOrder(int x) {
        if(x > N) return;

        inOrder(x * 2);
//        System.out.print(bst[x]);
        ans += bst[x];
        inOrder(x * 2 + 1);

    }
}
