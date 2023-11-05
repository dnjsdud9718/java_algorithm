package Q4.T35;

/*
 * 1931 회의실 배정하기 silver 2
 */
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.PriorityQueue;
class Meeting {
    private int start;
    private int end;
    public Meeting(String s, String e){
        this.start = Integer.parseInt(s);
        this.end = Integer.parseInt(e);
    }
    public int getStart() {return this.start;}
    public int getEnd() {return this.end;}
}

public class Main {
    public static void main(String[] args){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine());
            PriorityQueue<Meeting> pq = new PriorityQueue<>((o1, o2)->{
               if(o1.getEnd() != o2.getEnd()) return o1.getEnd() - o2.getEnd();
               else{
                return o1.getStart() - o2.getStart();
               }
            });

            for(int i=0; i<N; i++){
                String[] t = br.readLine().split(" ");
                pq.add(new Meeting(t[0], t[1]));
            }
            br.close();

            int answer = 0;
            int prevEnd = 0;
            while(!pq.isEmpty()){
                Meeting m = pq.poll();
                if(prevEnd <= m.getStart()) {
                    prevEnd = m.getEnd();
                    answer++;
                }
            }
            System.out.println(answer);
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
