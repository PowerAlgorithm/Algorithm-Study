// 2470번 두 용액 (G5) [투포인터]
/*
<문제 정보>
 1. 두 개의 서로 다른 용액을 혼합하여 특성값이 0에 가까운 용액을 만들어내는 두 용액 출력

<변수 범위>
 1. 1초 / 128MB
 2. 2<=N<=100,000
 3. 용액 특성값 절댓값이 1,000,000,000 이하인 정수
 4. 용액 특성값은 모두 다름

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Q2470_studySolve {
    static int N;
    static int[] arr;
    static int absSum = Integer.MAX_VALUE;
    static int lowIdx, highIdx;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        /*for (int n : arr) System.out.printf("%d ",n);
        System.out.println();*/
        if (arr[0]>=0) sb.append(arr[0]).append(" ").append(arr[1]);
        else if (arr[N-1]<=0) sb.append(arr[N-2]).append(" ").append(arr[N-1]);
        else {
            getAns();
            sb.append(arr[lowIdx]).append(" ").append(arr[highIdx]);
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void getAns() {
        int left = 0;
        int right = N-1;
        int sum;

        while (left<right) {
            sum = arr[left] + arr[right];
//            System.out.printf("left : %d, right : %d, sum : %d\n",arr[left], arr[right], sum);
            if (Math.abs(sum)<absSum) {
//                System.out.println("IN");
                lowIdx = left;
                highIdx = right;
                if (sum==0) break;
                absSum = sum>=0 ? sum : -sum;
            }
            if (sum>0) right--;
            else left++;
        }
    }
}



















