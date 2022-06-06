// 17829번 222-풀링 (S2) [분할정복]
/*
<문제 정보>
 1. 2x2 정사각형으로 쪼갠다.
 2. 각 정사각형에서 2번째로 큰수만 남긴다.
 3. 이렇게 해서 크기가 전체 크기가 1x1이 될때까지 222-풀링 을 진행한다.
 4. 마지막으로 남는 수를 출력한다.

<변수 범위>
 1. 1초 / 256BM
 2. 2<=N<=1024, N은 항상 2의 거듭제곱꼴
 3. 행렬의 각 성분은 절댓값이 10,000 이하인 정수

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Q17829 {
    static int N;
    static int[][] arr;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }
        pulling(N,0,0);
        sb.append(arr[0][0]);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void pulling (int size, int startX, int startY) {
        if (size==2) {
            Combine(size, startX, startY);
            return;
        }

        pulling(size/2, startX, startY);
        pulling(size/2, startX, startY+size/2);
        pulling(size/2, startX+size/2, startY);
        pulling(size/2, startX+size/2, startY+size/2);
        Combine(size, startX, startY);
    }

    public static void Combine (int size, int startX, int startY) {
        int[] temp = new int[4];
        int idx = 0;
        for (int i=0;i<2;i++) {
            for (int j=0;j<2;j++) temp[idx++] = arr[startX + i*(size/2)][startY + j*(size/2)];
        }
        Arrays.sort(temp);
        arr[startX][startY] = temp[2];
    }
}













