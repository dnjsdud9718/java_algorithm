package SSAFY.B형.week2.공통조상;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    부모와 자식을 따로 관리
        -> 자식을 관리하는 ArrayList childs
        -> 부모를 관리하는 Array parents
    Subtree의 size를 저장하는 Array -> 자기 자신은 1, dfs -> depth도 같이 저장 가능.
       -> 자식의 depth는 자기자신 + 1
       -> 자기 자신의 사이즈는 자식들의 사이즈 + 1
       int[]sz = new int[100010];
       int []depth = new int[100010];
       void dfs(int cur){
            if(cur > V) return;
            sz[cur] = 1;
            for(int i=0; i < childs[cur].size; i++){
                int child = childs[cur].get(i);
                depth[child] = depth[cur]+1;
                dfs(child);
                sz[cur]+=sz[child];
            }
       }
    이진 트리에서 임의의 두 정점의 가장 가까운 공통 조상을 찾고, 그 정점을 루트로 하는 서브 트리의 크기를 알아내는 프로그램

    지금 작성한 코드는 childs를 사용하지 않았네, 자식 정보를 binaryTrees로 관리
 */
public class Solution {
    static StringBuilder sb = new StringBuilder();
    static int V, E, N1, N2;
    static int[] parent;
//    static List<Integer>[] childs;
    static Node[] binaryTree;
    static List<Integer> ancestorN1;
    static List<Integer> ancestorN2;
    static int ansParent, ansSize;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken()); // # of Vertexes
            E = Integer.parseInt(st.nextToken()); // # of Edges
            N1 = Integer.parseInt(st.nextToken()); // N1
            N2 = Integer.parseInt(st.nextToken()); // N2
            parent = new int[V + 1];
//            childs = new ArrayList[V + 1];
            binaryTree = new Node[10000 + 1];
//            for (int i = 1; i <= V; i++) {
//                childs[i] = new ArrayList<>();
//            }
            for (int i = 1; i <= 10000; i++) {
                binaryTree[i] = new Node();
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                parent[c] = p;
                //childs[p].add(c);
                if(binaryTree[p].left > 0) binaryTree[p].right = c;
                else binaryTree[p].left = c;
            }
            ancestorN1 = new ArrayList<>();
            findAncestor(ancestorN1, parent[N1]);
            ancestorN2 = new ArrayList<>();
            findAncestor(ancestorN2, parent[N2]);
//            System.out.println(t+" "+Arrays.toString(ancestorN1.toArray()));
//            System.out.println(t+" "+Arrays.toString(ancestorN2.toArray()));
            int i1 = ancestorN1.size()-1;
            int i2 = ancestorN2.size()-1;
            while (i1 > -1 && i2 > -1) {
                if (Objects.equals(ancestorN1.get(i1), ancestorN2.get(i2))) {
                    ansParent = ancestorN1.get(i1);
                    i1--;
                    i2--;
                }else{
                    break;
                }
            }
//            System.out.println(ansParent);
            ansSize = dfs(ansParent);
            sb.append('#').append(t).append(' ').append(ansParent).append(' ').append(ansSize).append('\n');
        }
        System.out.println(sb.toString());
        br.close();
    }
    public static int dfs(int x){
        if(x <= 0 ) return 0;
        return dfs(binaryTree[x].left) + dfs(binaryTree[x].right) +1;
    }
    public static void findAncestor(List<Integer> anc, int x) {
        if( x <= 0 ) return;
        anc.add(x);
        findAncestor(anc, parent[x]);
    }
    static class Node{
        int left;
        int right;
        public Node(){
            left = right = -1;
        }
    }
}
