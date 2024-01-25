package SSAFY.study.t11054;

/*
 * 11054 가장 긴 바이토닉 부분 수열 구하기
 * 수열 S가 어떤 수 Sk를 기준으로 S1 < S2 < ... Sk-1 < Sk > Sk+1 > ... SN-1 > SN을 만족한다면,
 * 그 수열을 바이토닉 수열이라고 한다.
 * 
 * 내 접근법
 * 길이가 가장 긴 부분 수열 구하는 것을 두 번 수행
 * 좌 -> 우
 * 좌 <- 우
 * 이후 순회하면서 수열의 i번째 요소가 비아토닉 부분 수열의 가장 큰 값일 때 길이를 비교
 */
import java.io.*;
import java.util.stream.Stream;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int DL[] = new int[N];
        int DR[] = new int[N];
        for(int i=0; i<N; i++) DL[i] = DR[i] = 1;
        for(int i=1 ;i<N; i++){
            for(int j=0; j<i; j++){
                if(A[i] > A[j]) DL[i] = Math.max(DL[i], DL[j]+1);
            }
        }

        for(int i=N-2; i>=0; i--){
            for(int j=N-1; j>i; j--){
                if(A[i] > A[j]) DR[i] = Math.max(DR[i], DR[j]+1);
            }
        }
        int max=0;
        for(int i=0; i<N; i++) if(DL[i]+DR[i] > max) max = DL[i] + DR[i];
        System.out.println(max-1);

        br.close();
    }
}
