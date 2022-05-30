// 1654번 랜선자르기 (S3)
/*
<문제 정보>
 1. 랜선 K개로 N개를 만들 때 만들 수 있는 최대 랜선 길이 출력

<변수 범위>
 1. 2초 / 128MB
 2. 1<=K<=10,000
 3. 1<=N<=1,000,000
 4. K<=N
 5. 각 랜선의 길이는 2^31-1 이하의 자연수

<프로그램 진행>
 1. f(length) = maxNum
 2. length 증가 -> maxNum 같거나 감소
 3. length 증가 -> maxNum 같거나 증가
 4. maxNum이 N이상이 되는 경계 구하기 => BS_UpperBound

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q1654_studySolve {
    static int K, N;
    static int maxLine=0;
    static int[] lines;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        lines = new int[K];
        for (int i=0;i<K;i++) {
            lines[i] = Integer.parseInt(br.readLine());
            maxLine = Math.max(maxLine, lines[i]);
        }


        sb.append(getMaxLength(N));


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    public static long getMaxLength (int key) {
        long lo = 0;
        long hi = (long) maxLine+1;
        long mid;

        while(lo<hi) {
            mid = (lo+hi)/2;
//            System.out.printf("lo : %d, mid : %d, hi : %d\n",lo,mid,hi);
            if(getMaxLines(mid)>=key) lo = mid+1;
            else hi = mid;
        }
        return lo-1;
    }


    public static int getMaxLines(long length) {
        int line = 0;
        for (int i=0;i<K;i++) line+=lines[i]/length;
        return line;
    }
}

















