package SSAFY.study.week14.t2655;
/**
 * 백준 가장 높은 탑 쌓기(2655)
 * 밑면이 정사각형인 직육면체 벽돌들을 사용하여 탑을 쌓고자 한다. 탑은 벽돌을 한 개씩 아래에서 위로 쌓으면서 만들어 간다.
 *
 * 1. 벽돌은 회전시킬 수 없다. 즉, 옆면을 밑면으로 사용할 수 없다.
 * 2. 밑면의 넓이가 같은 벽돌은 없으며, 또한 무게가 같은 벽돌도 없다.
 * 3. 벽돌들의 높이는 같을 수 있다.
 * 4. 탑을 쌓을 때 밑면이 좁은 벽돌 위에 밑면이 넓은 벽돌은 놓을 수 없다.
 * 5. 무게가 무거운 벽돌을 무게가 가벼운 벽돌 위에 놓을 수 없다.
 *
 *
 * 고려 사항이 두 가지다 -> 벽돌의 무게와 넓이
 * 이럴 때는 정렬을 사용하여 고려 사항을 줄이는 것이 좋다.
 *
 * 넓이로 정렬(오름차순)된 벽돌들에 대해서 i번째 벽돌이 가장 밑에 놓일 때 가질 수 있는 최대 높이를 고려하자
 * 넓이를 기준으로 정렬되었기 때문에 무게만 고려하면 된다.
 *
 */

import java.util.*;
import java.io.*;
public class Main
{
    static int N, max, loc;
    static Brick[] bricks;
    static int[] heights;
    static int[] cnt;
    static StringBuilder sb = new StringBuilder();

    static class Brick implements Comparable<Brick>{
        /**
         * idx : 벽돌에 매겨진 색인
         * area : 넓이
         * height : 높이
         * weight : 무게
         */
        int idx, area, height, weight;
        public Brick(int idx, int area, int height, int weight){
            this.idx = idx;
            this.area = area;
            this.height = height;
            this.weight = weight;
        }
        // 넓이를 기준으로 정렬(오름차순 정렬)
        public int compareTo(Brick o){
            return this.area - o.area;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        bricks = new Brick[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int area = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            bricks[i] = new Brick(i+1, area, height, weight);
        }
        Arrays.sort(bricks); // 넓이를 기준으로 정렬!
        heights = new int[N]; // heights[i] : i번째 벽돌이 바닥에 놓일 때 세울 수 있는 높이의 최댓값
        cnt = new int[N]; // cnt[i] : heights[i]를 구하기 위해 사용한 벽돌 수
        for(int i=0; i<N; i++) { // 초기값 세팅 : 자기 자신
            heights[i] = bricks[i].height;
            cnt[i] = 1;
        }
        for(int i=1; i<N; i++){
            Brick base = bricks[i]; // 바닥에 깔리는 벽돌
            for(int j=0; j<i; j++){
                // base보다 넓이와 무게가 작은 벽돌은 위에 쌓을 수 있다.
                // 위 조건을 만족하는 벽돌 중 높이를 최대화할 수 있는 벽돌 선택
                if(base.weight > bricks[j].weight && heights[i] < heights[j] + base.height) {
                    heights[i] = heights[j] + base.height;
                    cnt[i] = cnt[j]+1;
                }
            }
        }
        max = loc = 0;
        for(int i=0; i<N; i++){ // 최대 높이 찾기
            if(max < heights[i]){
                max = heights[i];
                loc = i;
            }
        }
        sb.append(cnt[loc]).append("\n");
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i=loc; i>=0; i--){
            if(max == heights[i]){
                stack.push(bricks[i].idx);
                max -= bricks[i].height;
            }
        }
        while(!stack.isEmpty()){
            sb.append(stack.pop()).append("\n");
        }
// 		System.out.println(Arrays.toString(heights));
// 		System.out.println(Arrays.toString(cnt));
// 		for(int i=0; i<N; i++){
// 		    Brick b = bricks[i];
// 		    System.out.println(b.idx + " " + b.area + " " + b.height + " " + b.weight);
// 		}

        System.out.println(sb);
    }
}
