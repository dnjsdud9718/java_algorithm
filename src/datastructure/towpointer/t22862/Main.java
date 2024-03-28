package datastructure.towpointer.t22862;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K, answer;
    static int[] src;
    static int current, prev;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        src = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            src[i] = Integer.parseInt(st.nextToken());
        }
        current = prev = answer = 0;
        // 시작이 연속 홀수로 시작하는 경우 버리기
        while(current < N && src[current] % 2 != 0) current++;
        int k = 0;
        prev = current;
        while (current < N) {
            // 삭제 기능을 다 사용하자
            while (current < N) {
                if (src[current] % 2 != 0) {
                    if (k < K) {
                        k++;
                    }else{
                        break;
                    }
                }
                current++;
            }
//            System.out.println(current + " " + prev + " " + k);
            answer = Math.max(answer, current - prev - k);
            if (current > N) {
                break;
            }
            prev++;
            while(prev < N && src[prev]%2 != 0) {
                k--;
                prev++;
            }
        }
        System.out.println(answer);
        br.close();

    }
}
