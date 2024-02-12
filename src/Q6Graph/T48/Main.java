package Q6Graph.T48;
// 물의 양 구하기 2251 백준 그래프 문제 아이디어 떠올리기 쉽지 않네 코드 짜기 쉽지 않네....~!!!!!!!!!
/*
 * 각각의 부피가 A,B,C(1<= A,B,C<=200) 리터인 3개의 물통이 있다. 처음에는 앞의 두 물통은 비어 있고, 3번째 물통은 
 * 가득(C리터)차 있다. 이제 어떤 물통에 들어 있는 물을 다른 물통으로 쏟아부을 수 있는데, 이때는 한 물통이 비거나,
 * 다른 한 물통이 가득찰 때까지 물을 부을 수 있다. 이 과정에서 손실되는 물은 없다고 가정한다. 이와 같은 과정을 거치다
 * 보면 3번째 물통(용량이 C인)에 담겨 있는 물의 양이 변할 수도 있다. 1번째 물통(용량이 A인)이 비어 있을 때 3번째(용량이 C인)에
 * 담겨 있을 수 있는 물의 양을 모두 구하는 프로그램을 작성하시오.
 */
import java.util.*;
public class Main {
    static int[] sender = {0,0,1,1,2,2}; // 보내는 물통
    static int[] receiver = {1,2,0,2,0,1}; // 받는 물통
    static boolean[][] visited;
    static boolean[] answer;
    static int[] volume; // 물통의 최대량
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        volume = new int[3]; // A:0 B:1 C:2
        volume[0] = sc.nextInt(); // A가 담을 수 있는 최대 부피
        volume[1] = sc.nextInt(); // B가 담을 수 있는 최대 부피
        volume[2] = sc.nextInt(); // C가 담을 수 있는 최대 부피
        sc.close();

        visited = new boolean[201][201]; // A와 B 물통이 담은 물의 양, C 물통의 양은 자연히 구할 수 있다.
        answer = new boolean[201];
        bfs();

        for(int i=1; i<=200; i++) if(answer[i]) System.out.printf("%d ", i);
    }
    
    public static void bfs(){
        visited[0][0] = true;
        answer[volume[2]] = true;
        Queue<AB> queue = new LinkedList<>();
        queue.add(new AB(0,0)); //A,B 양을 안다면 C도 자동으로 알 수 있다.
        
        while(!queue.isEmpty()){
            AB current = queue.poll();
            int A = current.A;
            int B = current.B;
            int C = volume[2] - A - B;
            
            for(int i=0; i<6; i++){ //A->B, A->C, B->A, B->C, C->A, C->B 6가지 경우가 있다.
                int[] next = {A,B,C};    
                next[receiver[i]] += next[sender[i]]; //일단 경우에 따라 물을 붓는다고 가정.
                next[sender[i]] = 0;

                //초과된 경우. 즉, 물이 넘친 경우
                if(next[receiver[i]] > volume[receiver[i]]){
                    next[sender[i]] = next[receiver[i]] - volume[receiver[i]];
                    next[receiver[i]] = volume[receiver[i]];
                }
                //방문 여부 확인
                if(!visited[next[0]][next[1]]){
                    visited[next[0]][next[1]] = true;
                    queue.add(new AB(next[0], next[1]));
                    if(next[0] == 0) answer[next[2]] = true;
                }
            }
        }

    }
    
}

class AB{
    int A;
    int B;
    public AB(int A, int B){
        this.A = A;
        this.B = B;
    }
}
