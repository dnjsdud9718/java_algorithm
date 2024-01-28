package SSAFY.B.수열_편집;
/*
 N개의 10억 이하 자연수로 이뤄진 수열이 주어진다. 이 수열은 완성된 것이 아니라 M번의 편집을 거쳐 완성된다고 한다.
 완성된 수열에서 인덱스 L의 데이터를 출력하는 프로그램을 작성하시오.
 만약 편집이 끝난 후 L이 존재하지 않으면 -1을 출력한다.

 입력
 첫 줄에 T: test_case
 다음 줄부터 테스트 케이스의 별로 첫 줄에 수열의 길이 N, 추가 횟수 M, 출력할 인덱스 번호 L이 주어지고, 다음 줄에 수열이 주어진다.
 그 다음 M개의 줄에 걸쳐 추가할 인덱스와 숫자 정보가 주어진다. 5<=N<=1000, 1<=M<=1000, 6<=L<=N+M
 각 I, D, C 명령에서 입력 받는 인덱스의 위치가 존재하지 않는 불가능한 경우는 입력으로 들어오지 않는다.
 I 2 7 : 2번 인덱스 앞에 7을 추가하고, 한칸 씩 뒤로 이동한다.
 D 4 : 4번 인덱스 자리를 지우고, 한 칸씩 앞으로 이동한다.
 C 3 8 : 3번 인덱스 자리를 8로 바꾼다.
 */
import java.util.Scanner;
import java.util.stream.Stream;
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int test_case = 1; test_case <= T; test_case++){
            Node head = new Node();
            int N = sc.nextInt();
            int M = sc.nextInt();
            int L = sc.nextInt();
            int[] inputs = Stream.of(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            String A = sc.next();
            switch(A){
                case "D":
                    delete(head, sc.nextInt());
                    break;
                case "I":
                    insert(head, sc.nextInt(), sc.nextInt());
                    break;
                case "C":
                    change(head, sc.nextInt(), sc.nextInt());
                    break;
            }
            Node t = head;
            while(t.next != null){
                System.out.println(t.val);
                t = t.next;
            }
        }
        sc.close();
    }
    public static void insert(Node head, int idx, int val){
        
    }   
    public static void delete(Node head, int idx){
        Node current = head.next;
        int n = 0;
        while(current != null && n != idx){
            n++;
            current = current.next;
        }
        if(current == null) return;
        
    }
    public static void change(Node head, int idx, int val){
        Node current = head.next;
        int  n = 0;
        while(current != null && n != idx){
            current = current.next;
            n++;
        }
        if(current == null) return;
        current.val = val;
    }


}
class Node{
    int val;
    Node next;
    public Node(){}
    public Node(int val, Node next){
        this.val = val;
        this.next = next;
    }
}
