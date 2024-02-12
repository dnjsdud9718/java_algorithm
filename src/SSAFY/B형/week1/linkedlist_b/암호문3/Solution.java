package SSAFY.B형.week1.linkedlist_b.암호문3;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
    static int N, M;
    static LinkedList<Integer> list;
    static LinkedList<Integer> sub;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int t=1; t<=10; t++) {
            N = Integer.parseInt(br.readLine());
            list = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) list.add(Integer.parseInt(st.nextToken()));
            M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                String MODE = st.nextToken();
                int x, y;
                switch (MODE) {
                    case "I":
                        // I x, y, s
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());
                        sub = new LinkedList<>();
                        for (int i = 0; i < y; i++) sub.add(Integer.parseInt(st.nextToken()));
                        list.addAll(x, sub);
                        break;
                    case "D":
                        // D x, y
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());
                        for (int i = 0; i < y; i++) list.remove(x);
                        break;
                    case "A":
                        // A y, s
                        y = Integer.parseInt(st.nextToken());
                        for (int i = 0; i < y; i++) {
                            list.addLast(Integer.parseInt(st.nextToken()));
                        }
                        break;
                }
            }
            sb.append("#").append(t).append(" ");
            for (int i = 0; i < 10; i++) sb.append(list.get(i)).append(" ");
            sb.append("\n");


        }
        System.out.println(sb.toString());
    }
}
