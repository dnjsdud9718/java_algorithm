package SSAFY.study.week6.t4153;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static String yes = "right";
    static String no = "wrong";
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();
    static int a,b,c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            pq.clear();
            if(a+b+c == 0 ) break;
            pq.add(a);
            pq.add(b);
            pq.add(c);
            if (solve(pq.poll(), pq.poll(), pq.poll())) {
                sb.append(yes);
            }else sb.append(no);
            sb.append('\n');
        }
        System.out.println(sb);
        br.close();
    }
    public static boolean solve(int a, int b, int c){
        return c*c == (a*a + b*b);
    }
}
