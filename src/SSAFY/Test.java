package SSAFY;

public class Test {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        for (int i = 0; i <= 500; i++) {
            if(i%10==0) System.out.println();
            System.out.printf("%-3d ", i);

        }
    }
}