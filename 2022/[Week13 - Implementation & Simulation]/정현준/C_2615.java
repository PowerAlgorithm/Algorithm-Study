import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static final int SIZE = 19;
    static final int SAME_COUNT = 5;
    static int[][] map = new int[SIZE][SIZE];
    static int[][] eightDirs = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
    static int winner = Integer.MAX_VALUE;
    static int minX = Integer.MAX_VALUE;
    static int minY = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < SIZE; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        winner = 0;
        minX = Integer.MAX_VALUE;
        minY = Integer.MAX_VALUE;
        firstloop: for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] != 0) {
                    for (int k = 0; k < 8; k++) {
                        if (find(map[i][j], i, j, k)) {
                            break firstloop;
                        }
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(winner).append("\n");
        if (winner != 0) {
            sb.append(minX + 1).append(" ").append(minY + 1);
        }
        System.out.println(sb.toString());
    }

    public static boolean find(int blackOrWhite, int x, int y, int dir) {
        ArrayDeque<Position> queue = new ArrayDeque<Position>();
        Position start = new Position(x, y);
        queue.offer(start);
        int count = 1;
        boolean[][] visit = new boolean[SIZE][SIZE];
        visit[x][y] = true;
        while (!queue.isEmpty()) {
            Position now = queue.poll();
            getMinResult(now.x, now.y);
            for (int i = 0; i < 2; i++) {
                int newDir = i == 0 ? dir : reverseDir(dir);
                int[] moveDir = eightDirs[newDir];
                int moveX = now.x + moveDir[0];
                int moveY = now.y + moveDir[1];
                if (!isOutOfRange(moveX, moveY)) {
                    if (!visit[moveX][moveY] && map[moveX][moveY] == blackOrWhite) {
                        visit[moveX][moveY] = true;
                        count++;
                        queue.offer(new Position(moveX, moveY));
                    }
                }
            }
        }
        // System.out.println(String.format("%d , x = %d , y = %d , count = %d",
        // blackOrWhite , x , y , count));
        if (count == 5) {
            winner = blackOrWhite;
            return true;
        } else {
            winner = 0;
            minX = Integer.MAX_VALUE;
            minY = Integer.MAX_VALUE;
            return false;
        }
    }

    public static int reverseDir(int dir) {
        return dir < 4 ? dir + 4 : dir - 4;
    }

    public static void getMinResult(int x, int y) {
        if (minY > y || (minY == y && minX > x)) {
            minX = x;
            minY = y;
        }
    }

    public static boolean isOutOfRange(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return true;
        }
        return false;
    }
}