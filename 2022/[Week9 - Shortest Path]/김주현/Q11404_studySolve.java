// 11404번 플로이드 (G4) [최단경로 - 플로이드워셜]
/*
<문제 정보>
 1. A -> B 최소비용 출력 (모두)

<변수 범위>
 1. 1초 / 256MB
 2. 도시의 개수 n 2<=n<=100
 3. 버스 노선 개수 m 1<=m<=100,000
 4. 비용 c 100,000이하 자연수

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q11404_studySolve {
    static int N,M;
    static int[][] g;

    static final int INF = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        g = new int[N+1][N+1];
        initSet();
        int s, e, c;
        while(M-- >0) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            g[s][e] = Math.min(g[s][e], c);
        }
        FloydWarshall();
        for (int i=1;i<=N;i++) {
            for (int j=1;j<=N;j++) sb.append(g[i][j]==INF ? 0 : g[i][j]).append(" ");
            sb.append("\n");
        }

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
            for (int j=1;j<=N;j++) g[i][j] = INF;
        }
        for (int i=1;i<=N;i++) {
            g[i][i] = 0;
        }
    }
}















