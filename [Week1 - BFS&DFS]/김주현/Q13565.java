// 13565번 침투 (S2) [DFS&BFS]
/*
<문제 정보>
 1. 맨 위에 흰색 타일에 전류주었을 때 안쪽까지 모두 침투하면 YES 그렇지 못하면 NO

<변수 범위>
 1. 1초 / 512MB
 2. 2<=M,N<=1,000

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;

public class Q13565 {
    static int N,M;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M+1][N+1];
        visited = new boolean[M+1][N+1];
        for (int i=1;i<=M;i++) {
            int j=1;
            for (char c : br.readLine().toCharArray()) map[i][j++]=c-'0';
        }
        /*for (int i=1;i<=M;i++) {
            for (int j=1;j<=N;j++) sb.append(map[i][j]).append(" ");
            sb.append("\n");
        }*/
        sb.append(bfs() ? "YES" : "NO");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean bfs() {
        Deque<Pair2> q = new ArrayDeque<>();
        for (int i=1;i<=N;i++) {
            if(map[1][i]==0) {
                q.add(new Pair2(1,i));
                visited[1][i]=true;
            }
        }
        Pair2 now;
        int nextX, nextY;
        boolean inRange;
        boolean findAns=false;

        while(!q.isEmpty()) {
            if(findAns) break;
            now = q.poll();
//            System.out.printf("x : %d, y : %d\n",now.x, now.y);

            for(int[] d : dir) {
                nextX = now.x+d[0];
                nextY = now.y+d[1];
                inRange = nextX>=1 && nextY>=1 && nextX<=M && nextY<=N;
                if(inRange && !visited[nextX][nextY] && map[nextX][nextY]==0) {
                    if(nextX==M) {
                        findAns=true;
                        break;
                    }
                    visited[nextX][nextY]=true;
                    q.add(new Pair2(nextX, nextY));
                }
            }
        }
        return findAns;
    }
}

class Pair2 {
    int x;
    int y;

    public Pair2 (int x, int y) {
        this.x = x;
        this.y = y;
    }
}


