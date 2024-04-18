package SSAFY.study.week13.t1676;

import java.util.*;
public class Main
{
    // 소인수 분해
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int count = 0;
        for(int i=2;i<=N; i++){
            int d = 2;
            int v = i;
            while(v != 1){
                if(v%d != 0) d++;
                else {
                    v /= d;
                    if(d == 5) count++;
                }
            }
        }
        System.out.println(count);
        sc.close();
    }

}
