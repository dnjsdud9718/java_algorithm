package Q5;

public class Eratosthenes {
    public static void eratosthenes(int s, int e){
        int [] arr = new int[e+1];
        for(int i=2; i<=e; i++) arr[i] = i;

        for(int i=2; i<=e; i++){
            if(arr[i] == i){
                for(int j=i+i; j<=e; j+=i) arr[j] = 0;
            }
        }
        for(int i=2; i<=e; i++) if(arr[i] == i) System.out.printf("%d, ", arr[i]);
    }
    public static void main(String[] args){
        eratosthenes(1,100);
    }   
}
