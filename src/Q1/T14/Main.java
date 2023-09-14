package Q1.T14;

/*
 * 11286  siver 1
 * 절댓값 힙 구현하기.
 */
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            int first_abs = Math.abs(o1);
            int seconde_abs = Math.abs(o2);

            if(first_abs > seconde_abs) {
                return 1;
            }else if(first_abs < seconde_abs){
                return -1;
            }else{
                return o1 > o2 ? 1 : -1;
            }

        });
        
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            if(x == 0) {
                if(pq.isEmpty()) wr.write("0\n");
                else wr.write(pq.poll()+"\n");
            }
            else {
                pq.add(x);
            }
        }

        pq.clear();
        wr.flush();
        wr.close();
        br.close();
    }
}
