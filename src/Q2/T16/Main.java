package Q2.T16;
/*
 * 1377
 * 버블 소트 프로그램 1
 * N이 500,000이기 때문에 bubble sort로 해결할 수 없다.
 * 내부 for문에서 swap이 발생하지 않았다는 것은 이미 정렬된 상태를 의미.
 * ★ 내부 for문에서 한번 돌 때, 어떤 값이 좌 -> 우로는 여러번 갈 수 있지만...
 * 우 -> 좌로는 딱 한번만 이동한다. ★
 * 따라서 정렬 전 인덱스 - 정렬 후 인덱스가 가장 큰 것을 찾으면 된다.
 * 자기가 있어야 할 자리에서 가장 멀리 위치해 있던 놈...
 * 정렬 전 - 정렬 후 값이 가장 큰 놈만 생각하면 된다.
 * 왜냐하면 정렬 전 - 정렬 후 값이 가장 큰 놈은 모든 순회에서 우->좌로 이동하기 때문이다.
 * 어떤 값이 우->좌로 이동하지 않았다면, 그 값은 현재 위치보다 좌로 이동하지 않는다. 
 * 우로 이동은 가능.
 * 3 2 1 6 5 4 (0)
 * 2 1 3 5 4 6 (1)
 * 1 2 3 4 5 6 (2)
 * NO-SWAP(3)
 * 
 * 3 2 1 7 6 5 4 (0)
 * 2 1 3 6 5 4 7 (1)
 * 1 2 3 5 4 6 7 (2)
 * 1 2 3 4 5 6 7 (3)
 * NO-SWAP(4)
 */
import java.io.*;
import java.util.*;
class Data implements Comparable<Data>{
    public int idx;
    public int val;
    Data(int idx, int val) {
        super();
        this.idx = idx;
        this.val = val;
    }
    @Override
    public int compareTo(Data o){
        return this.val - o.val;
    }
}
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Data[] arrData = new Data[N];
        for(int i=0; i<N; i++) arrData[i] = new Data(i, Integer.parseInt(br.readLine()));

        Arrays.sort(arrData);

        int Max = Integer.MIN_VALUE;
        for(int i=0; i<N; i++) {
            if(Max < arrData[i].idx - i) Max = arrData[i].idx - i;
        }
        System.out.println(Max + 1);


        br.close();
    }
}
