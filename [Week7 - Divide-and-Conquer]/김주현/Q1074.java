// 1074번 Z (S1) [분할정복] [수학 -> 비트마스킹]
/*
<문제 정보>
 1. 2^N x 2^N 배열을 Z모양으로 방문할 때 r행 c열을 몇번째로 방문하는지 출력

<변수 범위>
 1. 0.5초 / 512MB
 2. 1<=N<15
 3. 0<=r,c<2^N

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q1074 {
    static int N, r, c;
    static boolean findAns = false;

    static final boolean ROW = true;
    static final boolean COLUMN = false;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int maxSize = (int) Math.pow(2,N);
        visitingByZ(maxSize, 0, 0, 0);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void visitingByZ(int size, int startX, int startY, int initValue) {
//        System.out.printf("size : %d, startX : %d, startY : %d\n",size, startX, startY);
        if (size==2) {
            for (int i=0;i<2;i++) {
                for (int j=0;j<2;j++) {
                    if (r==startX+i && c==startY+j) {
                        System.out.println(initValue+(2*i)+j);
                        findAns = true;
                        return;
                    }
                }
            }
        }

        if (findAns) return;
        int orderUnit = size*size/4;
        int lengthUnit = size/2;
        int nextValue;
        for (int i=0;i<2;i++) {
            if(!isRightArea(size/2,startX+(lengthUnit*i),ROW)) continue;
            for (int j=0;j<2;j++) {
                if (isRightArea(size/2,startY+(lengthUnit*j), COLUMN)) {
                    nextValue = initValue + (orderUnit*((2*i)+j));
                    visitingByZ(size/2, startX+(lengthUnit*i), startY+(lengthUnit*j), nextValue);
                }
            }
        }
    }

    public static boolean isRightArea(int size, int startInfo, boolean type) {
//        System.out.printf("<영역판별> size : %d, startX : %d, startY : %d\n",size, startX, startY);
        boolean rowRight = r>=startInfo && r<startInfo+size;
        boolean columnRight = c>=startInfo && c<startInfo+size;
        return type ? rowRight : columnRight;
    }
}
