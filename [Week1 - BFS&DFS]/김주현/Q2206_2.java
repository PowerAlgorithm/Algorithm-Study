// 번
/*
<문제 정보>
 1.

<변수 범위>
 1.

<프로그램 진행>
 1.

<필요 함수>
 1.

 */

// 720ms 나왔는데 564ms 도 가능 (대신 메모리 쪼금 더 듬) 42237131	wjddmadl97

import java.io.*;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;

public class Q2206_2 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static boolean[][] visitedBySkill;
    static int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];
        visitedBySkill = new boolean[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            int j = 1;
            for (char c : br.readLine().toCharArray())
                map[i][j++] = c - '0';
        }

        /*for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++)
                System.out.printf("%d ", map[i][j]);
            System.out.println();
        }*/
        if (N==1 && M==1) bw.write(String.valueOf(1));
        else bw.write(String.valueOf(bfs()));
        bw.flush();
        bw.close();
        br.close();
    }

    public static int bfs() {
        Deque<Pair6> q = new ArrayDeque<>();
        q.add(new Pair6(1, 1));
        visited[1][1] = true;
        visitedBySkill[1][1] = true;
        Pair6 now;
        int nextX, nextY;
        boolean inRange;
        int qsize;
        int dis = 0;
        boolean findAns = false;

        while (!q.isEmpty()) {
            if (findAns) break;
            qsize = q.size();

            for (int i = 1; i <= qsize; i++) {
                if (findAns) break;
                now = q.poll();

                for (int[] d : dir) {
                    nextX = now.x + d[0];
                    nextY = now.y + d[1];
                    inRange = nextX >= 1 && nextY >= 1 && nextX <= N && nextY <= M;

                    if (nextX==N && nextY==M) {
                        findAns = true;
                        break;
                    }

                    if (inRange) {
                        if (now.skill) {
                            if (map[nextX][nextY] == 0 && !visited[nextX][nextY]) {
                                visited[nextX][nextY] = true;
                                visitedBySkill[nextX][nextY] = true;
                                q.add(new Pair6(nextX, nextY));
                            }
                            if (map[nextX][nextY]==1 && !visitedBySkill[nextX][nextY]) {
                                visitedBySkill[nextX][nextY] = true;
                                q.add(new Pair6(nextX, nextY, false));
                            }
                        }
                        else {
                            if (map[nextX][nextY] == 0 && !visitedBySkill[nextX][nextY]) {
                                visitedBySkill[nextX][nextY] = true;
                                q.add(new Pair6(nextX, nextY, false));
                            }
                        }
                    }
                }

            }
            dis++;
        }
        return findAns ? dis+1 : -1;
    }
}

class Pair6 {
    int x, y;
    boolean skill;

    public Pair6(int x, int y) {
        this.x = x;
        this.y = y;
        skill = true;
    }

    public Pair6(int x, int y, boolean skill) {
        this.x = x;
        this.y = y;
        this.skill = skill;
    }
}
