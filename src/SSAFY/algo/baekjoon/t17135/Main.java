package SSAFY.algo.baekjoon.t17135;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
   케슬 디펜스
 */
public class Main {
    static int N, M, D, max, anchorDepth, eLen;
    static Enemy[] enemiesOriginal;
    static int[] archers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        eLen = 0;
        archers = new int[3];
        enemiesOriginal = new Enemy[N * M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int v = Integer.parseInt(st.nextToken());
                if(v == 1) enemiesOriginal[eLen++] = new Enemy(i, j, 0); 
            }
        }
        max = Integer.MIN_VALUE;
        comb(0, 0);
        System.out.println(max);
        br.close();
    }

    static void simul() {
        int[][] enemies = new int[eLen][3];
        for (int i = 0; i < eLen; i++) {
            Enemy e = enemiesOriginal[i];
            enemies[i][0] = e.r;
            enemies[i][1] = e.c;
            enemies[i][2] = 0;
        }
        anchorDepth = N;
        // 게임 시작
        int kill = 0;
        while (anchorDepth != 0) {
            Set<Integer> heat = new HashSet<>(); // 해당 라운드에서 맞은 적, 한 명의 적은 여러명의 궁수에게 맞을 수 있다(주의)
            for (int i = 0; i < 3; i++) { // 선택된 궁수 3명
                int minD = Integer.MAX_VALUE;
                int minCol = Integer.MAX_VALUE;
                int enemy = Integer.MAX_VALUE;
                for (int j = 0; j < eLen; j++) {
                    if(enemies[j][2] == 1) continue;
                    // 거리 계산
                    int d = Math.abs(anchorDepth - enemies[j][0]) + Math.abs(archers[i] - enemies[j][1]);
                    if (minD > d) {
                        minD = d;
                        minCol = enemies[j][1];
                        enemy = j;
                    } else if (minD == d && minCol > enemies[j][1]) {
                        minCol = enemies[j][1];
                        enemy = j;
                    }
                }
                if (D >= minD) {
                    heat.add(enemy);
                }
            }
            // 죽은 적 계산
            Iterator<Integer> iterator = heat.iterator();
            while (iterator.hasNext()) {
                enemies[iterator.next()][2] = 1;
                kill += 1;
            }
            // round finish ->
            for (int i = 0; i < eLen; i++) {
                if(enemies[i][2] == 1) continue;
                // 적들이 성에 도달.
                if(enemies[i][0] == anchorDepth-1) enemies[i][2] = 1;
            }
            //궁수가 한 칸 올라가는 것으로 적들이 한 칸 내려오는 것을 구현
            anchorDepth -= 1;
        }
        max = Math.max(max, kill);
    }
    static void comb(int srcIdx, int tgtIdx) {
        if (tgtIdx == 3) {
            simul();    
            return;
        }
        for (int i = srcIdx; i < M; i++) {
            archers[tgtIdx] = i;
            comb(i + 1, tgtIdx + 1);
        }
    }

    static class Enemy {
        int r,c, dead; // dead-> 0: alive, 1: dead

        public Enemy(int r, int c, int dead) {
            this.r = r;
            this.c = c;
            this.dead = dead;
        }
    }
}
