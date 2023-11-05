package Q4.T34;
/*
 * 1744
 */
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Collections;
import java.util.ArrayList;
public class ArrayListEx {
    public static void main(String[] args) {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.valueOf(br.readLine());
            //PriorityQueue<Integer> positive = new PriorityQueue<>(Collections.reverseOrder());
            ArrayList<Integer> positive = new ArrayList<>();
            ArrayList<Integer> negative = new ArrayList<>();
            
            int zeros = 0;
            int ones = 0;
            for(int i=0; i<N; i++){
                int t = Integer.parseInt(br.readLine());
                if(t == 0) zeros++;
                else if(t == 1) ones++;
                else if(t < 0) negative.add(t);
                else positive.add(t);
            }
            
            Collections.sort(positive, Collections.reverseOrder());
            Collections.sort(negative);
            int answer = ones;
            for(Object x : positive.toArray()) System.out.printf("%d, ", (int)x);
            
            while(positive.size() > 1){
                int a = positive.remove(0);
                int b = positive.remove(0);
                answer += (a*b);
            }
            if(!positive.isEmpty()) answer += positive.remove(0); 

            while(negative.size() > 1){
                answer += (negative.remove(0) * negative.remove(0));
                
            }
            if(!negative.isEmpty() && zeros == 0 ) answer += negative.remove(0);
            System.out.println(answer);

            br.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
