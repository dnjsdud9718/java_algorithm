package SSAFY.study.week12.t16722;

import java.io.*;
import java.util.*;
public class Main
{
    static Map<String, Integer> map = new HashMap<>(){{
        put("CIRCLE", 0);
        put("TRIANGLE", 1);
        put("SQUARE", 2);
        put("YELLOW", 0);
        put("RED", 1);
        put("BLUE", 2);
        put("GRAY", 0);
        put("WHITE", 1);
        put("BLACK", 2);
    }};
    static Pic[] pic = new Pic[9];
    static int N, score;
    static int[] tgt = new int[3];
    static boolean[] hap = new boolean[1<<9];
    static boolean[] visited = new boolean[1<<9];
    static int count = 0;
    static int count2 = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i=0; i<9; i++){
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();
            String c = st.nextToken();

            pic[i] = new Pic(map.get(a), map.get(b), map.get(c));
        }
        // combination 9C3
        comb(0,0);
        Arrays.fill(visited, false);
        N = Integer.parseInt(br.readLine());
        score = 0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            if("H".equals(st.nextToken())){
                int a1 = Integer.parseInt(st.nextToken())-1;
                int a2 = Integer.parseInt(st.nextToken())-1;
                int a3 = Integer.parseInt(st.nextToken())-1;
                if(hap[(1<<a1) | (1<<a2) | (1<<a3)] && !visited[(1<<a1) | (1<<a2) | (1<<a3)]) {
                    count2++;
                    score++;
                    visited[(1<<a1) | (1<<a2) | (1<<a3)] = true;
                }else score--;
            }else{ // G
                if(count2 == count) {
                    count--;
                    score+=3;
                }
                else score-=1;
            }
        }
        System.out.println(score);
        br.close();
    }
    static boolean isHab(){
        Pic a = pic[tgt[0]];
        Pic b = pic[tgt[1]];
        Pic c = pic[tgt[2]];
        // 도형
        if((a.figure + b.figure + c.figure) %3 != 0) return false;
        // 색
        if((a.color + b.color + c.color) %3 != 0) return false;
        //배경
        if((a.back + b.back + c.back) %3 != 0) return false;

        return true;

    }

    static void comb(int srcIdx, int tgtIdx){
        if(tgtIdx == 3){
            if(visited[(1<<tgt[0]) | (1<<tgt[1]) | (1<<tgt[2])]) return;
            visited[(1<<tgt[0]) | (1<<tgt[1]) | (1<<tgt[2])] = true;
            hap[(1<<tgt[0]) | (1<<tgt[1]) | (1<<tgt[2])] = isHab();
            if(hap[(1<<tgt[0]) | (1<<tgt[1]) | (1<<tgt[2])]){
                // System.out.println(tgt[0] + " " + tgt[1] + " " + tgt[2]);
                count++;
            }
            return;
        }
        for(int i=srcIdx; i<9; i++){
            tgt[tgtIdx] = i;
            comb(i+1, tgtIdx+1);
        }
    }
    static class Pic{
        int figure;
        int color;
        int back;
        public Pic(int figure, int color, int back){
            this.figure = figure;
            this.color = color;
            this.back = back;
        }
    }
}

