// 2573번 빙산 (G4) [bfs&&dfs]
/*
<문제 정보>
 1. 한 덩어리의 빙산이 주어질 때, 이 빙산이 두 덩어리 이상으로 분리되는 최소의 시간 구하기
    - 1년동안 인접한 바다의 칸 개수만큼 녹음

<변수 범위>
 1. 1초 / 256MB

<프로그램 진행>
 1. 3<=N,M<=300
 2. 높이 0이상 10이하
 3. 빙산이 처음에 차지하는 칸의 개수는 10,000 개 이하

<필요 함수>
 1.

 */

// 73784KB	756ms (시간 절반 정도 줄일 수 있음)

import java.io.*;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Q2573 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[][] change;
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        change = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];
        int years=0;
        for (int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1;j<=M;j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        /*for (int i=1;i<=N;i++) {
            for (int j=1;j<=M;j++) sb.append(map[i][j]).append(" ");
            sb.append("\n");
        }*/

        int iceburgNum;
        while (true) {
            years++;
            // 빙산 녹음
            for (int i=1;i<=N;i++) {
                for (int j=1;j<=M;j++) {
                    if (map[i][j]>0) getMeltInfo(i, j);
                }
            }
            melt();

            // 빙산 덩어리 개수 체크
            iceburgNum=0;
            for (int i=1;i<=N;i++) {
                for (int j=1;j<=M;j++) {
                    if (map[i][j]>0 && !visited[i][j]) {
                        iceburgNum++;
                        bfs(i, j);
                    }
                }
            }

            /*
            System.out.printf("%d년후 빙산\n",years);
            for (int i=1;i<=N;i++) {
            for (int j=1;j<=M;j++) System.out.printf("%d ",map[i][j]);
            System.out.println();
            }
            System.out.printf("빙산 덩어리 : %d개\n",iceburgNum);
             */

            // 빙산 덩어리 개수가 1보다 크거나 없다면 반복문 탈출
            if(iceburgNum>1 || iceburgNum==0) break;
            
            // 방문 정보 초기화
            for (int i=1;i<=N;i++) Arrays.fill(visited[i], false);
        }

        sb.append(iceburgNum==0 ? 0 : years);


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    public static void getMeltInfo (int x, int y) {
        int nextX, nextY;
        boolean inRange;
        for (int[] d : dir) {
            nextX = x + d[0];
            nextY = y + d[1];
            inRange = nextX>=1 && nextY>=1 && nextX<=N && nextY<=M;
            if(inRange && map[nextX][nextY]==0) {
                change[x][y]--;
                if(map[x][y]+change[x][y]==0) break;
            }
        }
    }

    public static void melt() {
        for (int i=1;i<=N;i++) {
            for (int j=1;j<=M;j++) {
                if(change[i][j]<0) {
                    map[i][j]+=change[i][j];
                    change[i][j]=0; // 다음 년도 진행을 위한 초기화
                }
            }
        }
    }

    public static void bfs (int x, int y) {
        Deque<Pair5> q = new ArrayDeque<>();
        q.add(new Pair5(x, y));
        visited[x][y] = true;
        Pair5 now;
        int nextX, nextY;
        boolean inRange;

        while(!q.isEmpty()) {
            now = q.pollLast();

            for (int[] d : dir) {
                nextX = now.x + d[0];
                nextY = now.y + d[1];
                inRange = nextX>=1 && nextY>=1 && nextX<=N && nextY<=M;
                if(inRange && !visited[nextX][nextY] && map[nextX][nextY]>0) {
                    visited[nextX][nextY] = true;
                    q.add(new Pair5(nextX, nextY));
                }
            }
        }
    }
}

class Pair5 {
    int x, y;

    public Pair5 (int x, int y) {
        this.x = x;
        this.y = y;
    }
}

















