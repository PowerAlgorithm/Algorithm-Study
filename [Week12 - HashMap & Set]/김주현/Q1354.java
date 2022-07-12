// 1354번 무한수열2 (G4) [해시를 이용한 DP]
/*
<문제 정보>
 1. A_i = 1 (i<=0)
 2. A_i = A_(i/P-X) + A_(i/Q-Y) (i>=1) (/ -> 몫 연산)
 3. A_N 출력

<변수 범위>
 1. 10초 / 512MB
 2. 0<=N<=10^13
 3. 2<=P,Q<=10^9
 4. 0<=X,Y<=10^9

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Q1354 {
    static long N;
    static int P, Q, X, Y;
    static HashMap<Long, Long> arr = new HashMap<>();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // ******************** 입력 & 초기화 ********************

        st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        // ******************** 메인 로직 & 정답 출력 ********************

        sb.append(solveDP(N));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static long solveDP(long n) {
        if (n<=0) return 1;
        if (arr.containsKey(n)) return arr.get(n);

        long firstTermValue = solveDP(n/P - X);
        long secondTermValue = solveDP(n/Q - Y);
        arr.put(n, firstTermValue + secondTermValue);
        return arr.get(n);
    }
}

