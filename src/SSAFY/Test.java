package SSAFY;


public class Test {
    public static void main(String[] args) {
        int N = 5;
        System.out.println("1");
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(i>=j) System.out.printf("%c ", '*');
            }
            System.out.println();
        }
        System.out.println("2");
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(i<=j) System.out.printf("%c ", '*');
                else System.out.printf("%c ", ' ');
            }
            System.out.println();
        }
        System.out.println("3");
        for(int i=N-1; i>=0; i--){
            for(int j=0; j<N; j++){
                if(j<=i) System.out.printf("%c ", '*');
                else System.out.printf("%c ", ' ');
            }
            System.out.println();
        }
        System.out.println("4");
        for(int i=N-1; i>=0; i--){
            for(int j=0; j<N; j++) {
                if(j<i) System.out.printf("%c ", ' ');
                else System.out.printf("%c ", '*');
            }
            System.out.println();
        }
        System.out.println("5");
        for(int i=N-1; i>=0; i--){
            for(int j=0; j<N; j++){
                if(j<i) System.out.printf("%c ", ' ');
            }
            for(int j=0; j<2*(N-i)-1; j++) System.out.printf("%c ", '*');
            System.out.println();
        }
        System.out.println("6");
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++) if(i > j) System.out.printf("%c ", ' ');
            for(int j=0; j < 2*(N-i)-1; j++) System.out.printf("%c ", '*');
            System.out.println();
        }
        System.out.println("7");
        int K = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j < N-K; j++){
                if(j < K)System.out.printf("%c ", ' ');
                else System.out.printf("%c ", '*');
            }
            K = i < N/2 ? K+1 : K-1;
            System.out.println();
        }

        System.out.println("8");
        K = N/2;
        for(int i=0; i<N; i++){
            for(int j=0; j < N-K; j++){
                if(j < K) System.out.printf("%c ", ' ');
                else System.out.printf("%c ", '*');
            }
            K = i < N/2 ? K-1 : K+1;
            System.out.println();
        }
        System.out.println("9");
        K=0; 
        for(int i=0; i<N; i++){
            for(int j=0; j<N-K; j++){
                if(j <= K) System.out.printf("%c ", '*');
                else System.out.printf("%c ", ' ');
            }
            for(int j=0; j<=K; j++) System.out.printf("%c ", '*');
            K = i < N/2 ? K+1 : K-1; 
            System.out.println();
        }
        System.out.println("10");
        K=N/2;
        for(int i=0; i<N; i++){
            for(int j=0; j<N-K; j++){
                if(j <= K) System.out.printf("%c ", '*');
                else System.out.printf("%c ", ' ');
            }
            for(int j=0; j<=K; j++) System.out.printf("%c ", '*');
            K = i < N/2 ? K-1 : K+1;
            System.out.println();
        }
    }
}
