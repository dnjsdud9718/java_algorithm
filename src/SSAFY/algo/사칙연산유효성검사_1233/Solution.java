package SSAFY.algo.사칙연산유효성검사_1233;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// 노드 유효성 검사
// 노드가 숫자이면 -> 자식이 없어야 한다.
// 노드가 연산자이면 자식노드 2개의 유효성 의존
public class Solution {
    static int N, ans;
    static char[] node;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int t=1; t<=10; t++){
            N = Integer.parseInt(br.readLine());
            node = new char[N+1];
            for(int i=1; i<=N; i++){
                node[i] = br.readLine().split(" ")[1].charAt(0);
            }
            //  자식 노드가 1개인 경우 바로 0 처리
            if( N % 2 == 0) {
                sb.append("#").append(t).append(" ").append(0).append("\n");
                continue;
            }
//            ans = 1;
//            int idx = N; // 마지막 노드부터 유효성 검사
//
//            while(idx != 1){
//                //자식 노드가 모두 숫자 이고, 부모노드가 연산자이어야 함.
//                if(!Character.isDigit(node[idx]) ||  !Character.isDigit(node[idx-1]) || Character.isDigit(node[idx/2])){
//                    ans = 0;
//                    break;
//                }
//                node[idx/2] = '1'; // 부모 노드를 임의의 숫자로 처리
//                idx -= 2;
//            }
//            sb.append("#").append(t).append(" ").append(ans).append("\n");
            ans = dfs(1) ? 1 : 0;
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
    public static boolean dfs(int x){
        if(x > N) return false;
        if(Character.isDigit(node[x])){
            if(x*2> N) return true;
            return false;
        }else{
            return dfs(x*2) && dfs(x*2+1);
        }
    }
}
