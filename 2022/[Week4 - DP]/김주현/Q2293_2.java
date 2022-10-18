// 2293번 동전1 (G5) [dp]
/*
<문제 정보>
 1. n개의 동전을 사용해서 가치의 합이 k가 되는 경우의 수 구하기

<변수 범위>
 1. 0.5초 / 4MB
 2. 1<=n<=100
 3. 1<=k<=10,000
 4. 가치 100,000 보다 작거나 같은은자연수

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Q2293_2 {
    static int N, K;
    static Integer[] coins;
    static int[][] dp;
    static final int EMPTY = -1;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coins = new Integer[N+1];
        dp = new int[K+1][N+1];
        for (int i=1;i<=N;i++) coins[i] = Integer.parseInt(br.readLine());

        Arrays.sort(coins,1,N,(a,b) -> b-a);
        resetDP();
        solveDP();

//        PrintDP();
        sb.append(dp[K][N]);


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solveDP() {
        for (int v=1;v<=K;v++) {
            for (int item=1;item<=N;item++) {
                if (dp[v][item] == EMPTY) dp[v][item] = 0;
                dp[v][item] += dp[v][item-1];
                if (coins[item] <= v) dp[v][item] += dp[v-coins[item]][item];
            }
        }
    }

    public static void resetDP() {
        for (int i=0;i<=K;i++) {
            for (int j=0;j<=N;j++) {
                if (i==0 || j==0) dp[i][j] = 0;
                else dp[i][j] = EMPTY;
            }
        }

        for (int i=1;i<=N;i++) {
            if (coins[i]<=K) dp[coins[i]][i] = 1;
        }
    }

    public static void PrintDP() {
        for (int i=1;i<=K;i++) {
            for (int j=1;j<=N;j++) System.out.printf("%d ",dp[i][j]);
            System.out.println();
        }
        System.out.println();
    }
}
