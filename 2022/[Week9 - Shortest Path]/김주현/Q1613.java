// 1613번 역사 (G3) [최단경로 - 플로이드 워셜]
/*
<문제 정보>
 1. 사실관계가 주어질 때 사건의 순서 유추
    - 앞 -> 뒤 : -1
    - 뒤 -> 앞 : 1
    - 모름 : 0

<변수 범위>
 1. 2초 / 128MB
 2. 사건 개수 n 400이하 자연수
 3. 사건의 전후 관계 수 k 50,000이하 자연수
 4. 전후 관계 알고 싶은 사건 수 s 50,000 이하 자연수

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q1613 {
    static int N, K;
    static int[][] g;

    static final int NO_INFO = 0;
    static final int RIGHT = -1;
    static final int WRONG = 1;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        g = new int[N+1][N+1];
        int s, e;
        while(K-- >0) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            g[s][e] = RIGHT;
            g[e][s] = WRONG;
        }
        FloydWarshall();
        int S = Integer.parseInt(br.readLine());
        while(S-- >0) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            sb.append(g[s][e]).append("\n");
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
                    if (i!=j && i!=via && j!=via && g[i][via]!=NO_INFO && g[via][j]!=NO_INFO && g[i][j]==NO_INFO) {
                        if (g[i][via]!=g[via][j]) continue;

                        if (g[i][via]==RIGHT) {
                            g[i][j] = RIGHT;
                            g[j][i] = WRONG;
                        }
                        else {
                            g[i][j] = WRONG;
                            g[j][i] = RIGHT;
                        }
                    }
                }
            }
        }
    }
}











