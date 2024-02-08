package DP.T12865;

/*
 K-napsack

 */
import java.io.*;
import java.util.LinkedList;
import java.util.stream.Stream;
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = inputs[0]; // # of items
        int K = inputs[1]; // weight
        Item[] items = new Item[N];
        for(int i=0; i<N; i++){
            inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int w = inputs[0];
            int v = inputs[1];
            items[i] = new Item(w,v);
        }
        int[][] memo = new int[N+1][K+1];
        for(int i=1; i<=N; i++){
            Item item = items[i-1];
            for(int j=1; j<=K; j++){
                if(item.w <= j){
                    memo[i][j] = Math.max(memo[i-1][j-item.w]+item.v, memo[i-1][j]);
                }else{
                    memo[i][j] = memo[i-1][j];
                }
            }
        }
        System.out.println(memo[N][K]);

        
        int w = K;
        LinkedList<Integer> list = new LinkedList<>();
        for(int i=N; i>0 ;i--){
            if(memo[i][w] > memo[i-1][w]){
                //System.out.println(i);
                list.add(i);
                w -= items[i-1].w;
            }
        }
        for(Object x : list.toArray()) System.out.printf("%d ", (int)x);
        System.out.println("------");
        for (int i = 0; i < memo.length; i++) {
            for(int x : memo[i]) System.out.print(x+" ");
            System.out.println();
        }
        br.close();
    }  
}
class Item implements Comparable<Item>{
    int w;
    int v;
    public Item(int w, int v){
        this.w = w;
        this.v = v;
    }
    @Override
    public int compareTo(Item o) {
        return this.w - o.w;
    }
    
}
