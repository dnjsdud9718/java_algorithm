package Q1.T11;
/*
 * 1874 silver 5 
 * stack 문제 
 */
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuffer bf = new StringBuffer();
        
        int N = sc.nextInt();
        int [] iArr = new int[N];
        for(int i=0; i<N; i++) iArr[i] = sc.nextInt();

        Stack<Integer> stk = new Stack<>();
        int num = 1;
        boolean flag = true;

        for(int i=0; i<N; i++){
            int su = iArr[i];
            if(su >= num){
                while(su >= num){
                    stk.add(num++);
                    bf.append("+\n");
                }
                stk.pop();
                bf.append("-\n");
            }else { // su 값이 스택 안에 있다는 의미.
                int n = stk.pop();
                if(n != su) {
                    System.out.println("NO");
                    flag = false;
                    break;
                }else {
                    bf.append("-\n");
                }
            }

        }

        if(flag) System.out.println(bf.toString());
        sc.close();
    }
}
