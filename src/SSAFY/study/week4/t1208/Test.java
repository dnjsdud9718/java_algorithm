package SSAFY.study.week4.t1208;

public class Test {
    public static void main(String[] args) {
        int[] arr = new int[]{-3, -2, -2, -2, -1, -1, -1, 0};
        int i1 = Main.lowerBound(arr, 0, arr.length, -1);
        int i2 = Main.upperBound(arr, 0, arr.length, -1);
        System.out.println("i1 = " + i1);
        System.out.println("i2 = " + i2);
    }
}
