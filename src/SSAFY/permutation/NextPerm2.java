package SSAFY.permutation;

import java.util.Arrays;
import java.util.Scanner;

// Next Permutation => 순열의 모든 경우를 오름차순으로 찾는 방법
// 만들 수 있는 (경우) 가장 작은 수를 만들고 계속  NP를 적용하면서 가장 큰 수까지 이어 간다.
// 만들어 지는 모든 수가 바로 순열의 경우의 수와 같다.
// 주의 최초(NP를 적용하지 않은 가장작은 수) 도 경우의 수 중 하나.
//정렬 후 NP적용해 나간다.
// 비트 마스킹 적용해보기
public class NextPerm2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] src = new int[N];
        for (int i = 0; i < N; i++) src[i] = sc.nextInt();

        //step 0: sort(increasing)
        Arrays.sort(src);
        do {
            // 순열 이용한 처리 로직이 여기에...
            System.out.println(Arrays.toString(src));
        } while (nextPerm(src));

        sc.close();
    }



    public static boolean nextPerm(int[] p) {
        final int N = p.length;
        // step1 : 교환 위치 찾기(뒤쪽부터 꼭대기를 찾으면 꼭대기 이전이 교환 위치가 됨)
        int i = N-1;
        while(i>0 && p[i-1] >= p[i]) --i;
        // i==0 -> 마지막 순열
        if(i == 0) return false;
        // p[i-1] < p[i] -> i번째 꼭대기 찾았다.
        //step2 : 교환 위치(i-1)에 넣을 값을 뒤쪽부터 찾기( 큰 값 중 최솟값)
        // 교환값을 찾지 못하는 경우 없다 -> 꼭대기(i)가 반드시 존재!
        int j = N-1;
        while(p[i-1] >= p[j]) --j;
        // step3 : swap(i-1, j)
        swap(p, i - 1, j);
        // step4: 꼭대기(i)부터 맨 뒤까지 오름차순 정렬 -> TIP i부터 뒤로 내림차순 정렬되어 있다.
        // swap으로 정렬 가능
        int k = N-1;
        while(i < k) swap(p, i++, k--);

        return true;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
