package SSAFY.study.week2.t1074;


import java.util.Scanner;

// 1074 Z
//한수는 크기가 2^N × 2^N인 2차원 배열을 Z모양으로 탐색하려고 한다. 예를 들어, 2×2배열을 왼쪽 위칸, 오른쪽 위칸, 왼쪽 아래칸, 오른쪽 아래칸 순서대로 방문하면 Z모양이다.
//첫째 줄에 정수 N, r, c가 주어진다
//r행 c열을 몇 번째로 방문했는지 출력한다.
//        1 ≤ N ≤ 15
//        0 ≤ r, c < 2^N
public class Main {
    static int N, r, c, cnt, ans;
//    static int [][] src;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        r = sc.nextInt();
        c = sc.nextInt();
//        src = new int[1<<N][1<<N];
        cnt = 0;
        ans = 0;
        if(r != 0 || c != 0){
            z(0, 0, (1<<N)-1, (1<<N)-1, (1<<N));
//            System.out.println(src[r][c]);
            System.out.println(ans);
        }
        else System.out.println(0);
        sc.close();
    }
    public static void z(int sy, int sx, int ey, int ex, int n){
        if(ans != 0) return;
        if(n == 2){
            for(int i=sy; i <= ey; i++){
                for(int j=sx; j<=ex; j++){
//                    src[i][j] = cnt++;
                    cnt++;
                    if(i==r && j==c){
//                        ans = src[i][j];
                        ans = cnt-1;
                        return;
                    }
                }
            }
            return;
        }
        int  m = n/2;
        int tP = m*m;
        if(sy <= r && r <= ey-m && sx <= c && c <= ex-m) z(sy, sx, ey-m, ex-m, m);
        else if(sy <= r && r <=ey-m && sx+m <= c && c <= ex) {
            cnt += tP;
            z(sy, sx + m, ey - m, ex, m);
        }else if(sy+m <=r && r<= ey && sx <= c && c <= ex-m ) {
            cnt += 2*tP;
            z(sy + m, sx, ey, ex - m, m);
        }else {
            cnt += 3*tP;
            z(sy + m, sx + m, ey, ex, m);
        }
    }
}
