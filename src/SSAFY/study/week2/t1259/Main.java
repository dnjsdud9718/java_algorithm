package SSAFY.study.week2.t1259;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            int check = 1; // 1: 팰린드로, -1 : 아니다.  0 : 종료
            String line = br.readLine();
            if(line.charAt(0)-'0' == '0'-'0') {
                check = 0;
                break;
            }
            char[] chArr = line.toCharArray();
            
            int len = chArr.length;
            for(int i=0; i<len/2; i++){
                if(chArr[i] != chArr[len-1-i]) {
                    check = -1;
                    break;
                }
            }
            if(check == 1) System.out.println("yes");
            else if(check == -1)System.out.println("no");
        }
        br.close();
    }
}
