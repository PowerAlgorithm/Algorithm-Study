// 18404번 현명한 나이트 (S1) [dfs&bfs]
/*
<문제 정보>
 1. NxN 체스판에서 나이트가 상대편 말을 잡기 위한 최소 이동 수 차례대로 출력

<변수 범위>
 1. 1초 / 256MB
 2. 1<=N<=500
 3. 상대편 말들의 개수 1<=M<=1,000


<프로그램 진행>
 1.

<필요 함수>
 1.

 */

// 맵 자체에 이동거리를 기록해서 상대 편 말의 위치에 해당하는 이동거리를 따오는 풀이도 가능

import java.io.*;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;

public class Q18404 {
    static int N;
    static int[][] map;
//    static boolean[][] visited;
    static int[] ans;
    static int[][] dir = {{2,1},{1,2},{-1,2},{-2,1},{1,-2},{2,-1},{-1,-2},{-2,-1}};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
//        visited = new boolean[N+1][N+1];
        ans = new int[M+1];
        st = new StringTokenizer(br.readLine());
        int knight_x, knight_y;
        knight_x = Integer.parseInt(st.nextToken());
        knight_y = Integer.parseInt(st.nextToken());
        int opp_x, opp_y;
        for (int i=1;i<=M;i++) {
            st = new StringTokenizer(br.readLine());
            opp_x = Integer.parseInt(st.nextToken());
            opp_y = Integer.parseInt(st.nextToken());
            map[opp_x][opp_y]=i;
        }

        bfs(knight_x, knight_y, M);

        for (int i=1;i<=M;i++) sb.append(ans[i]).append(" ");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void bfs(int kx, int ky, int opp) {
        Deque<Pair4> q = new ArrayDeque<>();
        q.add(new Pair4(kx, ky));
        map[kx][ky]=-1;
        Pair4 now;
        int nextX, nextY;
        boolean inRange;
        int size;
        int moves=0;
        boolean findAns = false;

        while(!q.isEmpty()) {
            if(findAns) break;
            size = q.size();

            for (int i=0;i<size;i++) {
                if(findAns) break;
                now = q.poll();

                for (int[] d : dir) {
                    nextX = now.x + d[0];
                    nextY = now.y + d[1];
                    inRange = nextX>=1 && nextY>=1 && nextX<=N && nextY<=N;
                    if(inRange && map[nextX][nextY]!=-1) {
                        if(map[nextX][nextY]>0) {
                            ans[map[nextX][nextY]] = moves+1;
                            opp--;
                        }
                        map[nextX][nextY] = -1;
                        if (opp==0) {
                            findAns = true;
                            break;
                        }
                        q.add(new Pair4(nextX, nextY));
                    }
                }
            }
            moves++;
        }
    }
}

class Pair4 {
    int x,y;

    public Pair4 (int x, int y) {
        this.x = x;
        this.y = y;
    }
}















