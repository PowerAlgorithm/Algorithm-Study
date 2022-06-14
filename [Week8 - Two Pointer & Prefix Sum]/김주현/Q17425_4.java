// 17425번 약수의 합 (G4) [수학 - 에라토스테네스의 체 응용]
/*
<문제 정보>
 1. f(x) : x의 약수의 합
 2. g(x) : f(1) + f(2) + ... + f(x)
 3. g(N)을 구하자.

<변수 범위>
 1. 1초 / 512MB
 2. 테스트케이스 1<=T<=100,000
 3. 자연수 N 1<=N<=1,000,000

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.Arrays;

public class Q17425_4 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        long[] value = new long[1_000_001];
        Arrays.fill(value,1);
        value[0] = 0;
        for (int i=2;i<=1_000_000;i++) {
            for (int j=i;j<value.length;j+=i) {
                value[j] += i;
            }
        }
        for (int i=1;i<=1_000_000;i++) value[i] += value[i-1];
        int T = Integer.parseInt(br.readLine());
        while(T-- >0) {
            sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
