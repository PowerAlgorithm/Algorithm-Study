// 2631번 줄세우기 (G5)
/*
<문제 정보>
 1. 아이들을 번호순으로 줄을 세울 때 이동시키는 아이들의 최솟값

<변수 범위>
 1. 1초 / 128MB
 2. 2<=N<=200

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;

public class Q2631 {
    static int N;
    static int[] arr;
    static int maxLength = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        int key;
        int index;
        for (int i=0;i<N;i++) {
            key = Integer.parseInt(br.readLine());
            index = GetIndex(key);
            arr[index] = key;
            if (index == maxLength) maxLength++;
        }
        sb.append(N-maxLength);


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int GetIndex (int key) {
        int lo = 0;
        int hi = maxLength;
        int mid;

        while(lo<hi) {
            mid = (lo+hi)/2;
            if (key == arr[mid]) return mid;
            if (key > arr[mid]) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}













