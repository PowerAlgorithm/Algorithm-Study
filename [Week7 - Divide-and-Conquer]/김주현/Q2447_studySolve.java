// 2447번 별 찍기 - 10 (S1) [분할정복]
/*
<문제 정보>
 1.
    ***
    * *
    ***
  형태의 패턴으롤 별을 찍어보자.

<변수 범위>
 1. 1초 / 256MB
 2. N=3^k, 1<=k<8

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;

public class Q2447_studySolve {
    static int N;
    static boolean[][] stars;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        stars = new boolean[N][N];
        twinkleLittleStar(N,0,0);
        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) sb.append(stars[i][j] ? "*" : " ");
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void twinkleLittleStar(int size, int startX, int startY) {
        if (size==3) {
            for (int i=0;i<3;i++) {
                for (int j=0;j<3;j++) stars[startX+i][startY+j] = true;
            }
            stars[startX+1][startY+1] = false;
            return;
        }

        int unit = size/3;
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                if (i==1 && j==1) continue;
                twinkleLittleStar(unit, startX + (unit*i), startY + (unit*j));
            }
        }
    }
}










