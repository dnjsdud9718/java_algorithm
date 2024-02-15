package datastructure.binarysearch;

public class SimpleBinarySearch {
    public static int[] src = new int[]{-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};

    public static int binarySearch(int x) {
        int left =0;
        int right = src.length-1;
        while (left <= right) {
            int mid = (left+right)/2;
            if (src[mid] == x) {
                return mid;
            } else if (src[mid] < x) {
                left = mid + 1;
            } else { // src[mid] > x
                right = mid -1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        for (int i : src) {
            System.out.println(i + " " + binarySearch(i));
        }
        System.out.println(7 + " " + binarySearch(7));
    }
}
