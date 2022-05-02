// 3197번 백조의 호수 (P5) [UnionFind]
/*
<문제 정보>
 1. 빙판이 녹아서 두 백조가 만날때까지 걸리는 날 출력

<변수 범위>
 1. 1초 / 256MB
 2. 1<=R,C<=1,500

<프로그램 진행>
 1. 2차원 맵 대상 UnionFind
 2. 물과 맞닿아 있는 빙산 큐에 저장 (bfs)
 3. melt 함수
    - 큐에 저장 되어 있는 빙산들을 녹이고 주변의 물들과 union
 4. bfs 한번 돌때마다 두 백조의 루트값을 체크해서 같다면 일수를 출력하고 끝

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;


public class Q3197 {
    static int N, M;
    static int[][] map;
    static int[][] parent;
    static Deque<Pair10> q = new ArrayDeque<>();
    static boolean[][] visited;
    static Pair10[] swans;
    static int days = 0;
    static int[][] dir = {{-1,0},{0,-1},{0,1},{1,0}};
    static final int WATER = '.';
    static final int SWAN = 'L';
    static final int ICE = 'X';


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        parent = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];
        for (int i=1;i<=N;i++) {
            int j = 1;
            for (char c : br.readLine().toCharArray()) map[i][j++] = c;
        }

        /*for (int i=1;i<=N;i++) {
            for (int j=1; j<=M;j++) System.out.printf("%d ",map[i][j]);
            System.out.println();
        }*/

        parentInitSet();
        /*for (int i=1;i<=N;i++) {
            for (int j=1; j<=M;j++) System.out.printf("%d ",parent[i][j]);
            System.out.println();
        }*/

        // 1. 맵을 한바퀴 돌면서 물 공간을 union 하고, 물과 맞닿아 있는 빙산을 큐에 넣는다. + 백조 위치 저장
        swans = new Pair10[2];
        int nextX, nextY;
        boolean inRange;
        int s = 0;
        for (int i=1;i<=N;i++) {
            for (int j=1;j<=M;j++) {
                if (map[i][j]!=ICE) {
                    if (map[i][j]==SWAN) swans[s++] = new Pair10(i, j);
                    for (int d=0;d<2;d++) {
                        nextX = i + dir[d][0];
                        nextY = j + dir[d][1];
                        inRange = nextX>=1 && nextY>=1 && nextX<=N && nextY<=M;
                        if (inRange && map[nextX][nextY]!=ICE) union(i, j, nextX, nextY);
                    }
                }
                else {
                    for (int d=0;d<4;d++) {
                        nextX = i + dir[d][0];
                        nextY = j + dir[d][1];
                        inRange = nextX>=1 && nextY>=1 && nextX<=N && nextY<=M;
                        if (inRange && map[nextX][nextY]!=ICE) {
                            q.add(new Pair10(i, j));
                            break;
                        }
                    }
                }
            }
        }
        /*for (int i=1;i<=N;i++) {
            for (int j=1; j<=M;j++) System.out.printf("%d ",parent[i][j]);
            System.out.println();
        }
        System.out.printf("녹을 예정인 ICE : %d\n",q.size());
        System.out.printf("백조1 root : %d, 백조2 root : %d\n",find(swans[0].x,swans[0].y), find(swans[1].x,swans[1].y));*/

        if (find(swans[0].x,swans[0].y) == find(swans[1].x,swans[1].y)) sb.append(0);
        else {
            melt();
            sb.append(days);
        }






       bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // melt
    // 1. 큐에 담긴 빙산들을 녹인다.
    // 2. 주변의 물과 union
    // 3. 백조 둘의 루트가 같은지 비교
    // 4. 방금 녹은 빙산 주변의 빙산을 큐에 추가
    public static void melt() {
        Pair10 now;
        int nextX, nextY;
        boolean inRange;
        int size;

        while(!q.isEmpty()) {
            days++;
            /*System.out.printf("%d일차\n",days);
            System.out.printf("녹을 예정인 빙산 : %d개\n",q.size());*/
            // 1. 큐에 담긴 빙산들을 녹인다.
            // 2. 주변의 물과 union
            for (Pair10 ice : q) {
//                System.out.printf("%d,%d 녹임\n",ice.x, ice.y);
                map[ice.x][ice.y] = WATER;
                for (int[] d : dir) {
                    nextX = ice.x + d[0];
                    nextY = ice.y + d[1];
                    inRange = nextX>=1 && nextY>=1 && nextX<=N && nextY<=M;
                    if (inRange && map[nextX][nextY]!=ICE) union(ice.x, ice.y, nextX, nextY);
                }
            }
            /*System.out.println("map");
            for (int i=1;i<=N;i++) {
                for (int j=1; j<=M;j++) System.out.printf("%d ",map[i][j]);
                System.out.println();
            }*/
            /*System.out.println("parent");
            for (int i=1;i<=N;i++) {
                for (int j=1; j<=M;j++) System.out.printf("%d ",parent[i][j]);
                System.out.println();
            }
            System.out.printf("빙산이 녹은 후 백조 둘의 루트 : %d, %d\n",find(swans[0].x,swans[0].y),find(swans[1].x,swans[1].y));*/

            // 3. 백조 둘의 루트가 같은지 비교
            if (find(swans[0].x,swans[0].y) == find(swans[1].x,swans[1].y)) break;

            // 4. 방금 녹은 빙산 주변의 빙산을 큐에 추가
            size = q.size();
            while(size-- >0) {
                now = q.poll();
                for (int[] d : dir) {
                    nextX = now.x + d[0];
                    nextY = now.y + d[1];
                    inRange = nextX>=1 && nextY>=1 && nextX<=N && nextY<=M;
                    if (inRange && map[nextX][nextY]==ICE && !visited[nextX][nextY]) {
                        visited[nextX][nextY] = true;
                        q.add(new Pair10(nextX, nextY));
                    }
                }
            }

        }
    }

    /*public static int PairToRoot (Pair10 A) {
        return (A.x-1)*M + A.y;
    }*/

    public static Pair10 RootToPair (int root) {
        int x = root%M==0 ? root/M : root/M+1;
        int y = root%M==0 ? M : root%M;
        return new Pair10(x, y);
    }

    public static void parentInitSet() {
        for (int i=1;i<=N;i++) {
            for (int j=1;j<=M;j++) parent[i][j] = (i-1)*M+j;
        }
    }

    public static int find (int x, int y) {
        if (parent[x][y]==(x-1)*M+y) return (x-1)*M+y;
        return parent[x][y] = find(RootToPair(parent[x][y]).x,RootToPair(parent[x][y]).y);
    }

    public static void union (int Ax, int Ay, int Bx, int By) {
        int Ar = find(Ax, Ay);
        int Br = find(Bx, By);

        if (Ar==Br) return;
        if (Ar<Br) parent[RootToPair(Br).x][RootToPair(Br).y] = Ar;
        else parent[RootToPair(Ar).x][RootToPair(Ar).y] = Br;
    }
}

class Pair10 {
    int x, y;

    public Pair10 (int x, int y){
        this.x = x;
        this.y = y;
    }
}























