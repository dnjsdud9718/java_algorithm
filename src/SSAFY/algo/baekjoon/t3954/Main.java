package SSAFY.algo.baekjoon.t3954;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static final int MAX_SM = 100_000;
    static final int MAX_SC = 4_096;
    static final int MAX_SI = 4_096;
    static final int MAX_LOOP = 100_000_000;
    static int Sm, Sc, Si; // Sm : 배열 크기, Sc : 코드 크기, Si : 입력 크기
    static int[] memory;
    static char[] code;
    static char[] in;
    static int[] bracketPt;
    static Deque<Integer> stack = new ArrayDeque<>();
    static int pointer;
    static int codeIdx;
    static int inIdx;
    static int cnt;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            Sm = Integer.parseInt(st.nextToken());
            Sc = Integer.parseInt(st.nextToken());
            Si = Integer.parseInt(st.nextToken());
            memory = new int[Sm]; // inital -> All 0
            code = new char[Sc];
            in = new char[Si];
            String s = br.readLine();
            for (int i = 0; i < Sc; i++) {
                code[i] = s.charAt(i);
            }
            s = br.readLine();
            for (int i = 0; i < Si; i++) {
                in[i] = s.charAt(i);
            }
            // 대괄호 매칭 -> 대괄호 쌍은 루프를 의미한다.
            bracketPt = new int[Sc];
            stack.clear();
            for (int i = 0; i < Sc; i++) {
                if (code[i] == '[') {
                    stack.push(i);
                } else if (code[i] == ']') {
                    int j = stack.pop();
                    bracketPt[j] = i;
                    bracketPt[i] = j;
                }
            }
            pointer = 0;
            codeIdx = 0;
            inIdx = 0;
            cnt = 0;
            while (cnt <= MAX_LOOP && codeIdx < Sc) {
                process();
                cnt++;
                codeIdx++;
            }
            if (codeIdx == Sc) {
                sb.append("Terminates").append("\n");
            } else {
                // 무한루프가 발생.
                // codeIdx = 무한 루프가 발생한 루프 범위 내에 위치하고 있을 것이 자명하다.
                // 무한루프를 계속 실행하면서 범위 내 최소 위치와 최대 위치를 찾는다.
                // 최소 위치가 여는 괄호의 위치가 될 것이고, 닫는 위치가 최대 위치가 될 것이다.


                int maxpid = codeIdx; // 닫는 괄호의 위치
                int minpid = codeIdx; // 여는 괄호의 위치

                while (cnt-- > 0) {
                    process();
                    codeIdx++;
                    maxpid = Math.max(maxpid, codeIdx);
                    minpid = Math.min(minpid, codeIdx);
                }
                // 왜 minPid -1 인가? ']'에 도착하여 '['로 점프하고 항상 위치가 1앞으로 증가한다.
                sb.append("Loops ").append(minpid - 1).append(" ").append(maxpid).append("\n");
            }
        }
        System.out.println(sb);
        br.close();
    }

    static void process() {
        char cmd = code[codeIdx];
        switch (cmd) {
            case '-':
                memory[pointer]--;
                if(memory[pointer] < 0) memory[pointer] = 255;
                break;
            case '+':
                memory[pointer] = (memory[pointer] + 1) % 256;
                break;
            case '<':
                pointer--;
                if(pointer < 0) pointer = Sm - 1;
                break;
            case '>':
                pointer = (pointer + 1) % Sm;
                break;
            case '[':
                if (memory[pointer] == 0) {
                    codeIdx = bracketPt[codeIdx];
                }
                break;
            case ']':
                if (memory[pointer] != 0) {
                    codeIdx = bracketPt[codeIdx];
                }
                break;
            case ',':
                if(inIdx >= Si) memory[pointer] = 255;
                else memory[pointer] = (int)in[inIdx++];
            default:
                break;
        }
    }
}
