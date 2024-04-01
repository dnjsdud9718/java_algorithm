package SSAFY;

public class Test {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        for (int i = 10; i <= 350; i++) {
            if(i%10==0) System.out.println();
            System.out.printf("%-3d ", i);

        }
        System.out.println(11 >> 1);
    }
}