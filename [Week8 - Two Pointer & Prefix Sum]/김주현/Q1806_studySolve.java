// 1806번 부분합 (G4) [투포인터]
/*
<문제 정보>
 1. 10,000 이하 자연수로 이루어진 길이 N 수열의 연속된 부분합이 S이상 되는 것 중 최소 길이 출력

<변수 범위>
 1. 0.5초 / 128MB
 2. 10<=N<=100,000
 3. 0<S<=100,000,000
 4. 각 수열의 수 10,000 이하

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q1806_studySolve {
    static int N, S;
    static int[] arr;
    static int minLength = 100_000;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) arr[i] = Integer.parseInt(st.nextToken());
        getMinLength();
        sb.append(minLength==100_000 ? 0 : minLength);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    public static void getMinLength() {
        int left = 0;
        int right = 1;
        int sum = arr[left];
        boolean flag;
        while(true) {
//            System.out.printf("left : %d, right : %d, sum : %d\n",left,right,sum);
            if (sum >= S) {
                minLength = Math.min(minLength, right-left);
                flag = false;
            }
            else flag = true;

            if(flag) {
                if (right==N) break;
                sum+=arr[right++];
            }
            else sum-=arr[left++];
        }
    }
}















