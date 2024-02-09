package SSAFY.순조부.subset;

public class SubsetWithMasking {
    static int N;
    static int[] src;
    static int COUNT = 0;
    public static void main(String[] args) {
        N=3;
        src = new int[N];
        for(int i=0; i<N; i++) src[i] = i+1;
        subset(0,0);
        System.out.println("CNT : " + COUNT);
    }
    public static void subset(int idx, int mask){
        if(idx == N){
            COUNT++;
            for(int i=0; i<N; i++) if((mask & (1<<i))!= 0) System.out.print(src[i]+" ");
            System.out.println();
            return;
        }
        subset(idx+1, mask | (1<<idx));
        subset(idx+1, mask);

    }
}
