// 2665번 미로 만들기 (G4)
/*
<문제 정보>
 1. 주어진 미로(nXn)에서 시작에서 끝으로 갈 수 있게 할 때 바꿔야하는 검은방 개수의 최솟값 출력

<변수 범위>
 1. 1초 / 128MB
 2. 1<=n<=50
 3. 0 : 검은 방, 1 : 흰 방

<프로그램 진행>
 1. N^2 X N^2
 2. 처음에 섬의 개수를 알아내고, 각각의 섬에 번호 표시를 해주어야함
 3. 전체 맵을 돌면서 각 섬에서 다른 섬까지의 최단거리를 bfs로 찾아낸다.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Q2665_2 {
    static int N;
    static boolean[][] map;
    static boolean[][] visited;
    static int removedBlack = 0;
    static boolean findAns = false;

    static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];
        visited = new boolean[N][N];
        for (int i=0;i<N;i++) {
            int j=0;
            for (char c : br.readLine().toCharArray()) map[i][j++] = c=='1';
        }
        searchSP();
//        System.out.printf("끝방 : %b\n",visited[N-1][N-1]);
        sb.append(removedBlack);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void searchSP() {
        Deque<Pair18> black = new ArrayDeque<>();
        searchAdjWhite(black,0,0);
//        for (Pair18 p : black) System.out.printf("%d %d\n",p.x,p.y);
        if(findAns) return;
        Pair18 now;
        int nextX, nextY;
        boolean inRange;
        int size;

        while(!black.isEmpty()) {
            removedBlack++;
            /*if (removedBlack==3) {
                for (Pair18 p : black) System.out.printf("%d %d\n",p.x,p.y);
            }*/
            size = black.size();

            for (int i=1;i<=size;i++) {
                now = black.poll();

                for (int[] d : dir) {
                    nextX = now.x + d[0];
                    nextY = now.y + d[1];
                    inRange = nextX>=0 && nextY>=0 && nextX<N && nextY<N;
                    if (inRange && !visited[nextX][nextY]) {
                        if (map[nextX][nextY]) {
                            if (nextX==N-1 && nextY==N-1) return;
                            searchAdjWhite(black, nextX, nextY);
                            if(findAns) return;
                        }
                        else {
                            visited[nextX][nextY] = true;
                            black.add(new Pair18(nextX,nextY));
                        }
                    }
                }
            }
        }

    }

    public static void searchAdjWhite(Deque<Pair18> black, int x, int y) {
        Deque<Pair18> q = new ArrayDeque<>();
        q.add(new Pair18(x, y));
        visited[x][y] = true;
        Pair18 now;
        int nextX, nextY;
        boolean inRange;

        while(!q.isEmpty()) {
            now = q.poll();

            for (int[] d : dir) {
                nextX = now.x + d[0];
                nextY = now.y + d[1];
                inRange = nextX>=0 && nextY>=0 && nextX<N && nextY<N;
                if (inRange && !visited[nextX][nextY]) {
                    if (!map[nextX][nextY]) black.add(new Pair18(nextX, nextY));
                    else {
                        if (nextX==N-1 && nextY==N-1) {
                            findAns = true;
//                            System.out.printf("endNow : %d %d\n",now.x,now.y);
                            return;
                        }
                        q.add(new Pair18(nextX,nextY));
                    }
                    visited[nextX][nextY] = true;
                }
            }
        }
    }

    /*public static void printMap() {
        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) System.out.printf("%d ",map[i][j] ? 1 : 0);
            System.out.println();
        }
    }*/
}

