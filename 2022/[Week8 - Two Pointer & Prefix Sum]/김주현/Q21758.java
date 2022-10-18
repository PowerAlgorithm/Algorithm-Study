// 21758번 꿀따기 (S1) [누적합][그리디]
/*
<문제 정보>
 1. 장소들의 꿀의 양정보가 주어질때 두 벌의 시작지점과 벌통의 지점을 적절히 정해서 최대 꿀의 양 출력

<변수 범위>
 1. 1초 / 512MB
 2. 3<=N<=100,000
 3. 각 장소의 꿀의 양은 10,000 이하의 자연수

<프로그램 진행>
 1. 1 ~ N 지점에서 꿀벌의 위치를 x,y 벌통을 a라고하면
    -> 꿀의 양 : sum(x+-1,a)+sum(y+-1,a)
 2. 첫번째 지점이나 마지막 지점은 그 곳에 꿀통이 있지 않은 이상 먹지 못함
 3. + 가운데도 항상 고려해야함.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q21758 {
    static int N;
    static int[] honey;
    static int[] sums;
    static int maxHoney = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        honey = new int[N+1];
        sums = new int[N+1];
        int max = 0;
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) {
            honey[i] = Integer.parseInt(st.nextToken());
            if (i!=1 && i!=N) max = Math.max(max, honey[i]);
            sums[i] = sums[i-1] + honey[i];
        }

        int minus=Integer.MAX_VALUE;
        int beeIdx=0;

        // 1. 오른쪽 끝에 꿀통이 있을 때
        for (int i=2;i<N;i++) {
            if (honey[i] + sums[i]-sums[2] < minus) {
                minus = honey[i] + sums[i]-sums[2];
                beeIdx = i;
            }
        }
        maxHoney = sums[N]-sums[1] + sums[N]-sums[beeIdx] - honey[beeIdx];

        // 2. 왼쪽 끝에 꿀통이 있을 때
        minus=Integer.MAX_VALUE;
        for (int i=N-1;i>1;i--) {
            if (honey[i] + sums[N-1]-sums[i-1] < minus) {
                minus = honey[i] + sums[N-1]-sums[i-1];
                beeIdx = i;
            }
        }
        maxHoney = Math.max(maxHoney, sums[N-1] + sums[beeIdx-1] - honey[beeIdx]);

        // 3. 두 벌이 양 끝에서 출발할 때
        maxHoney = Math.max(maxHoney, sums[N-1] - sums[1] + max);

        sb.append(maxHoney);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
