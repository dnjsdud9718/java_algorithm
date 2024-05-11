package SSAFY.study.week15.t17219;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static String site, pw;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<String, String> map = new HashMap<>();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            site = st.nextToken();
            pw = st.nextToken();
            map.put(site, pw);
        }
        for (int i = 0; i < M; i++) {
            site = br.readLine();
            sb.append(map.get(site)).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
