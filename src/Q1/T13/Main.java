package Q1.T13;
/*
 * 2164 silver 5 
 * 
 */
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Queue<Integer> list = new LinkedList<>();
        for(int i = 0; i<N; i++) list.add(i);
        while(list.size() != 1){
            list.poll();
            list.add(list.poll());
        }
        System.out.println(list.poll());

        sc.close();
    }
}
