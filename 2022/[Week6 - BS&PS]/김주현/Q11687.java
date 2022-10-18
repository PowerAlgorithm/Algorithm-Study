// 11687번 팩토리얼 0의 개수 (G5) [매개변수 탐색]
/*
<문제 정보>
 1. 가장 끝의 0의 개수가 M개인 N! 중에서 가장 작은 N 출력
    - 이러한 N이 없는 경우 -1 출력

<변수 범위>
 1. 0.5초 / 256MB
 2. 1<=M<=100,000,000

 ** M이 최대여도 N은 인트범위 내

<프로그램 진행>
 1. f(N) = 0의 개수
 2. N 증가 -> 0의 개수 같거나 증가
 3. 최소

<필요 함수>
 1.

 */


import java.io.*;

public class Q11687 {
    static int M;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        M = Integer.parseInt(br.readLine());
        sb.append(BS_LowerBound(M));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int BS_LowerBound (int key) {
        int lo = 0;
        int hi = 400000015;
        int mid;

        while(lo<hi) {
            mid = (lo+hi)/2;
            if (getNumOfZero(mid)<key) lo = mid+1;
            else hi = mid;
        }
        return getNumOfZero(lo)==key ? lo : -1;
    }

    public static int getNumOfZero (int N) {
        int zeroNum = 0;
        while (N>0) {
            N/=5;
            zeroNum+=N;
        }
        return zeroNum;
    }
}
