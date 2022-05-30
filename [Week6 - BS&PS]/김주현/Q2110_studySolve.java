// 2110번 공유기 설치 (G5) [매개변수 탐색]
/*
<문제 정보>
 1. 수직선위의 N개의 집에 공유기 C개를 설치할 때 공유기 사이의 거리의 최솟값의 최댓값 출력

<변수 범위>
 1. 2초 / 128MB
 2. 집의 개수 2<=N<=200,000
 3. 공유기 개수 2<=C<=N
 4. 집의 좌표 0<=x_i<=1,000,000,000

<프로그램 진행>
 1. f(최소 거리) = 공유기 C개 설치 가능?
 2. 일정 최소 거리가 넘어서 부터는 C개 설치 불가
 3. 설치 개수가 C개가 되는 최대의 최소 거리 -> BS_UpperBound
 ** overflow 가능성 없음

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Q2110_studySolve {
    static int N, C;
    static int[] houses;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        houses = new int[N];
        for (int i=0;i<N;i++) houses[i] = Integer.parseInt(br.readLine());
        Arrays.sort(houses);

        sb.append(BS_upperBound(C));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int BS_upperBound(int key) {
        int lo = 1;
        int hi = houses[N-1]-houses[0]+1;
        int mid;

        while(lo<hi) {
            mid = (lo+hi)/2;
            if (is_router_possible(mid, key)) lo = mid+1;
            else hi = mid;
        }
        return lo-1;
    }

    public static boolean is_router_possible (int minDis, int numOfRouter) {
        int routers = 1;
        int priorRouter = houses[0];

        for (int i=1;i<N;i++) {
            if(houses[i]-priorRouter>=minDis) {
                priorRouter = houses[i];
                routers++;
                if (routers==numOfRouter) return true;
            }
        }
        return false;
    }
}
