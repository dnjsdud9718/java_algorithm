package SSAFY.study.week15.t6064;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int T, N, M, X, Y;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            boolean flag = false;
            int gcdRet = gcd(n, m);
            int lcm = n * (m / gcdRet);
            // System.out.println("lcm " + lcm);
            for (int i = x; i <= lcm; i += n) {
                int a = i % n == 0 ? n : i % n;
                int b = i % m == 0 ? m : i % m;
                // System.out.println(i + " " + a + " " + b + " " + t );
                if (a == x && b == y) {
                    sb.append(i).append("\n");
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                sb.append(-1).append("\n");
            }
        }
        System.out.println(sb);
        br.close();
    }

    public static int gcd(int x, int y) {
        if (y == 0) {
            return x;
        }
        return gcd(y, x % y);
    }
}
