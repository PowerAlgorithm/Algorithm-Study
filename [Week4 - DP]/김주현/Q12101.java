// 12101번 1, 2, 3 더하기 2 (S1) [DP]
/*
<문제 정보>
 1. 정수 n과 k가 주어졌을 때 n을 1,2,3 의 합으로 나타내는 방법중 k번째로 오는 식을 구하는 프로그램 작성
    - k번째 오는 식이 없을 경우 -1

<변수 범위>
 1. 1초 / 512MB
 2. n<=11
 3. k<=2^31-1

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q12101 {
    static int N, K;
    static int[] dp;
    static String[][] initValue = new String[4][5];
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[N+1];
        SetInitValue();
        MakeDPArray();
        SolveDP(N, K);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void SolveDP(int n, int k) {
        if (k>dp[n]) {
            sb.append(-1);
            return;
        }

        if (n<=3) {
            sb.append(initValue[n][k]);
            return;
        }
        // 1을 쓰는 경우
        if (k<=dp[n-1]) {
            sb.append("1+");
            SolveDP(n-1,k);
        }
        else if (k<=dp[n-1]+dp[n-2]) {
            sb.append("2+");
            SolveDP(n-2, k-dp[n-1]);
        }
        else {
            sb.append("3+");
            SolveDP(n-3,k-dp[n-1]-dp[n-2]);
        }
    }

    public static void MakeDPArray() {
        for (int i=4;i<=N;i++) dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
    }

    public static void SetInitValue() {
        initValue[1][1] = "1";
        dp[1] = 1;

        if(N>=2){
            initValue[2][1] = "1+1";
            initValue[2][2] = "2";
            dp[2] = 2;

            if(N>=3){
                initValue[3][1] = "1+1+1";
                initValue[3][2] = "1+2";
                initValue[3][3] = "2+1";
                initValue[3][4] = "3";
                dp[3] = 4;
            }
        }
    }
}















