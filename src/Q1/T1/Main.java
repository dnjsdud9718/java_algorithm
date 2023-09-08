package Q1.T1;

import java.util.*;

public class Main {
    public static void main(String[] args){
        /* 백준: 11720 브론즈 2
         * 아래 풀이 문제
         * 1 <= N <= 100 이므로 M의 길이가 최대 100이다. 따라서 int나 long으로 값을 받을 수 없다.
         
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = Integer.parseInt(sc.next());
        int answer = 0;
        for(int i=0; i<N ; i++){
            answer += M%10;
            M /= 10;
        }
        System.out.println(answer);
        sc.close();
        */

        Scanner sc = new Scanner(System.in);
        sc.nextInt();
        String sNum = sc.next();
        char [] chArr = sNum.toCharArray();
        int answer = 0;
        for(char x : chArr){
            answer += x - '0';
        }
        System.out.println(answer);
        sc.close();
    }
}
