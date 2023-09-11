package Q1.T10_1;
/**
 * 11003 응용 문제
 * 최소값 찾기
 * N개의 수 A1, A2, ... , An과 L이 주어진다.
 * Di = Ai ~ Ai+L-1 중의 최솟값이라고 할 때, D에 저장된 수를 출력하는 프로그램을 작성하시오.
 * 이때, i>=N인 Ai는 무시하고 D를 구해야 한다.
 */

import java.io.*;
import java.util.*;

class Item{
   public int index;
   public int value;
   Item(int index, int value){
      this.index = index;
      this.value = value;
   }
}

public class Main {
   public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.valueOf(st.nextToken());
      int L = Integer.valueOf(st.nextToken());
      int[] iArr = new int[N];

      st = new StringTokenizer(br.readLine()); 
      for(int i=0; i<N; i++) {
         iArr[i] = Integer.valueOf(st.nextToken());
      }

      Deque<Item> dq = new ArrayDeque<Item>();
      for(int i=N-1; i>=0; i--){
         int val = iArr[i];
         while(!dq.isEmpty() && dq.peek().value >= val){
            dq.pollFirst();
         }
         dq.addFirst(new Item(i, val));
         if(dq.peekLast().index - i >= L){
            dq.pollLast();
         }
         iArr[i] = dq.peekLast().value;
         System.out.println(iArr[i]);
      }
      for(int i=0; i<N; i++) wr.write(iArr[i]+" ");


      wr.flush();
      wr.close();
      br.close();
    
   } 
}
