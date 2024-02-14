package SSAFY.study.t1932;

public class Test {
    public static void main(String[] args) {
        int v = 0;
        for (int i = 1; i <= 500; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(v++);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
