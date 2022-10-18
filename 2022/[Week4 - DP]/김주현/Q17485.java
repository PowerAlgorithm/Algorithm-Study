// 17485번 진우의 달 여행(Large) (G5)
/*
<문제 정보>
 1. 움직일 수 있는 방향은 3 가지 ↙ ↓ ↘
    - 같은 방향 연속 2번 불가
 2. 지구 -> 달 연료 최솟값 출력

<변수 범위>
 1. 1초 / 256MB
 2. 2<=N,M<=1,000
 3. 각 비용 100 이하 자연수

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q17485 {
    static int N, M;
    static int[][] cost;
    static int[][][] dp;

    static int[] yDir = {0, -1, 0, 1};
    static final int INF = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cost = new int[N+1][M+1];
        dp = new int[N+1][M+1][4];

        for (int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1;j<=M;j++) cost[i][j] = Integer.parseInt(st.nextToken());
        }

        int minCost = INF;
        for (int i=1;i<=M;i++) {
            minCost = Math.min(minCost, solveDP(1,i,0));
        }

        sb.append(minCost);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // dp[x][y][d] : d의 방향을 사용하지 못할 때 x,y 에서 목적지 까지의 최소비용
    public static int solveDP (int x, int y, int impossibleDirection) {
        if (x==N) return cost[x][y];

        if (dp[x][y][impossibleDirection] != 0) return dp[x][y][impossibleDirection];

        int nextY;

        dp[x][y][impossibleDirection] = INF;
        for (int direction=1;direction<=3;direction++) {
            if (direction == impossibleDirection) continue;
            nextY = y + yDir[direction];
            if (nextY>0 && nextY<=M) {
                dp[x][y][impossibleDirection] = Math.min(dp[x][y][impossibleDirection], solveDP(x+1, nextY, direction));
            }
        }
        dp[x][y][impossibleDirection] += cost[x][y];
        return dp[x][y][impossibleDirection];
    }
}



































