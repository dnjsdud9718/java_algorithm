package datastructure.binarysearch;

public class LowerBounds {
//    public static int[] src = new int[]{-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
    public static int[] src = new int[]{5, 5, 7, 7, 7, 8, 8, 9, 9, 10, 15};
    // lower bounds -> 찾고자 하는 값 보다 크거나 같은 수 중 가장 좌측에 있는 값을 찾는다.
    public static int lowerBounds(int x) {
        int left = 0;
        int right = src.length;
        while (left < right) {
            int mid = (left+right)/2;
            if (src[mid] >= x) {
                right = mid;
            } else {
                left = mid+1;
            }
        }
        return right;
    }
    public static void main(String[] args) {
        for (int i = 0; i < src.length; i++) {
            System.out.println(src[i] + " " + lowerBounds(src[i]));
        }
        System.out.println("12 "+lowerBounds(12));
    }
}
