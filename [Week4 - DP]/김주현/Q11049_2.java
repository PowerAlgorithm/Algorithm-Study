// 11049번 행렬 곱셈 순서 (G3) [dp]
/*
<문제 정보>
 1. 행렬 곱셈이 주어졌을 때 필요한 곱셈 연산 횟수의 최솟값 구하기
    - 최악의 순서로 연산해도 int 범위 안

<변수 범위>
 1. 1초 / 256MB
 2. 행렬 개수 1<=N<=500
 3. 행렬의 크기 1<=r,c<=500



<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q11049_2 {
    static int N;
    static int[][] matrixList;
    static int[][] dp;
    static final int ROW = 0;
    static final int COLUMN = 1;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        matrixList = new int[N+1][2];
        dp = new int [N+1][N+1];

        for (int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            matrixList[i][ROW] =  Integer.parseInt(st.nextToken());
            matrixList[i][COLUMN] =  Integer.parseInt(st.nextToken());
        }

        SetInitDP();
        solveDP();

        /*for (int i=1;i<=N;i++) {
            for (int j=1;j<=N;j++) System.out.printf("%d ",dp[i][j]);
            System.out.println();
        }
        System.out.println();*/
        sb.append(dp[1][N]);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void SetInitDP() {
        for (int i=1;i<N;i++) {
            dp[i][i+1] = matrixList[i][ROW] * matrixList[i][COLUMN] * matrixList[i+1][COLUMN];
        }
    }

    public static void solveDP() {
        int fromCost, toCost;

        for (int dis=2;dis<N;dis++) {
            for (int i=1;i+dis<=N;i++) {
                dp[i][i+dis] = INF;
                for (int via=i;via<i+dis;via++) {
                    fromCost = dp[i][via];
                    toCost = dp[via+1][i+dis];
                    dp[i][i+dis] = Math.min(
                            dp[i][i+dis],
                            fromCost + toCost + getCalculationNum(i,via,i+dis)
                    );
                }
            }
        }
    }

    public static int getCalculationNum(int start, int mid, int end) {
        return matrixList[start][ROW] * matrixList[mid][COLUMN] * matrixList[end][COLUMN];
    }
}

















