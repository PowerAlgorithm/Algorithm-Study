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

// 여기서 제곱근 분할법이 어떻게 쓰일까??

import java.io.*;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;

public class Q1354_4 {
    static long N, size;
    static int P, Q, X, Y;
    static long[] arr;

    static final int MAX = 10_000_000;

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

        size = Math.min(N, MAX);
        arr = new long[(int) size+1];
        getDP();
        if (N<=MAX) sb.append(arr[(int)size]);
        else sb.append(solveDP(N));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static long solveDP(long n) {
        Deque<Long> stack = new ArrayDeque<>();
        stack.add(n);

        long nowNum;
        long subTerm = 0;
        long ans = 0;

        while(!stack.isEmpty()) {
            nowNum = stack.pollLast();

            for (int i=0;i<2;i++) {
                subTerm = i==0 ? nowNum/P-X : nowNum/Q-Y;

                if (subTerm <= 0) ans += 1;
                else if (subTerm <= MAX) ans += arr[(int)subTerm];
                else stack.add(subTerm);
            }
        }
        return ans;
    }

    public static void getDP() {
        arr[0] = 1;
        int pTerm = -X;
        int qTerm = -Y;
        long pTermValue, qTermValue;
        for (int i=1;i<=size;i++) {
            if (i % P == 0) pTerm++;
            if (i % Q == 0) qTerm++;
            pTermValue = pTerm <= 0 ? 1 : arr[pTerm];
            qTermValue = qTerm <= 0 ? 1 : arr[qTerm];
            arr[i] = pTermValue + qTermValue;
        }
    }
}

