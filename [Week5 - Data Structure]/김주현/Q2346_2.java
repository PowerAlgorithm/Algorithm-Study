// 2346번 풍선 터뜨리기 (S3) [자료구조 - 덱]
/*
<문제 정보>
 1. 풍선

<변수 범위>
 1. 2초 / 4MB
 2. 1<=N<=1,000

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;

public class Q2346_2 {
    static int N;
    static int[] arr;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) arr[i] = Integer.parseInt(st.nextToken());

        Deque<Integer> dq = new ArrayDeque<>();
        for (int i=1;i<=N;i++) dq.add(i);

        boolean isRight = true;
        int dist = 1;

        int poppedBalloon;
        while(!dq.isEmpty()) {
            while(dist>1) {
                if (isRight) dq.add(dq.poll());
                else dq.addFirst(dq.pollLast());
                dist--;
            }
            if (isRight) poppedBalloon = dq.poll();
            else poppedBalloon = dq.pollLast();
            sb.append(poppedBalloon).append(" ");
            dist = arr[poppedBalloon];
            isRight = dist>0;
            dist = Math.abs(dist);
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}




















