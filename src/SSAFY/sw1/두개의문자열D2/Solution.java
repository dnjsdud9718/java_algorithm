package SSAFY.sw1.두개의문자열D2;

import java.io.*;
import java.util.StringTokenizer;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int aSize = Integer.parseInt(st.nextToken());
            int bSize = Integer.parseInt(st.nextToken());
            int [] aArr = new int[aSize];
            int [] bArr = new int[bSize];
            
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<aSize; i++){
                aArr[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<bSize; i++){
                bArr[i] = Integer.parseInt(st.nextToken());
            }

            int [] bigger;
            int [] smaller;
            if(aSize >= bSize){
                bigger = aArr;
                smaller = bArr;
            }else{
                bigger = bArr;
                smaller = aArr;
            }
            int m = Integer.MIN_VALUE;

            //IDEA * 컴퓨터 비전, Feature Map 크기 계산 (I - F + 2P)/S + 1
            //I(입력 크기), F(피처 크기), P(패딩), S(스트라이드)
            
            for(int i=0; i< bigger.length - smaller.length + 1; i++){
                int sum = 0;
                for(int j=0; j<smaller.length; j++){
                    sum += bigger[i+j] * smaller[j];
                }
                if(sum >= m) m = sum;
            }
            
            bw.write("#"+test_case+" "+m+"\n");
		}
        bw.flush();
        bw.close();
        br.close();
	}
}