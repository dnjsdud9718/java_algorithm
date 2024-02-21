package SSAFY.순조부.comb;

// 조합
public class CombBasic {
    static int N = 6, K = 2;
    static int[] src = new int[]{-99, -2, -1, 1, 4, 98};
    static int[] tgt = new int[K];
    static int COUNT = 0;
    public static void main(String[] args) {
        comb(0,0);
        System.out.println("CNT: " + COUNT);
    }
    public static void comb(int srcIdx, int tgtIdx){
        if(tgtIdx == K){
            COUNT++;
            int sum = 0;
            for(int i=0; i<K; i++) {
                sum += tgt[i];
                System.out.print(tgt[i] + " ");
            }
            System.out.println(sum);
            return;
        }
        for(int i=srcIdx; i<N; i++){
            tgt[tgtIdx] = src[i];
            comb(i+1, tgtIdx+1);
        }
    }
}
