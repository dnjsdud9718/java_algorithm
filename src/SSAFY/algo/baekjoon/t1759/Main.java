package SSAFY.algo.baekjoon.t1759;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int L, C;
    static int[] src;
    static int[] tgt;
    static int check = 0;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        src = new int[C];
        tgt = new int[L];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            src[i] = st.nextToken().charAt(0);
//            System.out.println(src[i]);
        }
        check = (1 << (0));
        check |= (1 << ('e' - 'a'));
        check |= (1 << ('i' - 'a'));
        check |= (1 << ('o' - 'a'));
        check |= (1 << ('u' - 'a'));
        Arrays.sort(src);
        comb(0, 0);
        System.out.println(sb);
        br.close();
    }
    public static void comb(int srcIdx, int tgtIdx) {
        if (tgtIdx == L) {
            // a,e,i,o,u 중 하나 있는지 체크
//            System.out.println(Arrays.toString(tgt));
            boolean b = false;
            int cnt = 0;
            for(int i=0; i<L; i++) {
                if ((check & (1 << (tgt[i] - 97))) != 0) {
                    b = true;
                }else cnt++;
            }
            if (b && cnt >= 2) {
                for (int i = 0; i < L; i++) {
                    sb.append((char)tgt[i]);
                }
                sb.append('\n');
            }

            return;
        }
        for (int i = srcIdx; i < C; i++) {
            tgt[tgtIdx] = src[i];
            comb(i + 1, tgtIdx + 1);
        }
    }
}
