package SSAFY.순조부.subset;

// subset => binaryCounting
public class BinaryCounting {
    static int N, COUNT;
    static int[] src;
    public static void main(String[] args) {
        N = 4;
        src = new int[]{1,2,3,4};
        COUNT = (1<<N);
        for(int i=0; i<COUNT; i++){
            for(int j=0; j<N; j++){
                if((i & (1<<j)) == 0) continue;
                System.out.print(src[j]+" ");
            }
            System.out.println();
        }
        System.out.println("CNT : " + COUNT);
    }
}
