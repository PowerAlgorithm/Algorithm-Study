// 1106번 호텔 (G5) [dp]
/*
<문제 정보>
 1. 각 도시마다 cost 원을 투자해서 person 수만큼의 고객이 늘어난다고 할 때,
    적어도  C명의 고객을 늘이기 위해 투자해야 하는 돈의 최솟값

<변수 범위>
 1. 2초 / 128MB
 2. 1<=C<=1,000
 3. 1<=N<=20
 4. 도시별 cost, person 100 이하 자연수

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q1106 {
    static int customers;
    static int cities;
    static int[][] cityList;
    static int[][] dp;
    static final int COST = 0;
    static final int CUSTOMER = 1;
    static final int IMPOSSIBLE = 0;


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        customers = Integer.parseInt(st.nextToken());
        cities = Integer.parseInt(st.nextToken());
        cityList = new int[cities+1][2];
        dp = new int[customers+1][cities+1];
        for (int i=1;i<=cities;i++) {
            st = new StringTokenizer(br.readLine());
            cityList[i][COST] = Integer.parseInt(st.nextToken());
            cityList[i][CUSTOMER] = Integer.parseInt(st.nextToken());
        }

        solveDP();
//        PrintDP();

        sb.append(dp[customers][cities]);



        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solveDP() {
        int possibleCost1;
        int possibleCost2;
        for (int custmr=1;custmr<=customers;custmr++) {
            for (int city=1;city<=cities;city++) {
                possibleCost1 = dp[custmr][city-1];
                /*
                if (cityList[city][CUSTOMER]==custmr) {
                    possibleCost2 = cityList[city][COST];
                }
                else if (cityList[city][CUSTOMER]>custmr) {
                    possibleCost2 = dp[custmr - cityList[city][CUSTOMER]][city] == IMPOSSIBLE ?
                            IMPOSSIBLE : cityList[city][COST] + dp[custmr - cityList[city][CUSTOMER]][city];
                }
                else possibleCost2 = IMPOSSIBLE;
                */

                if (custmr <= cityList[city][CUSTOMER]) possibleCost2 = cityList[city][COST];
                else {
                    possibleCost2 = dp[custmr - cityList[city][CUSTOMER]][city] == IMPOSSIBLE ?
                            IMPOSSIBLE : cityList[city][COST] + dp[custmr - cityList[city][CUSTOMER]][city];
                }



                if (possibleCost1==IMPOSSIBLE || possibleCost2==IMPOSSIBLE) {
                    dp[custmr][city] = Math.max(possibleCost1, possibleCost2);
                }
                else dp[custmr][city] = Math.min(possibleCost1, possibleCost2);
            }
        }
    }

    public static void PrintDP() {
        for (int i=1;i<=customers;i++) {
            for (int j=1;j<=cities;j++) System.out.printf("%d ",dp[i][j]);
            System.out.println();
        }
        System.out.println();
    }
}














