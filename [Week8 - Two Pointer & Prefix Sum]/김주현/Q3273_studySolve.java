// 3273번 두 수의 합 (S3) [Two Pointer]
/*
<문제 정보>
 1. 서로다른 양의 정수 수열에서 a_i + a_j = x 만족하는 (a_i,a_j) 쌍의 수 출력

<변수 범위>
 1. 1초 / 128MB
 2. 수열의 값 1이상 1,000,000 이하
 3. 1<=x<=2,000,000

<프로그램 진행>
 1.

<필요 함수>
 1.

 */

// sum==x 일때도 포인터 변화를 주어야 함

import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Q3273_studySolve {
    static int N, x;
    static int[] arr;
    static int numOfPair = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) arr[i] = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(br.readLine());
        Arrays.sort(arr);
        findPair();
        sb.append(numOfPair);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void findPair() {
        int left = 0;
        int right = N-1;
        int sum=0;
        while (left<right) {
            sum = arr[left] + arr[right];
//            System.out.printf("left : %d, right : %d, sum : %d\n",left,right,sum);
            if (sum==x) {
                numOfPair++;
                left++;
            }
            else if (sum<x) left++;
            else right--;
        }
    }
}

















