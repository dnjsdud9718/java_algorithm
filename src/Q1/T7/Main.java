package Q1.T7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int M = Integer.valueOf(st.nextToken());
        int[] iArr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) iArr[i] = Integer.valueOf(st.nextToken());
        //sort
        Arrays.sort(iArr);

        //count
        int answer = 0;
        int left = 0, right = N-1;
        int sum = iArr[left] + iArr[right];
        while(left < right && iArr[left] < M) { //iArr[right] <= M 은 조건식에 포함하면 안 된다. right가 감소하며 원소값이 작아져야 하기 때문에...
            if(sum == M) {
                answer++;
                left++; right--;
            }else if(sum < M) left++;
            else right--;
            sum = iArr[left] + iArr[right];
        }
        System.out.println(answer);
        
    }    
}
