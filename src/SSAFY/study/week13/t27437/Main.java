package SSAFY.study.week13.t27437;

import java.util.*;
public class Main{
    static long X; // 1 <= X  <= 10^18
    static int idx, ret, move;
    static long sum, answer, child, parent;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        X = sc.nextLong();

        // 최적화 요소 2 -> 마땅한 방법이 떠오르지 않아 이전까지의 문제 풀이 과정에서
        // 휴리스틱하게 발견한 값을 넣어줬다.
        idx = 1_414_213_563;

        // 이분탐색
        int ret = lowerBound(1, idx, X);

        answer = sum(ret);
        // 최적화 요소 1
        // 최적화 핵심 (빼기)
        move = (int)(answer - X);

        // 최적화 완료
        if(ret % 2 != 0){ // if ret is odd then 1/ret, (+,-)
            child = 1 + move;
            parent = ret-move;
        }else{ // if ret is even then ret/1, (-,+)
            child = ret - move;
            parent = 1 + move;
        }

        System.out.println(child+"/"+parent);
        sc.close();
    }

    // 이분 탐색 -> lowerBound -> X와 같거나 큰 값 중에 가장 작은 값
    // 인덱스를 리턴
    public static int lowerBound(int lt, int rt, long x){
        while(lt <rt){
            int mid = lt/2 + rt/2;
            long s = sum(mid); // s = (mid*(mid+1))/2
            if(s >= x){
                rt = mid;
            }else{
                lt = mid+1;
            }
        }
        return rt;
    }

    // 등차가 1인 수열의 합 공식
    public static long sum(long k){
        return (k *(k+1))/2;
    }
}

