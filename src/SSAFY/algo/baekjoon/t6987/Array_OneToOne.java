package SSAFY.algo.baekjoon.t6987;

public class Array_OneToOne {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        int[][] map = {
                {0, 1, 2, 3},
                {0, 1, 2, 3},
                {0, 1, 2, 3},
                {0, 1, 2, 3}
        };
        // home away처럼 두 번 대결
//        for (int i = 0; i < map.length; i++) {
//            for (int j = 0; j < map.length; j++) {
//                if( i== j) continue;
//                sb.append(map[j][i]).append(" ").append(map[i][j]).append(" ");
//            }
//            sb.append("\n");
//        }
        // home - away 방식
        int[] t = new int[]{0, 1, 2, 3};
//        for (int i = 0; i < t.length; i++) {
//            for (int j = 0; j < t.length; j++) {
//                if(i == j) continue;
//                sb.append(t[i]).append("-").append(t[j]).append(" ");
//            }
//            sb.append("\n");
//        }
        // 우리는 두 팀이 한번만 대결하는 형태를 원한다
//        for (int i = 0; i < map.length-1; i++) {
//            for (int j = i+1; j < map.length; j++) {
//                sb.append(map[j][i]).append(" ").append(map[i][j]).append(" ");
//            }
//            sb.append("\n");
//        }
        for (int i = 0; i < t.length-1; i++) {
            for (int j = i + 1; j < t.length; j++) {
                sb.append(t[i]).append("-").append(t[j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }
}
