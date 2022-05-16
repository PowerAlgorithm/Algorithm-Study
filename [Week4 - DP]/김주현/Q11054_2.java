// 번
/*
<문제 정보>
 1.

<변수 범위>
 1.

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Q11054_2 {
    static int N;
    static int[] arr;
    static int[] frontLIS;
    static int[] backLIS;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        frontLIS = new int[N];
        backLIS = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
        int frontMaxLength=1;
        int backMaxLength=1;
        frontLIS[0] = arr[0];
        backLIS[0] = arr[N-1];

        int index;
        int[] bitonicArrayLength = new int[N];
        Arrays.fill(bitonicArrayLength, -1);

        bitonicArrayLength[0] += 1;
        bitonicArrayLength[N-1] += 1;
        for (int i=1;i<N;i++) {
            // 앞 조사
            index = FindUpperBound(frontLIS, arr[i],frontMaxLength);
            if (index==frontMaxLength) frontMaxLength++;
            frontLIS[index] = arr[i];
            bitonicArrayLength[i] += frontMaxLength;

            // 뒤 조사
            index = FindUpperBound(backLIS, arr[N-1-i],backMaxLength);
            if (index==backMaxLength) backMaxLength++;
            backLIS[index] = arr[N-1-i];
            bitonicArrayLength[N-1-i] += backMaxLength;
        }

        int maxBitonicArrayLength = -1;
        for (int i=0;i<N;i++) {
            maxBitonicArrayLength = Math.max(maxBitonicArrayLength, bitonicArrayLength[i]);
//            System.out.printf("%d ",bitonicArrayLength[i]);
        }
//        System.out.println();

        sb.append(maxBitonicArrayLength);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int FindUpperBound (int[] arr, int key, int maxLength) {
        int lo = 0;
        int hi = maxLength;
        int mid;

        while(lo<hi) {
            mid = (lo+hi)/2;
            if(key == arr[mid]) return mid;
            if(key>arr[mid]) lo = mid+1;
            else hi = mid;
        }
        return lo;
    }
}














