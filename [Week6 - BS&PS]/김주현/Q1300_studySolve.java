// 1300번 K번째 수 (G2) [매개변수 탐색]
/*
<문제 정보>
 1. NxN 배열 A A[i][j] = i*j
    - 이 수들을 일차원배열에 넣고 오름차순 정렬했을 때 B[k] 출력
    - A,B 인덱스는 1부터 시작

<변수 범위>
 1. 2초 / 128MB
 2. N은 10^5보다 작거나 같은 자연수
 3. k는 min(10^9, N^2) 보다 작거나 같은 자연수

<프로그램 진행>
 1. f(num) = k (번째)
 2. num 증가 -> k 같거나 증가
 3. 같은 수가 있을 수 있기 때문에 원해는 k가 딱나오지 않을 수 있음.
    -> UpperBound

<필요 함수>
 1.

 */


import java.io.*;

public class Q1300_studySolve {
    static int N,K;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        /*for (int i=1;i<=N;i++) {
            for (int j=1;j<=N;j++) System.out.printf("%d ",i*j);
            System.out.println();
        }
        System.out.println();

        for (int i=1;i<=N*N;i++) System.out.printf("%d ",BS_UpperBound(i));
        System.out.println();*/
        sb.append(BS_UpperBound(K));


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static long BS_UpperBound(int key) {
        long lo = 1;
        long hi = (long) N*N+1;
        long mid;
        System.out.printf("lo : %d, hi : %d\n",lo, hi);
        while(lo<hi) {
            mid = (lo+hi)/2;
//            System.out.printf("lo : %d, hi : %d, mid : %d\n",lo,hi,mid);
            // flag==0 => mid 보다 작거나 같은 숫자의 개수가 K보다 모자람 => mid를 키워주어야 함.
            if(!isEnough(mid,key)) lo = mid+1;
            else hi = mid;
        }

        return hi;
    }

    // O(N)
    public static boolean isEnough (long num, int K) {
        long smallerNum = 0;
        for (int i=1;i<=N;i++) {
            smallerNum+=Math.min(num/i, N);
            if (smallerNum>=K) return true;
        }
//        System.out.printf("%d보다 작거나 같은 숫자 개수 : %d\n",num, smallerNum);
        return false;
    }
}















