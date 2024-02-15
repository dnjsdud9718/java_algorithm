package SSAFY.study.week3.t1918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

// 후위표기식 만들기 1918
public class Main {
    // Map.of -> jdk9부터 사용 가능하네.
    public static Map<Character, Integer> priority = Map.of('(', 0, ')', 3, '*', 2, '/', 2, '+',1,'-',1);
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        char[] src = br.readLine().toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        stack.clear();
        int A = 'A'-'A';
        int Z = 'Z'-'A';
        for(char c : src){
            if(c-'A' >= A && c-'A' <= Z ) sb.append(c);
            else{
                if(stack.isEmpty()) stack.addFirst(c);
                else{
                    int p = priority.get(c);
                    switch(c){
                        case '(':
                            stack.addFirst(c);
                            break;
                        case ')':
                            while(!stack.isEmpty() && stack.peekFirst() != '(') sb.append(stack.removeFirst());
                            stack.removeFirst();
                            break;
                        default:
                            while(!stack.isEmpty() && p <= priority.get(stack.peekFirst())) sb.append(stack.removeFirst());
                            stack.addFirst(c);
                            break;
                    }
                }
            }
        }
        while(!stack.isEmpty()) sb.append(stack.removeFirst());
        System.out.println(sb.toString());
        br.close();
    }
}
