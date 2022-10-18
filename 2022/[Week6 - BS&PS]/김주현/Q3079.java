// 3079번 입국심사 (G5) [매개변수 탐색]
/*
<문제 정보>
 1. N개의 입국 심사대에서 M명이 입국심사를 받을 때 걸리는 시간의 최솟값

<변수 범위>
 1. 1초 / 128MB
 2. 1<=N<=100,000
 3. 1<=M<=1,000,000,000
 4. 심사에 걸리는 시간 1<=T_k<=10^9

 ** overflow 반드시 처리

<프로그램 진행>
 1. f(시간) = 사람 M명 처리가능?
 2. 일정 시간 이상이면 M명 처리 가능
  => BS_LowerBound

<필요 함수>
 1.

 */

// <overflow>

import java.io.*;
import java.util.StringTokenizer;

public class Q3079 {
    static int N, M;
    static int[] times;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        times = new int[N];
        for (int i=0;i<N;i++) times[i] = Integer.parseInt(br.readLine());
        sb.append(BS_LowerBound(M));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static long BS_LowerBound (int key) {
        long lo = 0;
        long hi = (long) Math.pow(10,18);
        long mid;

        while(lo<hi) {
            mid = (lo+hi)/2;
            if (!isPersonPossible(mid, key)) lo = mid+1;
            else hi = mid;
        }
        return lo;
    }

    public static boolean isPersonPossible (long time, int person) {
        long capacity = 0;
        for (int i=0;i<N;i++) {
            capacity += time/times[i];
            if (capacity>=person) return true;
        }
        return false;
    }
}
