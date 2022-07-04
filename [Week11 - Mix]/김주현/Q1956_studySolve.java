// 1956번 운동 (G4) [최단경로 - 플로이드워셜]
/*
<문제 정보>
 1. 도로의 길이의 합이 가장 작은 사이클을 출력

<변수 범위>
 1. 2초 / 192MB
 2. 마을 개수 2<=V<=400
 3. 단방향 가중
 4. 각 도로의 거리 10,000 이하 자연수

<프로그램 진행>
 1. FloydWarshall 400*400*400 = 64,000,000

<필요 함수>
 1.

 */

/*
    1. Floyd-Warshall 을 이용해 사이클을 찾는다.
    2. dist[i][i] 가 모두 0인 경우 사이클이 없다고 판단
 */


import java.io.*;
import java.util.StringTokenizer;

public class Q1956_studySolve {
    static int V,E;
    static int[][] dist;

    static final int INF = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // ******************** 입력 & 초기화 ********************
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        dist = new int[V+1][V+1];
        initDist(); // 거리 입력을 받기 전에 INF로 초기화

        int start_in, end_in, dist_in;
        while(E-- >0) {
            st = new StringTokenizer(br.readLine());
            start_in = Integer.parseInt(st.nextToken());
            end_in = Integer.parseInt(st.nextToken());
            dist_in = Integer.parseInt(st.nextToken());
            dist[start_in][end_in] = dist_in;
        }

        // ******************** 메인 로직 ********************

        // 1. Floyd Warshall 함수를 통해 마을간 거리의 최솟값을 구한다.
        FloydWarshall();

        // 2. dist[i][i]를 순회하며 사이클의 최솟값을 구한다.
        int minCycle = INF;
        for (int i=1;i<=V;i++) minCycle = Math.min(minCycle, dist[i][i]);

        // ******************** 정답 출력 ********************

        // 만약 minCycle 값이 INF 라면 사이클이 없는 것 이므로 -1을 출력한다.
        sb.append(minCycle==INF ? -1 : minCycle);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // [초기화] 각 마을 간의 거리 INF(무한대)로 초기화
    public static void initDist() {
        for (int i=1;i<=V;i++) {
            for (int j=1;j<=V;j++) {
                dist[i][j] = INF;
            }
        }
    }

    // Floyd Warshall 함수
    public static void FloydWarshall() {
        for (int via=1; via<=V; via++) {
            for (int i=1;i<=V;i++) {
                for (int j=1;j<=V;j++) {
                    if (i!=via && j!=via && dist[i][via]!=INF && dist[via][j]!=INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][via]+dist[via][j]);
                    }
                }
            }
        }
    }

}













