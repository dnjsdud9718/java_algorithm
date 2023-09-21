package Q2.T18;

// Selection Sort, 선택 정렬
// 최솟값 또는 최댓값을 찾고, 남은 정렬 부분 가장 앞에 있는 데이터와 swap하는 것이 주된 개념

// Selection Sort, 선택 정렬
// 최솟값 또는 최댓값을 찾고, 남은 정렬 부분 가장 앞에 있는 데이터와 swap하는 것이 주된 개념
import java.util.Scanner;
public class Selection {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char[] arr = s.toCharArray();
        for(int i=0; i<arr.length; i++) {
            int max = arr[i] - '0';
            int idx = i;
            for(int j = i+1; j<arr.length; j++) {
                if(max < arr[j] - '0') {
                    idx = j;
                    max = arr[j] - '0';
                }
            }
            char tmp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = tmp;
        }
        for(char x: arr) System.out.printf("%d", x-'0');
        
        sc.close();
    }
}




/*public class Selection {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for(int i=0; i<arr.length; i++) arr[i] = (int)(Math.random() * 100);
        for(int x: arr) System.out.printf("%d ", x);
        System.out.println("");
        for(int i=0; i<arr.length; i++) {
            int max = arr[i];
            int idx = i;
            for(int j = i+1; j<arr.length; j++) {
                if(max < arr[j]) {
                    idx = j;
                    max = arr[j];
                }
            }
            int tmp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = tmp;
        }
        for(int x: arr) System.out.printf("%d ", x);
    }
}
*/