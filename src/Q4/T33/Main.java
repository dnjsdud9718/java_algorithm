package Q4.T33;
/*
 * 1715 gold 5
 * 묶음 카드 정렬 횟수 최소화하기
 * 두 묶음 A, B를 정렬 횟수: A+B
 * 묶음이 여러개인 경우 순서에 따라 횟수가 달라진다.
 *  
 */
import java.util.Scanner;
import java.util.PriorityQueue;
public class Main {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            return o1 - o2;
        });
        for(int i=0; i<N; i++) pq.add(sc.nextInt());
        int answer = 0;
        while(pq.size() != 1){
            int tmp = pq.poll() + pq.poll();
            answer+=tmp;
            pq.add(tmp);
        }
        System.out.println(answer);
        sc.close();
    }
}
