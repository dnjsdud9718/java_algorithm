package Q6.T11404;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class FloydWarshall{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());
        int[][] arr = new int[N][];
        for(int i=0;i<N; i++) {
            arr[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j=0; j< arr[i].length; j++) {
                if(i==j) arr[i][j] = 0;
                else if(arr[i][j] == 0) arr[i][j] = 100000;
            }
        }

        for(int k=0; k<N; k++){
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    arr[i][j] = Math.min(arr[i][j], arr[i][k]+arr[k][j]);
                    System.out.printf("%d ", arr[i][j] != 100000 ? arr[i][j] : -1);
                }
                System.out.println();
            }
        }
        System.out.println("============================");
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(arr[i][j] == 100000) System.out.printf("%c ", '☆');
                else System.out.printf("%d ", arr[i][j]);
            }
            System.out.println();
        }
        
    }
}