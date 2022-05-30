// 17245번 서버실 (S2) [이분탐색]
/*
<문제 정보>
 1. 서버실 각각의 칸에 컴퓨터들이 쌓여있을 때 절반 이상이 켜지려면 몇분이 지나야하는지 출력

<변수 범위>
 1. 1초 / 256MB
 2. 1<=N<=1,000
 3. 한칸에는 최대 10,000,000대 까지 쌓을 수 있다.

<프로그램 진행>
 1. f(height) = computerNum
    - height 증가 -> computerNum 증가
    - 절반이 켜지는 시간이 연속적으로 있다면 그중 최솟값 -> LowerBound

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q17245 {
    static int N;
    static long totalCom = 0;
    static int[][] server;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        server = new int[N][N];
        StringTokenizer st;
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++) {
                server[i][j] = Integer.parseInt(st.nextToken());
                totalCom+=server[i][j];
            }
        }
        long half = totalCom%2==0 ? totalCom/2 : totalCom/2+1;
        sb.append(BS_LowerBound(half));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int BS_LowerBound(long key) {
        int lo = 0;
        int hi = 10000001;
        int mid;

        while(lo<hi) {
            mid = (lo+hi)/2;
            if (computer(mid)<key) lo = mid+1;
            else hi = mid;
        }
        return lo;
    }

    public static long computer(int height) {
        long com = 0;
        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) com += Math.min(server[i][j], height);
        }
        return com;
    }
}
