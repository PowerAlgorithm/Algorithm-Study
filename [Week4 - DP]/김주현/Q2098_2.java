// 2098번 외판원 순회(Traveling Salesman problem : TSP) (G1)
/*
<문제 정보>
 1. 한 도시에서 출발해 N개의 도시를 모두 거쳐 다시 원래의 도시로 돌아오는 순회 여행의 최소 비용 구하기
    - 한번 갔던 도시는 갈 수 없음
    - 순회할 수 있는 경우만 입력으로 주어진다.

<변수 범위>
 1. 1초 / 128MB
 2. 도시의 수 2<=N<=16
 3. 각 행렬의 성분은 1,000,000 이하의 양의 정수, 갈 수 없는 경우 : 0

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q2098_2 {
    static int N;
    static int[][] cost;
    static int[][] dp;
    static final int START = 1;
    static final int IMPOSSIBLE = 0;
    static final int EMPTY = 0;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        cost = new int[N+1][N+1];
        dp = new int[N+1][1<<N];
        for (int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1;j<=N;j++) cost[i][j] = Integer.parseInt(st.nextToken());
        }

        sb.append(solveDP(1,1));
//        PrintDP();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int solveDP(int prevCity, int status) {

        if (dp[prevCity][status]!=EMPTY) return dp[prevCity][status];

        // 모든 도시가 탐색되었다면, 마지막 탐색된 곳에서 시작(1)로 가는 비용을 return, 갈 수 없다면 INF
        if (status == (1<<N)-1) {
            dp[prevCity][status] = cost[prevCity][START]==IMPOSSIBLE ? INF : cost[prevCity][START];
            return dp[prevCity][status];
        }

        dp[prevCity][status] = INF;
        // prevCity에서 갈 수 있고, 현재 status의 비트가 꺼져있는 곳을 탐색
        for (int nextCity=2;nextCity<=N;nextCity++) {
            if (cost[prevCity][nextCity] == IMPOSSIBLE || (status & (1<<(nextCity-1))) != 0) continue;
            // 다음 도시로 보냈는데, 거기서 길이 막히면 현재 조사중인 dp[prevCity][status] 는 더이상 갱신하지 않는다.
            if (solveDP(nextCity, status | (1<<(nextCity-1))) == INF) continue;
            dp[prevCity][status] =
                    Math.min(
                            dp[prevCity][status],
                            cost[prevCity][nextCity] + solveDP(nextCity, status | (1<<(nextCity-1)))
                    );
        }
        return dp[prevCity][status];
    }

    public static void PrintDP() {
        for (int i=1;i<=N;i++) {
            for (int j=0;j<(1<<N);j++) {
                if (dp[i][j]==INF) System.out.print("F ");
                else System.out.printf("%d ",dp[i][j]);
            }
            System.out.println();
        }
    }
}














