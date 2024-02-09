package SSAFY.순조부.comb;

import java.util.Arrays;

// 중복 조합
public class CombWithRep {
    static int N=5,K=3;
    static int[] src = new int[]{1,2,3,4,5};
    static int[] tgt = new int[K];
    static int COUNT = 0;
    public static void main(String[] args) {
        combWithRep(0, 0);
        System.out.println("CNT: " + COUNT);
    }
    public static void combWithRep(int srcIdx, int tgtIdx){
        if(tgtIdx == K){
            System.out.println(Arrays.toString(tgt));
            COUNT++;
            return;
        }
        for(int i=srcIdx; i<N; i++){
            tgt[tgtIdx] = src[i];
            combWithRep(i, tgtIdx+1);
        }
    }
}
