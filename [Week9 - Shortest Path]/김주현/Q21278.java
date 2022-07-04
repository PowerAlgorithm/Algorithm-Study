// 21278번 호석이 두마리 치킨 (G5) [최단경로 - 플로이드 워셜]
/*
<문제 정보>
 1. 각 건물과의 왕복시간의 합이 최소가 되는 두개의 건물을 오름차순으로 출력 후 시간 출력
    - 그런 건물이 여러개라면 건물번호가 작은것을 출력

<변수 범위>
 1. 1초 / 512MB
 2. 2<=N<=100


<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Q21278 {
    static int N,M;
    static int[][] g;

    static final int INF = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        g = new int[N+1][N+1];
        initSet();
        int v1, v2;
        while(M-- >0) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            g[v1][v2] = 1;
            g[v2][v1] = 1;
        }
        FloydWarshall();
        int minTime = INF;
        int building1=0;
        int building2=0;
        int time;
        for (int i=1;i<N;i++) {
            for (int j=i+1;j<=N;j++) {
                // i번, j번 빌딩에 지었을때의 시간
                time = 0;
                for (int building=1;building<=N;building++) {
                    time += Math.min(g[i][building],g[j][building])*2;
                }
                if (time<minTime) {
                    minTime = time;
                    building1 = i;
                    building2 = j;
                }
            }
        }
        sb.append(building1).append(" ").append(building2).append(" ").append(minTime);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void FloydWarshall() {
        for (int via=1;via<=N;via++) {
            for (int i=1;i<=N;i++) {
                for (int j=1;j<=N;j++) {
                    if (i!=j && i!=via && j!=via && g[i][via]!=INF && g[via][j]!=INF) {
                        g[i][j] = Math.min(g[i][j], g[i][via]+g[via][j]);
                    }
                }
            }
        }
    }

    public static void initSet() {
        for (int i=1;i<=N;i++) {
            Arrays.fill(g[i], INF);
            g[i][i] = 0;
        }
    }
}














