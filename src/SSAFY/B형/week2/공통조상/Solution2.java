package SSAFY.B형.week2.공통조상;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class Solution2 {
    static int V, E, N1, N2;
    static int[] parents;
    static List<Integer>[] childs;
    static int ansParent, ansSize;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            N1 = Integer.parseInt(st.nextToken());
            N2 = Integer.parseInt(st.nextToken());
            parents = new int[V + 1];
            childs = new List[V + 1];
            for (int i = 1; i <= V; i++) childs[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                parents[c] = p;
                childs[p].add(c);
            }
            // LCA 찾기
            List<Integer> ancestorN1 = new ArrayList<>();
            findAllAncestor(ancestorN1, parents[N1]);
            List<Integer> ancestorN2 = new ArrayList<>();
            findAllAncestor(ancestorN2, parents[N2]);
            int i1 = ancestorN1.size()-1;
            int i2 = ancestorN2.size() - 1;
            while (i1 >= 0 && i2 >= 0) {
                if (Objects.equals(ancestorN1.get(i1), ancestorN2.get(i2))) {
                    ansParent = ancestorN1.get(i1);
                    i1--; i2--;
                }else break;
            }
            // 찾은 LCA를 루트로하는 서브트리의 사이즈 찾기
            ansSize = dfs(ansParent);
            sb.append('#').append(t).append(' ').append(ansParent).append(' ').append(ansSize).append('\n');
        }
        System.out.println(sb.toString());
        br.close();
    }

    // subtree의 size를 구하는 메서드
    public static int dfs(int x) {
        int cur = 1;
        for(int i=0; i < childs[x].size(); i++){
            int child = childs[x].get(i);
            cur += dfs(child);
        }
        return cur;
    }
    // 재귀적으로 모든 조상 찾는 메서드
    public static void findAllAncestor(List<Integer> list, int x) {
        if(x == 0) return;
        list.add(x);
        findAllAncestor(list, parents[x]);
    }
}
