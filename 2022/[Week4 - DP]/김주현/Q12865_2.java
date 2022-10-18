// 12865번 평범한 배낭 (G5) [DP]
/*
<문제 정보>
 1. 각 물건 : 무게 W, 가치 V
 2. 최대 K만큼의 무게만을 넣을 수 있다.
 3. N개의 물건을 가방에 넣을 때 총 가치의 최댓값 구하기


<변수 범위>
 1. 2초 / 512MB
 2. 물품의 수 1<=N<=100
 3. 무게제한 1<=K<=100,000
 4. 무게 1<=W<=100,000
 5. 가치 0<=V<=1,000

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q12865_2 {
    static int N, weightLimit;
    static int[][] itemList;
    static int[][] dp;
    static final int WEIGHT = 0;
    static final int VALUE = 1;
    static final int EMPTY = -1;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        weightLimit = Integer.parseInt(st.nextToken());
        itemList = new int[N+1][2];
        dp = new int[weightLimit+1][N+1];
        for (int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            itemList[i][WEIGHT] = Integer.parseInt(st.nextToken());
            itemList[i][VALUE] = Integer.parseInt(st.nextToken());
        }

        for (int i=0;i<=weightLimit;i++) {
            for (int j=0;j<=N;j++) {
                if(i!=0 && j!=0) dp[i][j] = EMPTY;
            }
        }

//        sb.append(solveDP(weightLimit, N));
//        PrintDP();

        solveDP2();
        sb.append(dp[weightLimit][N]);



        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int solveDP(int weightLimit, int item) {
        if (dp[weightLimit][item]!=EMPTY) return dp[weightLimit][item];

        dp[weightLimit][item] = solveDP(weightLimit, item-1);
        if (itemList[item][WEIGHT]<=weightLimit) {
            dp[weightLimit][item] = Math.max(dp[weightLimit][item],
                    itemList[item][VALUE] + solveDP(weightLimit-itemList[item][WEIGHT],item-1));
        }
        return dp[weightLimit][item];
    }

    public static void solveDP2 () {
        for (int w=1;w<=weightLimit;w++) {
            for (int item=1;item<=N;item++) {
                if (itemList[item][WEIGHT]<=w) {
                    dp[w][item] = Math.max(dp[w][item-1], itemList[item][VALUE] + dp[w-itemList[item][WEIGHT]][item-1]);
                }
                else dp[w][item] = dp[w][item-1];
            }
        }
    }

    public static void PrintDP() {
        for (int i=0;i<=weightLimit;i++) {
            for (int j=0;j<=N;j++) System.out.printf("%d ",dp[i][j]);
            System.out.println();
        }
    }
}

















