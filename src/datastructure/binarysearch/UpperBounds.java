package datastructure.binarysearch;

public class UpperBounds {
    // 찾고자 하는 x를 초과하는 값 중 가장 작은 값의 위치
    //    public static int[] src = new int[]{-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
    public static int[] src = new int[]{5, 5, 7, 7, 7, 8, 8, 9, 9, 10, 15};

    public static int upperBounds(int x) {
        int left = 0;
        int right = src.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (src[mid] > x) {
                right = mid;
            } else { // src[mid] <= x
                left = mid + 1;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        for (int i = 0; i < src.length; i++) {
            System.out.println(src[i]+" "+upperBounds(src[i]));
        }
    }
}
