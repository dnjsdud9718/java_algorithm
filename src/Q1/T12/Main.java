package Q1.T12;
/*
 * 17298 오큰수
 * 
 * 수열의 각 원소 Ai에 대해서 오큰수 NGE(i)를 구하시오
 * Ai의 오큰수는 오른쪽에 있으면서 Ai보다 큰 수 중에서 가장 왼쪽에 있는 수를 말한다.
 * 
 */
import java.io.*;
import java.util.*;
class Item {
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
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] iArr = new int[N];
        Stack<Item> stk = new Stack<>();
        int idx = 0;
        stk.add(new Item(idx++, Integer.parseInt(st.nextToken()))); //인덱스를 아는 것이 중요.

        while(st.hasMoreTokens()){
            int val = Integer.parseInt(st.nextToken()); 
            //val가 스택에 들어가기 전에 스택에 있는 값 중에 val보다 작은 값을 처리.
            // 로직 상 val가 들어가기 전에 val보다 작은 값이 스택 안에 있을 수 없다.☆ 스택은 top을 기준으로 아래로 오름차순되어 있다.
            while(!stk.isEmpty() && stk.peek().value < val){ 
                Item item = stk.pop();
                iArr[item.index] = val;
            }
            stk.add(new Item(idx++, val));
        }
        while(!stk.isEmpty()){
            Item item = stk.pop();
            iArr[item.index] = -1;
        }
        for(int i=0; i<N; i++) wr.write(iArr[i]+" ");

        br.close();
        wr.flush();
        wr.close();
    }    
}
