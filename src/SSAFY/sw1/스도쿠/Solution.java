package SSAFY.sw1.스도쿠;

import java.io.*;
import java.util.stream.Stream;

public class Solution {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for(int ts = 1; ts<=T; ts++){   
            int answer = 1;
            int [][] arr = new int[9][9];
            for(int i=0; i<arr.length; i++){
                arr[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            
            
            //가로 체크
            if(answer != 0){
                for(int i=0; i<arr.length; i++){
                    int sum = 0;
                    for(int j=0; j<arr[i].length; j++) sum += arr[i][j];
                    if(sum != 45){
                        answer = 0;
                        break;
                    }
                }
            }
            
            //세로 체크
            if(answer != 0){
                for(int i=0; i<arr.length; i++){
                    int sum = 0;
                    for(int j=0; j<arr[i].length; j++) sum += arr[j][i];
                    if(sum != 45){
                        answer = 0;
                        break;
                    }
                }
            }
            //박스 체크
            if(answer != 0){
                for(int i=0; i<3; i++){
                    for(int j=0; j<3; j++){
                        int sum =0;
                        for(int p=0; p<3; p++){
                            for(int q=0; q<3; q++){
                                sum += arr[i*3+p][j*3+q];
                                
                            }
                        }
                        if(sum != 45){
                            answer = 0;
                            break;
                        }
                    }
                    if(answer == 0) break;
                }
            }

            bw.write("#"+ts+" "+answer+"\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
