package SSAFY.study.t11723;
// 비어있는 공집합 S가 주어졌을 때, 아래 연산을 수행하는 프로그램을 작성하시오.
// add x: S에 x를 추가한다. (1 ≤ x ≤ 20) S에 x가 이미 있는 경우에는 연산을 무시한다.
// remove x: S에서 x를 제거한다. (1 ≤ x ≤ 20) S에 x가 없는 경우에는 연산을 무시한다.
// check x: S에 x가 있으면 1을, 없으면 0을 출력한다. (1 ≤ x ≤ 20)
// toggle x: S에 x가 있으면 x를 제거하고, 없으면 x를 추가한다. (1 ≤ x ≤ 20)
// all: S를 {1, 2, ..., 20} 으로 바꾼다.
// empty: S를 공집합으로 바꾼다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// input
// 첫째 줄에 수행해야 하는 연산의 수 M (1 ≤ M ≤ 3,000,000)이 주어진다.
// 둘째 줄부터 M개의 줄에 수행해야 하는 연산이 한 줄에 하나씩 주어진다.

// output
// check 연산이 주어질때마다, 결과를 출력한다.
public class Main {
    static String mode;
    static int check, N;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        check = 0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            mode = st.nextToken();
            int val;
            switch(mode){
                case "add":
                    val = Integer.parseInt(st.nextToken());
                    check |= (1<<(val-1));
                    break;
                case "remove":
                    val = Integer.parseInt(st.nextToken());
                    if((check & (1<<(val-1))) != 0) check ^= (1<<(val-1));
                    break;
                case "check":
                    val = Integer.parseInt(st.nextToken());
                    if((check & (1<<(val-1))) != 0) sb.append(1);
                    else sb.append(0);
                    sb.append("\n");
                    break;
                case "toggle":
                    val = Integer.parseInt(st.nextToken());
                    check ^= (1<<(val-1));
                    break;
                case "all":
                    check = (1<<20)-1;
                    break;
                case "empty":
                    check = 0;
                    break;
                default:
                    break;
            }
        }
        System.out.println(sb.toString());
        br.close();
    }
}
