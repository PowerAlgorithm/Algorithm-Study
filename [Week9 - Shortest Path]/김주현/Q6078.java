// 6078번 레이저 통신 (G3) [bfs&dfs]
/*
<문제 정보>
 1. 주어진 두 곳을 레이저로 연결하기 위한 거울의 최소 개수 출력

<변수 범위>
 1. 1초 / 128MB
 2. WxH map 1<=W,H<=100

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;

public class Q6078 {
    static int W, H;
    static int[][] map;
    static int mirrors = 0;
    static int laserX1, laserY1, laserX2, laserY2;

    static int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};
    static final int EMPTY = -1;
    static final int WALL = -2;
    static final int LASER_POINT = -3;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        boolean laser = true;
        for (int i=0;i<H;i++) {
            int j=0;
            for (char c : br.readLine().toCharArray()) {
                switch(c) {
                    case '.':
                        map[i][j] = EMPTY;
                        break;
                    case '*':
                        map[i][j] = WALL;
                        break;
                    case 'C':
                        map[i][j] = LASER_POINT;
                }

                if (map[i][j] == LASER_POINT) {
                    if (laser) {
                        laserX1 = i;
                        laserY1 = j;
                        laser = false;
                    }
                    else {
                        laserX2 = i;
                        laserY2 = j;
                    }
                }
                j++;
            }
        }

        bfs();
        printMap();
        sb.append(mirrors);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void bfs() {
        Deque<Pair19> q = new ArrayDeque<>();
        Pair19 now;
        int nextX, nextY;
        boolean inRange;
        int size;
        for (int d=0;d<4;d++) {
            nextX = laserX1 + dir[d][0];
            nextY = laserY1 + dir[d][1];
            inRange = nextX>=0 && nextY>=0 && nextX<H && nextY<W;
            while(inRange && map[nextX][nextY]!=WALL) {
                if (nextX==laserX2 && nextY==laserY2) return;
                q.add(new Pair19(nextX, nextY, d));
                map[nextX][nextY] = mirrors;
                nextX += dir[d][0];
                nextY += dir[d][1];
                inRange = nextX>=0 && nextY>=0 && nextX<H && nextY<W;
            }
        }

        int direction, dx, dy;

        while(!q.isEmpty()) {
            mirrors++;
            size = q.size();

            for (int i=1;i<=size;i++) {
                now = q.poll();

                // 왼쪽 또는 오른쪽으로 꺾기
                for (int turn=0;turn<2;turn++) {
                    if (turn==0) direction = now.direction==0 ? 3 : now.direction-1;
                    else direction = now.direction==3 ? 0 : now.direction+1;
                    dx = dir[direction][0];
                    dy = dir[direction][1];
                    nextX = now.x + dx;
                    nextY = now.y + dy;
                    inRange = nextX>=0 && nextY>=0 && nextX<H && nextY<W;
                    while(inRange && map[nextX][nextY]!=WALL) {
                        if (map[nextX][nextY]<0) {
                            if (nextX==laserX2 && nextY==laserY2) return;
                            q.add(new Pair19(nextX, nextY, direction));
                            map[nextX][nextY] = mirrors;
                        }
                        nextX += dx;
                        nextY += dy;
                        inRange = nextX>=0 && nextY>=0 && nextX<H && nextY<W;
                    }
                }
            }
        }
    }

    public static void printMap() {
        for (int i=0;i<H;i++) {
            for (int j=0;j<W;j++) System.out.printf("%d ",map[i][j]);
            System.out.println();
        }
    }
}

class Pair19 {
    int x, y, direction;

    public Pair19 (int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
}

