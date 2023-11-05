package Q4.T34;


import java.util.ArrayList;
public class ArrayListEx {
    public static void main(String[] args) {
        ArrayList<Integer> al = new ArrayList<>();
        for(int i=0; i<10; i++) al.add(i);        
        while(!al.isEmpty()) System.out.printf("%d-%d ", al.remove(0), al.remove(0));
    }
}
