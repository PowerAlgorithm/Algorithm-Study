import java.io.*;
import java.util.*;

class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Move extends Position implements Comparable<Move> {
    int count;
    int dir;

    public Move(int x, int y, int count, int dir) {
        super(x, y);
        this.count = count;
        this.dir = dir;
    }

    @Override
    public int compareTo(Move o) {
        return this.count - o.count;
    }

    @Override
    public String toString() {
        return "Move [" + " x=" + x + " y=" + y + " count=" + count + ", dir=" + dir + "]";
    }
}

class Main {
    static PriorityQueue<Move> pq;
    static int row, col;
    static Position start, end;
    static int[][] fourDirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }, count;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());

        map = new char[row][col];
        count = makeCountArr();

        for (int i = 0; i < row; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < col; j++) {
                char ch = line[j];
                if (ch == 'C') {
                    if (start == null)
                        start = new Position(i, j);
                    else
                        end = new Position(i, j);
                    ch = '.';
                }
                map[i][j] = ch;
            }
        }

        System.out.println(dijkstra(start.x, start.y));
    }

    public static int dijkstra(int x, int y) {
        int result = 0;
        pq = new PriorityQueue<Move>();
        pq.offer(new Move(x, y, 0, -1));

        while (!pq.isEmpty()) {
            // print();
            Move now = pq.poll();
            if (now.x == end.x && now.y == end.y) {
                return now.count;
            }
            for (int i = 0; i < 4; i++) {
                int[] moves = fourDirs[i];
                int moveX = now.x + moves[0];
                int moveY = now.y + moves[1];
                if (!isOutOfRange(moveX, moveY)) {
                    if (map[moveX][moveY] == '.' && count[moveX][moveY] > now.count) {
                        // 최초에만 4방향으로 거울없이 발사
                        // 지금 위치가 움직이는 해당 방향으로 나아가기
                        if (now.dir == -1 || now.dir == i) {
                            pq.offer(new Move(moveX, moveY, now.count, i));
                            count[moveX][moveY] = now.count;
                        }
                    }
                }
                if (now.dir != -1) {
                    mirrorMove(now);
                }
            }
        }
        return result;
    }

    public static void mirrorMove(Move now) {
        // 나아가는 방향 왼쪽 , 오른쪽이 갈 수 있는 곳이라면 거울을 세워서 방향 지정
        int[] moveDirs = getDir(now.dir);
        for (int mirrorMove : moveDirs) {
            int mirrorMoveX = now.x + fourDirs[mirrorMove][0];
            int mirrorMoveY = now.y + fourDirs[mirrorMove][1];
            if (!isOutOfRange(mirrorMoveX, mirrorMoveY)) {
                if (map[mirrorMoveX][mirrorMoveY] == '.' && count[mirrorMoveX][mirrorMoveY] > now.count) {
                    pq.offer(new Move(mirrorMoveX, mirrorMoveY, now.count + 1, mirrorMove));
                }
            }
        }

    }

    public static boolean isOutOfRange(int x, int y) {
        if (x >= row || x < 0 || y >= col || y < 0) {
            return true;
        }
        return false;
    }

    public static int[][] makeCountArr() {
        int[][] result = new int[row][col];
        for (int i = 0; i < row; i++) {
            Arrays.fill(result[i], Integer.MAX_VALUE);
        }
        return result;
    }

    public static int[] getDir(int dir) {
        if (dir == 0)
            return new int[] { 3, 1 };
        else if (dir == 1)
            return new int[] { 0, 2 };
        else if (dir == 2)
            return new int[] { 1, 3 };
        else
            return new int[] { 2, 0 };
    }

    public static void print() {
        for (int i = 0; i < row; i++) {
            System.out.println();
            for (int j = 0; j < col; j++) {
                System.out.print(count[i][j] == Integer.MAX_VALUE ? "x " : count[i][j] + " ");
            }
        }
        System.out.println();
    }
}