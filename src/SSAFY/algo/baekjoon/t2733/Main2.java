package SSAFY.algo.baekjoon.t2733;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main2 {
    public static int ptr;
    public static char arr[];
    public static String code;
    public static StringBuilder res = new StringBuilder();
    public static Map<Integer, Integer> indexMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            ptr = 0;
            indexMap.clear();
            arr = new char[32768];
            char[] tmp = Arrays.copyOf(arr, 10);
            for (int j = 0; j < 10; j++) {
                System.out.print((int)tmp[j]);
            }

            res.append(String.format("PROGRAM #%d:\n", i));

            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = br.readLine().replace(" ", "");
                if ("end".equals(line)) {
                    break;
                }

                if (line.contains("%")) {
                    line = line.substring(0, line.indexOf("%"));
                }
                sb.append(line);
            }
            code = sb.toString();
            System.out.println(code);

            Stack<Integer> stk = new Stack<>();

            for (int j = 0; j < code.length(); j++) {
                char ch = code.charAt(j);
                if (ch == '[') {
                    stk.push(j);
                } else if (ch == ']') {
                    int idx = stk.pop();
                    indexMap.put(j, idx);
                    indexMap.put(idx, j);
                }
            }

            if (stk.isEmpty()) {
                for (int j = 0; j < code.length(); j = parse(j)) ;
            } else {
                res.append("COMPILE ERROR");
            }
            res.append("\n");
        }

        System.out.println(res);
        br.close();
    }

    private static int parse(int idx) {
        switch (code.charAt(idx)) {
            case '>':
//                ptr = ptr == 32767 ? 0 : ptr + 1;
                ptr = (ptr + 1) % 32768;
                break;

            case '<':
//                ptr = ptr == 0 ? 32767 : ptr - 1;
                ptr--;
                if(ptr <0) ptr = 32767;

                break;
            case '+':
                arr[ptr] = arr[ptr] == 255 ? 0 : (char) (arr[ptr] + 1);
                break;
            case '-':
                arr[ptr] = arr[ptr] == 0 ? 255 : (char) (arr[ptr] - 1);
                break;
            case '.':
                System.out.println((int)arr[ptr]);
                res.append(arr[ptr]);
                break;
            case '[':
                return arr[ptr] == 0 ? indexMap.get(idx) + 1 : idx + 1;
            case ']':
                return arr[ptr] != 0 ? indexMap.get(idx) : idx + 1;
        }
        return idx + 1;
    }
}
