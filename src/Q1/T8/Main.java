package Q1.T8;
/**
 * 1253 gold 3
 * '좋은 수' 구하기
 * N개의 수 중에서 어떤 수가 다른 두 개의 합으로 나타낼 수 있다면 그 수는 '좋다'라고 한다.
 * 1<=N<=2,000
 * |Ai| <= 1,000,000,000, Ai는 정수
 * ★ 수의 위치가 다르면 값이 같아도 다른 수이다 ★
 */

 import java.io.IOException;
 import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.util.StringTokenizer;
 import java.util.Arrays;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] iArr = new int[N];
        for(int i=0; i<N; i++) iArr[i] = Integer.valueOf(st.nextToken());
        Arrays.sort(iArr);


        int answer = 0;
        for(int i=0; i<N; i++) {
            int k = iArr[i]; //k가 좋은 수가 될 수 있는지 확인.
            int p=0, q=N-1;
            while(p < q) {
                if(iArr[p] + iArr[q] == k){
                    if(p != i && q != i){
                        answer++;
                        break;
                    }
                    else if( p == i){
                        p++;
                    }
                    else{
                        q--;
                    }
                }else if(iArr[p] + iArr[q] < k) p++;
                else q--;
            }
        }
        System.out.println(answer);
    }    
}
