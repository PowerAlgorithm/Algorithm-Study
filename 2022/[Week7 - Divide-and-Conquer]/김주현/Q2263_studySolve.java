// 2263번 트리의 순회 (G2) [분할정복]
/*
<문제 정보>
 1. n개의 정점을 갖는 이진 트리의 정점에 1 ~ N 까지의 번호가 중복없이 매겨져있다.
 2. 이진트리의 인오더와 포스트오더가 주어졌을 때, 프리오더를 출력

<변수 범위>
 1. 5초 / 128MB
 2. 1<=N<=100,000

<프로그램 진행>
 1.

<필요 함수>
 1.

 */

import java.io.*;
import java.util.StringTokenizer;

public class Q2263_studySolve {
    static int N;
    static int[] inOrder;
    static int[] postOrder;
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        inOrder = new int[N];
        postOrder = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) inOrder[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) postOrder[i] = Integer.parseInt(st.nextToken());
        getPreOrder(0,0,N);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    public static void getPreOrder(int startIn, int startPost, int length) {
        if (length==1) {
            sb.append(inOrder[startIn]).append(" ");
            return;
        }
        sb.append(postOrder[startPost+length-1]).append(" ");
        int endIn = startIn + length -1;
        int leftLength=0;
        int rightLength=0;
        for (int i=0;i<=length/2;i++) {
            if (inOrder[startIn+i]==postOrder[startPost+length-1] || inOrder[endIn-i]==postOrder[startPost+length-1]) {
                leftLength = inOrder[startIn+i]==postOrder[startPost+length-1] ? i : length-i-1;
                rightLength = length-leftLength-1;
                break;
            }
        }
        if(leftLength!=0) getPreOrder(startIn,startPost,leftLength);
        if(rightLength!=0) getPreOrder(startIn+leftLength+1,startPost+leftLength,rightLength);
    }
}