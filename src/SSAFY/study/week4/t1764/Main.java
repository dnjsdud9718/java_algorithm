package SSAFY.study.week4.t1764;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//3 4
//ohhenrie
//charlie
//baesangwook
//obama
//baesangwook
//ohhenrie
//clinton
public class Main {
    static int N, M;
    static Map<String, Integer> map;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new HashMap<>();
        PriorityQueue<String> pq = new PriorityQueue<>();
        for (int i = 0; i < N + M; i++) {
            String key = br.readLine();
            Integer val = map.get(key);
            if(val == null) map.put(key, 0);
            else {
                pq.add(key);
                map.put(key, val + 1);
            }
        }
        sb.append(pq.size()).append("\n");
        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
}
