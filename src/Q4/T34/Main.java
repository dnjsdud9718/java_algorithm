package Q4.T34;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.PriorityQueue;
import java.util.Collections;
public class Main {
    public static void main(String[] args){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.valueOf(br.readLine());
            PriorityQueue<Integer> positive = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> negative = new PriorityQueue<>();
            int zeros = 0;
            int ones = 0;
            for(int i=0; i<N; i++){
                int t = Integer.parseInt(br.readLine());
                if(t == 0) zeros++;
                else if(t == 1) ones++;
                else if(t < 0) negative.add(t);
                else positive.add(t);
            }
            
            int answer = ones;
            
            
            while(positive.size() > 1){
                int a = positive.poll();
                int b = positive.poll();
                answer += (a*b);
            }
            if(!positive.isEmpty()) answer += positive.poll(); 

            while(negative.size() > 1){
                answer += (negative.poll() * negative.poll());
                
            }
            if(!negative.isEmpty() && zeros == 0 ) answer += negative.poll();
            System.out.println(answer);

            br.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
}
