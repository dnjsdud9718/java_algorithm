package Q1.T10;

/**
 * 11003 플래티넘5
 * 최솟값 찾기
 * deque를 사용해야 한다.
 * 
 */

import java.io.*;
import java.util.*;

class Item { // 데큐에 담을 아이템
    private int index;
    private int value;
    Item (int index, int value){
        this.index = index;
        this.value = value;
    }

    public void setIndex(int index) {this.index = index;}
    public int getIndex() {return this.index;}
    public void setValue(int value) {this.value = value;}
    public int getValue() {return this.value;}
}

public class Main {
    private static BufferedWriter wr;
    private static Deque<Item> deque;
    private static int L;

    public static void modifyDeque(Item item) throws IOException{
        if(deque.isEmpty()){ // 데큐가 비어있으면 아이템을 넣어주고 해당 아이템의 값을 출력.
            deque.add(item);
            wr.write(deque.peekFirst().getValue() + " ");
        }else { //★ 파라미터로 받은 item의 인덱스가 기준이 된다는 생각을 가지자. 문제의 Di = A(i-L+1": index) ~ Ai의 i다.
            // 최신(현재) 들어오는 아이템의 값보다 작은 이전 아이템들은 필요없다. 지금 들어오는 놈이 기준.
            while(!deque.isEmpty() && deque.peekLast().getValue() >= item.getValue()){ 
                deque.pollLast();
            }
            deque.addLast(item); // 이때 데큐에는 추가되는 아이템의 value 보다 작은 놈들이 왼쪽에 정렬된 상태로 배치되어 있다.
            while(item.getIndex() - deque.peekFirst().getIndex() + 1 > L) deque.poll(); // i-L+1 문제의 핵심
            wr.write(deque.peek().getValue() + " ");
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());


        wr = new BufferedWriter(new OutputStreamWriter(System.out));
        deque = new ArrayDeque<Item>();

        st = new StringTokenizer(br.readLine());
        int [] iArr = new int[N];
        for(int i=0; i<N; i++) {
            iArr[i] = Integer.parseInt(st.nextToken());
            Item item = new Item(i, iArr[i]);
            modifyDeque(item);
        }
        wr.flush();
        wr.close();
        br.close();
        


        

    }    
}
