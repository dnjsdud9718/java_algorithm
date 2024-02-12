package SSAFY.study.week4.t10866;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
    10866 덱
    push_front X : 정수 X를 덱의 앞에 넣는다.
    push_back X : 정수 X를 덱의 뒤에 넣는다.
    pop_front : 덱의 가장 앞에 있는 수를 빼고, 그 수를 출력한다. 만약, empty인 경우 -1을 출력한다.
    pop_back : 덱의 가장 앞에 있는 수를 빼고, 그 수를 출력한다. 만약, empty인 경우 -1을 출력한다.
    size : 덱에 들어 있는 정수의 개수를 출력한다.
    empty : 덱이 비어 있으면 1, 그렇지 않으면 0을 출력한다.
    front : 덱의 가장 앞에 있는 수를 출력, 없으면 -1
    back : 덱의 가장 뒤에 있는 수를 출력, 없으면 -1
 */
public class Main {
    static Deque<Integer> deque;
    static int N;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        deque = new ArrayDeque<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String MODE = st.nextToken();
            switch (MODE) {
                case "push_front":
                    deque.addFirst(Integer.parseInt(st.nextToken()));
                    break;
                case "push_back":
                    deque.addLast(Integer.parseInt(st.nextToken()));
                    break;
                case "pop_front":
                    if(deque.isEmpty()) sb.append(-1);
                    else sb.append(deque.removeFirst());
                    sb.append("\n");
                    break;
                case "pop_back":
                    if(deque.isEmpty()) sb.append(-1);
                    else sb.append(deque.removeLast());
                    sb.append("\n");
                    break;
                case "size":
                    sb.append(deque.size());
                    sb.append("\n");
                    break;
                case "empty":
                    if(deque.isEmpty()) sb.append(1);
                    else sb.append(0);
                    sb.append("\n");
                    break;
                case "front":
                    if(deque.isEmpty()) sb.append(-1);
                    else sb.append(deque.peekFirst());
                    sb.append("\n");
                    break;
                case "back":
                    if(deque.isEmpty()) sb.append(-1);
                    else sb.append(deque.peekLast());
                    sb.append("\n");
                    break;
            }
        }
        System.out.println(sb.toString());
        br.close();
    }

}
