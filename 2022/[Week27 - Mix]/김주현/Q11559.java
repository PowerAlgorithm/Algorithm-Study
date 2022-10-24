// 11559번 Puyo Puyo (G4) [구현]
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

import Necessary_Class.Pos;

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Q11559 {
    static int[][] map = new int[12][6];
    static boolean[][] visited = new boolean[12][6];

    static final int N = 12;
    static final int M = 6;
    static final int EMPTY = '.';
    static final int POPPED = '*';
    static final int RED = 'R';
    static final int YELLOW = 'Y';
    static final int GREEN = 'G';
    static final int BLUE = 'B';
    static final int PURPLE = 'P';

    static final int[][] DIR = {{1,0}, {0,1}, {-1,0}, {0,-1}};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // ******************** 입력 & 초기화 ********************
        for (int i=0; i<N; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j=0; j<M; j++) {
                map[i][j] = input[j];
            }
        }

        // ******************** 메인 로직 ********************

        int chainCount = -1;
        boolean popped = true;

        // 연쇄가 일어나는 동안 반복
        while (popped) {
            chainCount++;
            popped = false;
            initVisited();

            for (int i=0; i<N; i++) {
                for (int j=0; j<M; j++) {
                    if (visited[i][j] || map[i][j] == EMPTY) continue;
                    if (popPuyo(i, j)) {
                        popped = true;
                    }
                }
            }
            pushDown();
        }

        // ******************** 정답 출력 ********************

        sb.append(chainCount);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    /**
     * (x,y)에 연결되어 있는 뿌요를 터뜨리고 true 반환 만약 터뜨릴 수 없다면 false 반환
     * @param x
     * @param y
     * @return isPopped
     */
    public static boolean popPuyo(int x, int y) {
        ArrayList<Pos> popList = search(x, y);
        if (popList.size() < 4) return false;

        for (Pos pop : popList) {
            map[pop.x][pop.y] = POPPED;
        }
        return true;
    }

    public static void pushDown() {
        for (int col=0; col < M; col++) {
            pushColumnDown(col);
        }
    }

    public static void pushColumnDown(int col) {
        int idx = N-1;
        for (int i = N-1; i >= 0; i--) {
            if (map[i][col] == POPPED || map[i][col] == EMPTY) {
                continue;
            }
            map[idx][col] = map[i][col];
            idx--;
        }

        while (idx >= 0) {
            map[idx][col] = EMPTY;
            idx--;
        }
    }

    public static ArrayList<Pos> search (int x, int y) {
        final int COLOR = map[x][y];

        Deque<Pos> q = new ArrayDeque<>();
        q.add(new Pos(x, y));
        visited[x][y] = true;

        Pos now;
        int nextX, nextY;
        boolean inRange;
        ArrayList<Pos> popList = new ArrayList<>();
        popList.add(new Pos(x, y));

        while (!q.isEmpty()) {
            now = q.poll();

            for (int[] d: DIR) {
                nextX = now.x + d[0];
                nextY = now.y + d[1];
                inRange = nextX >= 0 && nextY >=0 && nextX < N && nextY < M;
                if (inRange && !visited[nextX][nextY] && map[nextX][nextY] == COLOR) {
                    popList.add(new Pos(nextX, nextY));
                    visited[nextX][nextY] = true;
                    q.add(new Pos(nextX, nextY));
                }
            }
        }
        return popList;
    }

    public static void initVisited () {
        for (int i=0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }
    }
}