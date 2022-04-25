// 2583번 영역 구하기 (S1) [dfs&bfs]
/*
<문제 정보>
 1. MxN 내부에 직사각형 K개를 그릴 때 나머지 영역이 몇개로 나뉘어지고 나뉘어진 영역의 넓이 출력
 2. 왼쪽 아래 꼭짓점 좌표 (0,0) / 오른쪽 위 꼭짓점 (N,M)
 3. K개 직사각형들이 전체를 채우는 경우는 없음

<변수 범위>
 1. 1초 / 128MB
 2. M,N,K<=100 자연수

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public class Q2583 {
    static int M,N;
    static boolean[][] map;
    static boolean[][] visited;
    static ArrayList<Integer> squareAreas = new ArrayList<>();
    static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new boolean[M + 1][N + 1];
        visited = new boolean[M + 1][N + 1];
        int[] square = new int[4];

        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) square[i] = Integer.parseInt(st.nextToken());
            register(square);
        }

        /*for (int i=1;i<=M;i++) {
            for (int j=1;j<=N;j++) sb.append(map[i][j]).append(" ");
            sb.append("\n");
        }*/

        int squares = 0;
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if (!visited[i][j] && !map[i][j]) {
                    squares++;
                    bfs(i, j);
                }
            }
        }
        Collections.sort(squareAreas);
        sb.append(squares).append("\n");
        for (int area : squareAreas) sb.append(area).append(" ");


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void register(int[] square) {
        for (int i=square[1]+1;i<=square[3];i++) { // y좌표 : i, x좌표 : j
            for (int j=square[0]+1;j<=square[2];j++) map[i][j]=true;
        }
    }

    public static void dfs(int x, int y) {
        Deque<Pair3> stack = new ArrayDeque<>();
        stack.add(new Pair3(x, y));
        Pair3 now;
        int nextX, nextY;
        boolean inRange;
        int area=0;
        int maxStack=Integer.MIN_VALUE;

        while(!stack.isEmpty()) {
            now = stack.pollLast();
            if (visited[now.x][now.y]) continue;
            visited[now.x][now.y]=true;
            area++;

            for (int[] d : dir) {
                nextX = now.x + d[0];
                nextY = now.y + d[1];
                inRange = nextX>=1 && nextY>=1 && nextX<=M && nextY<=N;
                if(inRange && !visited[nextX][nextY] && !map[nextX][nextY]) {
                    stack.add(new Pair3(nextX, nextY));
                }
            }
            maxStack = Math.max(maxStack, stack.size());
        }
        System.out.printf("maxStack : %d\n",maxStack);
        squareAreas.add(area);
    }

    public static void bfs(int x, int y) {
        Deque<Pair3> q = new ArrayDeque<>();
        q.add(new Pair3(x, y));
        visited[x][y] = true;
        Pair3 now;
        int nextX, nextY;
        boolean inRange;
        int area=0;
        int maxQueue=Integer.MIN_VALUE;

        while(!q.isEmpty()) {
            now = q.poll();
            area++;

            for (int[] d : dir) {
                nextX = now.x + d[0];
                nextY = now.y + d[1];
                inRange = nextX>=1 && nextY>=1 && nextX<=M && nextY<=N;
                if(inRange && !visited[nextX][nextY] && !map[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    q.add(new Pair3(nextX, nextY));
                }
            }
            maxQueue = Math.max(maxQueue, q.size());
        }
        System.out.printf("maxQueue : %d\n",maxQueue);
        squareAreas.add(area);
    }
}

class Pair3 {
    int x,y;

    public Pair3 (int x, int y) {
        this.x = x;
        this.y = y;
    }
}














