package SSAFY.algo.baekjoon.t2733;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/*
    backjoon : 2733
    BrainFuck
    implementation

 */

public class Main {
    static final int MAX_MEM_SIZE = (1<<15);
    static final int MAX_CODE = 128_000;
    static final String ERR = "COMPILE ERROR";
    static int[] memory;
    static char[] code;
    static int pointer;
    static int codeIdx, cLen;
    static int[] bracket;

    static StringBuilder sb = new StringBuilder();
    static Deque<Integer> stack = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            memory = new int[MAX_MEM_SIZE];
            code = new char[MAX_CODE];
            bracket = new int[MAX_CODE];
            pointer = 0;
            codeIdx = 0;
            cLen = 0;
            stack.clear();
            do {
                String s = br.readLine();
                if(s.equals("end")) {
                    break;
                }
                boolean exit = false;
                for (int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    switch (c) {
                        case '-':
                        case '+':
                        case '<':
                        case '>':
                        case '.':
                        case '[':
                        case ']':
                            code[cLen++] = c;
                            break;
                        case '%':
                            exit = true;
                            break;
                    }
                    if(exit) break;
                }
            } while (true);

            // bracket 처리
            boolean check = false;
            for (int i = 0; i < cLen; i++) {
                if (code[i] == '[') {
                    stack.push(i);
                } else if (code[i] == ']') {
                    if (stack.isEmpty()) {
                        check=true;
                        break;
                    }
                    int j = stack.pop();
                    bracket[i] = j;
                    bracket[j] = i;
                }
            }


            sb.append("PROGRAM #").append(t).append(":\n");
            if (check || !stack.isEmpty()) {
                sb.append(ERR);
            } else {
                brainfuck();
            }
            sb.append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    static void brainfuck() {
        int cnt=0;
        while (codeIdx < cLen) {
            cnt++;
            char c = code[codeIdx];
            switch (c) {
                case '-':
                    memory[pointer]--;
                    if(memory[pointer] < 0) memory[pointer] = 255;
                    break;
                case '+':
                    memory[pointer] = (memory[pointer] + 1) % 256;
                    break;
                case '>':
                    pointer = (pointer + 1) % MAX_MEM_SIZE;
                    break;
                case '<':
                    pointer--;
                    if (pointer < 0) {
                        pointer = MAX_MEM_SIZE - 1;
                    }
                    break;
                case '[':
                    if (memory[pointer] == 0) {
                        codeIdx = bracket[codeIdx];
                    }
                    break;
                case ']':
                    if (memory[pointer] != 0) {
                        codeIdx = bracket[codeIdx];
                    }
                    break;
                case '.':
                    sb.append((char)memory[pointer]);
                    break;
            }
            codeIdx++;
        }
    }
}
