// 16401번 과자 나눠주기 (S2)
/*
<문제 정보>
 1. M 명의 조카, N개의 과자가 있을 때 조카 1명에게 줄 수 있는 과자 최대 길이 출력
    - 반드시 같은 길이의 과자를 주어야함

<변수 범위>
 1. 1초 / 256MB
 2. 조카의 수 M 1<=M<=1,000,000
 3. 과자의 수 1<=N<=1,000,000
 4. 과자의 길이 1,000,000,000 이하의 자연수

<프로그램 진행>
 1. f(length) = maxNum   ex) f(5) = 6명
 2. length 증가 -> maxNum 같거나 감소

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q16401 {
    static int M,N;
    static int maxLength =0;
    static int[] snacks;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        snacks = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            snacks[i] = Integer.parseInt(st.nextToken());
            maxLength = Math.max(maxLength, snacks[i]);
        }

        int ans = getMaxLength(M);
        sb.append(ans==-1 ? 0 : ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int getMaxLength(int key) {
        int lo = 0;
        int hi = maxLength+1;
        int mid;

        while(lo<hi) {
            mid = (lo+hi)/2;
            if (mid==0) return -1;
            if(getMaxChild(mid)>=key) lo = mid+1;
            else hi = mid;
        }
        return lo-1;
    }

    public static int getMaxChild(int length) {
        int child = 0;
        for (int i=0;i<N;i++) child+=snacks[i]/length;
        return child;
    }
}
