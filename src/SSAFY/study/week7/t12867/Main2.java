package SSAFY.study.week7.t12867;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*
    backjoon 12867
    idea => N차원을 M차원으로 축소하기
    결국 사용하는 차원의 수는 최대 M개다.
 */
public class Main2 {
    static int N; // N차원
    static int M; // 여행 길이
    static int[] src; // 여행 계획 저장.
    static int[] dimension; // 사용하는 차원을 저장: dimension[i]에 저장되는 차원을 i차원으로 간주.(차원 축소)
    static int dLen; // 사용되는 차원의 수.
    static int[] points; // 상태 저장.
    static String move; // +- 문자열
    static HashMap<Integer, Integer> dIdx = new HashMap<>(); // key: dimension[idx], value : idx
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        src = new int[M];
        dimension = new int[M+1];
        dLen = 1;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int v = Integer.parseInt(st.nextToken()); // v차원
            src[i] = v;
            boolean check = false; // v차원이 축소되었는가?
            for (int j = 0; j <= M; j++) {
                if (dimension[j] == v) { // v차원이 j차원으로 축소되었는지 확인
                    check = true;
                    break;
                }
            }
            if(!check) { // v차원이 축소되지 않았다면 -> 축소 진행.
                dIdx.put(v, dLen);
                dimension[dLen++] = v; // v차원이 dLen차원으로 축소됨.
            }
        }

        move = br.readLine();
        points = new int[dLen]; // 여행 상태 기록.
        HashMap<String, Boolean> map = new HashMap<>(); // key: 여행 상태, value : 무의미.
        String s = "";
        for (int i = 0; i < dLen; i++) s += 0;
        map.put(s, true); // 초기화.
        for (int i = 0; i < M; i++) { // M개의 차원에 대하여
            int point = src[i];
            char dir =move.charAt(i);
            if (dir == '+') {
                points[dIdx.get(point)]++;
            } else {
                points[dIdx.get(point)]--;
            }
            s = "";
            for (int j = 0; j < dLen; j++) {
                s += points[j];
            }


            if (map.containsKey(s)) { // 중복
                System.out.println(0);
                return;
            } else {
                map.put(s, true);
            }
        }
        System.out.println(1);
        br.close();
    }
}
