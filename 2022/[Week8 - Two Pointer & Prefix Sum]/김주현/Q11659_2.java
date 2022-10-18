// 11659번 구간 합 구하기4 (S3) [누적합]
/*
<문제 정보>
 1. 수 N개 주어졌을 때, i부터 j번째 수까지의 합 출력

<변수 범위>
 1. 1초 / 256MB
 2. 1<=N<=100,000
 3. 구해야 하는 횟수 1<=M<=100,000
 4. 수열의 각 수는 1,000 이하의 자연수

<프로그램 진행>
 1.

<필요 함수>
 1.

 */

import java.io.*;
import java.util.StringTokenizer;

public class Q11659_2 {
    static int N,M;
    static int[] sums;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sums = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) {
            sums[i] = sums[i-1]+Integer.parseInt(st.nextToken());
        }
        int lowIdx, highIdx;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            lowIdx = Integer.parseInt(st.nextToken());
            highIdx = Integer.parseInt(st.nextToken());
            sb.append(sums[highIdx]-sums[lowIdx-1]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
