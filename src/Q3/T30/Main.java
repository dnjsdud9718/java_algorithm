package Q3.T30;

/*
 * 2343 silver2 블루레이 만들기
 * 
 * 이진 탐색
 */

import java.io.*;
public class Main {
    static int M;
    static int answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] row = br.readLine().split(" ");
        int N = Integer.parseInt(row[0]);
        M = Integer.parseInt(row[1]);

        int[] arr = new int[N];
        int [] sum = new int[N];
        int max = Integer.MIN_VALUE; //블루레이의 최소 크기는 레슨 중 크기가 가장 큰 레슨의 크기.
        row = br.readLine().split(" ");
        for(int i=0; i<arr.length; i++){
            arr[i] = Integer.parseInt(row[i]);
            if(arr[i] >= max) max = arr[i];
            if(i == 0) sum[i] = arr[i];
            else sum[i] += sum[i-1] + arr[i];
        }
        
        answer = 0;
        solution(sum, max, sum[N-1]);
        System.out.println(answer);
        br.close();
    }
    public static void solution(int[] arr, int s, int e){
        if(s <= e) { // 이진 탐색
            int bluelay = (s+e)/2; 
            int cnt = 0;
            int p=-1, q=0;
            while(q < arr.length){ //arr는 부분합된 배열.
                if(p == -1 && arr[q] <= bluelay) q++;
                else if(p != -1 && arr[q] - arr[p] <= bluelay) q++;
                else{
                    p = q-1;
                    cnt++;
                }
            }
            if(cnt + 1 <= M){ // +1의 의미 중요: 마지막 블루레이는 카운트가 오르지 않는다. 마지막 블루레이는 항상 <= bluelay 만족하기 때문에.
                answer = bluelay; //정답이 될 수 있는 값. 이 보다 작은 값이 정답이 될 수 있는지 확인해야 한다.
                solution(arr, s, bluelay-1);
            }else{
                solution(arr, bluelay+1, e); //위로 다시 탐색.
            }
        }
    }
}
