package SSAFY.study.week12.t5430;

import java.io.*;
import java.util.*;

public class Main
{
    static int T, N;
    static String cmd, tmp;
    static Deque<Integer> deque = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++){
            cmd = br.readLine();
            N =Integer.parseInt(br.readLine());
            tmp = br.readLine();
            tmp = tmp.substring(1, tmp.length()-1);
            tmp = tmp.replace(","," ");
            StringTokenizer st = new StringTokenizer(tmp);
            deque.clear();
            for(int i=0; i<N; i++){
                deque.add(Integer.parseInt(st.nextToken()));
            }
            int point = 0; // 0: 시작, 1: 끝
            for(int i=0; i<cmd.length(); i++){
                char c = cmd.charAt(i);
                if(c == 'D'){
                    if(deque.isEmpty()){
                        point = -1;
                        break;
                    }
                    if(point == 0) deque.removeFirst();
                    else deque.removeLast();
                }else{
                    point = (point+1)%2;
                }
            }
            if(point == -1){
                sb.append("error");
            }else if(point == 0){
                sb.append("[");
                if(!deque.isEmpty()) sb.append(deque.removeFirst());
                while(!deque.isEmpty()){
                    sb.append(",").append(deque.removeFirst());
                }
                sb.append("]");
            }else{
                sb.append("[");
                if(!deque.isEmpty()) sb.append(deque.removeLast());
                while(!deque.isEmpty()){
                    sb.append(",").append(deque.removeLast());
                }
                sb.append("]");
            }
            sb.append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}