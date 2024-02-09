package SSAFY.순조부.comb;

// 조합
public class CombBasic {
    static int N = 5, K = 3;
    static int[] src = new int[]{1,2,3,4,5};
    static int[] tgt = new int[K];
    static int COUNT = 0;
    public static void main(String[] args) {
        comb(0,0);
        System.out.println("CNT: " + COUNT);
    }
    public static void comb(int srcIdx, int tgtIdx){
        if(tgtIdx == K){
            COUNT++;
            for(int i=0; i<K; i++) System.out.print(tgt[i]+" ");
            System.out.println();
            return;
        }
        for(int i=srcIdx; i<N; i++){
            tgt[tgtIdx] = src[i];
            comb(i+1, tgtIdx+1);
        }
    }
}
