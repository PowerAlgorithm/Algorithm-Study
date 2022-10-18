// 1184번 귀농 (G1) [해시맵 & 브루트포스]
/*
<문제 정보>
 1.

<변수 범위>
 1.

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Q1184 {
    static int N;
    static int count = 0;
    static int[][] profits;
    static int[][] profitSum;

    static final boolean FROM_TOP_LEFT_TO_BOTTOM_RIGHT = true;
    static final boolean FROM_TOP_RIGHT_TO_BOTTOM_LEFT = false;

    static final int BOTTOM_LEFT = 1;
    static final int BOTTOM_RIGHT = 2;
    static final int TOP_LEFT = 3;
    static final int TOP_RIGHT = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        profits = new int[N+1][N+1];
        profitSum = new int[N+1][N+1];
        for (int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1;j<=N;j++) profits[i][j] = Integer.parseInt(st.nextToken());
        }

        getSquareSum();
        // printProfitSum();

        /*
        (1,1) ~ (N-1, N-1) 돌아가며 체크
        한번 체크할 때
            1. 대각선 2개 방향을 체크
            2. 만약 5x5 에서 (2,3) 을 체크한다면 ?
                2-1. 4개로 쪼개짐
                    - (1,1) - (2,3) : 넣기
                    - (3,4) - (5,5) : 확인

                    - (1,4) - (2,5) : 넣기
                    - (3,1) - (5,3) : 확인
            3. (일반화) 만약 NxN 에서 (x,y)를 체크한다면 ?
                3-1. 4개로 쪼개짐
                    - (1,1) - (x,y) : 넣기
                    - (x+1,y+1) - (N,N) : 확인

                    - (1,y+1) - (x,N) : 넣기
                    - (x+1,1) - (N,y) : 확인
        */

        for (int i=1;i<N;i++) {
            for (int j=1;j<N;j++) checkPoint(i,j);
        }

        sb.append(count);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // *************** 지점 체크 함수 ********************

    public static void checkPoint(int x, int y) {
//        System.out.printf("[check point] x : %d, y : %d\n",x, y);
        checkPairOfSquares(x, y, FROM_TOP_LEFT_TO_BOTTOM_RIGHT);
//        System.out.printf("count : %d\n",count);
        checkPairOfSquares(x, y, FROM_TOP_RIGHT_TO_BOTTOM_LEFT);
//        System.out.printf("count : %d\n",count);
    }

    public static void checkPairOfSquares(int x, int y, boolean way) {
        HashMap<Integer, Integer> profit = new HashMap<>();
        if (way == FROM_TOP_LEFT_TO_BOTTOM_RIGHT) {
            // (1,1) ~ (x,y)
            findSumOfPartialSquare(profit, x, y, BOTTOM_RIGHT);
            // (x+1, y+1) ~ (N,N)
            findSumOfPartialSquare(profit, x+1, y+1, TOP_LEFT);
        }
        else {
            // (x, y+1) ~ (1,N)
            findSumOfPartialSquare(profit, x, y+1, BOTTOM_LEFT);
            // (x+1, y) ~ (N,1)
            findSumOfPartialSquare(profit, x+1, y, TOP_RIGHT);
        }
    }

    public static void findSumOfPartialSquare(HashMap<Integer, Integer> profit, int refX, int refY, int refPoint) {
        int partialProfit=0;

        switch(refPoint) {
            // direction : up right -> (1,N)
            case BOTTOM_LEFT :
                for (int x=refX; x>=1; x--) {
                    for (int y=refY; y<=N; y++) {
                        partialProfit = getPartialProfit(x-1, refY-1, refX, y);
                        if (!profit.containsKey(partialProfit)) profit.put(partialProfit, 1);
                        else profit.replace(partialProfit, profit.get(partialProfit)+1);
                    }
                }
                break;

            case BOTTOM_RIGHT :
                for (int x=refX; x>=1; x--) {
                    for (int y=refY; y>=1; y--) {
                        partialProfit = getPartialProfit(x-1, y-1, refX, refY);
                        if (!profit.containsKey(partialProfit)) profit.put(partialProfit, 1);
                        else profit.replace(partialProfit, profit.get(partialProfit)+1);
                    }
                }
                break;

            case TOP_LEFT :
                for (int x=refX; x<=N; x++) {
                    for (int y=refY; y<=N; y++) {
                        partialProfit = getPartialProfit(refX-1, refY-1, x, y);
                        if (profit.containsKey(partialProfit)) count += profit.get(partialProfit);
                    }
                }
                break;

            case TOP_RIGHT :
                for (int x=refX; x<=N; x++) {
                    for (int y=refY; y>=1; y--) {
                        partialProfit = getPartialProfit(refX-1, y-1, x, refY);
                        if (profit.containsKey(partialProfit)) count += profit.get(partialProfit);
                    }
                }
        }
    }

    public static int getPartialProfit (int x1, int y1, int x2, int y2) {
        return profitSum[x2][y2] - profitSum[x1][y2] - profitSum[x2][y1] + profitSum[x1][y1];
    }


    // 2차원 배열 부분합 구하기
    public static void getSquareSum() {
        for (int i=1;i<=N;i++) {
            for (int j=1;j<=N;j++) {
                profitSum[i][j] = profitSum[i][j-1] + profits[i][j];
            }
        }

        for (int i=1;i<=N;i++) {
            for (int j=1;j<=N;j++) {
                profitSum[i][j] += profitSum[i-1][j];
            }
        }
    }

    // print 함수
    public static void printProfitSum() {
        for (int i=1;i<=N;i++) {
            for (int j=1;j<=N;j++) System.out.printf("%d ",profitSum[i][j]);
            System.out.println();
        }
    }
}
