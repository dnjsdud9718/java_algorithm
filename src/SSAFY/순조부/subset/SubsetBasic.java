package SSAFY.순조부.subset;

public class SubsetBasic {
    static int N;
    static int[] src;
    static boolean[] visited;
    static int COUNT = 0;
    public static void main(String[] args) {
        N = 3;
        src = new int[N];
        visited = new boolean[N];
        for(int i=0; i<N ;i++) src[i] = i+1;
        subset(0);
        System.out.println(COUNT);
    }
    public static void subset(int idx){
        if(idx == N){
            COUNT++;
            System.out.print(COUNT+" : ");
            for(int i=0; i<N; i++) if(visited[i]) System.out.print(src[i]+" ");
            System.out.println();
            return;
        }

        visited[idx] = true;
        subset(idx+1);
        visited[idx] = false;
        subset(idx+1);

    }

    
}
