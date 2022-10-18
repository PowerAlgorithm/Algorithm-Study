import java.io.*;
import java.util.*;

class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Position [x=" + x + ", y=" + y + "]";
    }

}

class Move extends Position implements Comparable<Move> {
    int goThroughGarvage;
    int nearGarvage;

    public Move(int x, int y, int goThroughGarvage, int nearGarvage) {
        super(x, y);
        this.goThroughGarvage = goThroughGarvage;
        this.nearGarvage = nearGarvage;
    }

    @Override
    public int compareTo(Move o) {
        if (this.goThroughGarvage == o.goThroughGarvage) {
            return this.nearGarvage - o.nearGarvage;
        }
        return this.goThroughGarvage - o.goThroughGarvage;
    }

    @Override
    public String toString() {
        return "Move [" + " x=" + x + " y=" + y + " goThroughGarvage=" + goThroughGarvage + "]";
    }
}

class Main {
    static int row, col;
    static Position start, end;
    static int[][] fourDirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new char[row][col];

        for (int i = 0; i < row; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < col; j++) {
                char ch = line[j];
                if (ch == 'S') {
                    start = new Position(i, j);
                } else if (ch == 'F') {
                    end = new Position(i, j);
                }
                map[i][j] = ch;
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                char ch = map[i][j];
                if (!(i == start.x && j == start.y)) {
                    if (ch == 'g') {
                        for (int[] dir : fourDirs) {
                            int moveX = i + dir[0];
                            int moveY = j + dir[1];
                            if (!isOutOfRange(moveX, moveY)) {
                                if (map[moveX][moveY] == '.') {
                                    map[moveX][moveY] = 'q';
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println(dijkstra(start.x, start.y));
    }

    public static String dijkstra(int x, int y) {
        visited = new boolean[row][col];
        visited[x][y] = true;
        PriorityQueue<Move> pq = new PriorityQueue<Move>();
        pq.offer(new Move(x, y, 0, 0));
        while (!pq.isEmpty()) {
            Move now = pq.poll();
            for (int[] dir : fourDirs) {
                int moveX = now.x + dir[0];
                int moveY = now.y + dir[1];
                int goThroughGarvage = now.goThroughGarvage;
                int nearGarvage = now.nearGarvage;
                if (!isOutOfRange(moveX, moveY) && !visited[moveX][moveY]) {
                    if (map[moveX][moveY] == 'q') {
                        nearGarvage++;
                    } else if (map[moveX][moveY] == 'g') {
                        // 이동할 칸에 쓰레기가 있다면 주변 쓰레기는 카운트 하지 않는다
                        goThroughGarvage++;
                    } else if (map[moveX][moveY] == 'F') {
                        return now.goThroughGarvage + " " + now.nearGarvage;
                    }
                    pq.offer(new Move(moveX, moveY, goThroughGarvage, nearGarvage));
                    visited[moveX][moveY] = true;
                }
            }
        }
        return "0 0";
    }

    public static boolean isOutOfRange(int x, int y) {
        if (x >= row || x < 0 || y >= col || y < 0) {
            return true;
        }
        return false;
    }
}