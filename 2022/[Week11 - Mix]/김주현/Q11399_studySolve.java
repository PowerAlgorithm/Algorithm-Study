// 11399번 ATM (S4) [그리디 - 정렬]
/*
<문제 정보>
 1. 각 사람이 돈을 인출하는데 걸리는 시간의 합의 최솟값

<변수 범위>
 1. 1초 / 256MB
 2. 1<=N<=1,000

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Q11399_studySolve {
    static int N;
    static int[] arr;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        // ************ 입력 **************
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) arr[i] = Integer.parseInt(st.nextToken());

        // ************ 메인 기능 ************

        /*
         인출하는데 시간이 많이 걸릴 수록 기다리는 시간에 적게 포함해야 함.
         1번째 사람이 인출하는데 걸리는 시간은 1번째 이외의 N-1명의 사람이 기다림 => 자신을 포함해 N번이 더해짐
         2번째 사람이 ~ 시간은 N-2명이 기다림 => 자신을 포함해 N-1번이 더해짐
         -> 정렬!
         */

        Arrays.sort(arr);
        int sum=0;
        for (int i=0;i<N;i++) sum += (N-i)*arr[i];
        sb.append(sum);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}











