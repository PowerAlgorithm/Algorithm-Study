import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

public class Main {
    static final int ROW = 12;
    static final int COL = 6;
    static char[][] map = new char[ROW][COL];
    static int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < ROW; i++) {
            String line = br.readLine();
            for (int j = 0; j < COL; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        System.out.println(chainCount());
    }

    public static int chainCount() {
        int count = 0;
        List<Position> positions;
        while ((positions = findGroup()).size() > 0) {
            count++;
            Set<Integer> bombs = new HashSet<>();
            for (Position pos : positions) {
                map[pos.x][pos.y] = '.';
                bombs.add(pos.y);
            }
            for (int bombY : bombs) {
                int bombX = ROW - 1;
                while (bombX >= 0) {
                    if (map[bombX][bombY] == '.') {
                        for (int findX = bombX - 1; findX >= 0; findX--) {
                            if (map[findX][bombY] != '.') {
                                map[bombX][bombY] = map[findX][bombY];
                                map[findX][bombY] = '.';
                                break;
                            }
                        }
                    }
                    bombX--;
                }
            }
            // print();
        }
        return count;
    }

    public static List<Position> findGroup() {
        List<Position> positions = new ArrayList<>();
        boolean[][] checked = new boolean[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (map[i][j] != '.') {
                    List<Position> group = new ArrayList<>();
                    ArrayDeque<Position> queue = new ArrayDeque<>();
                    Position now = new Position(i, j);
                    checked[i][j] = true;
                    group.add(now);
                    queue.offer(now);

                    while (!queue.isEmpty()) {
                        Position pos = queue.poll();
                        for (int k = 0; k < 4; k++) {
                            int moveX = pos.x + dirs[k][0];
                            int moveY = pos.y + dirs[k][1];
                            if (!isOutOfRange(moveX, moveY) && map[moveX][moveY] == map[pos.x][pos.y]
                                    && !checked[moveX][moveY]) {
                                Position move = new Position(moveX, moveY);
                                group.add(move);
                                queue.offer(move);
                                checked[moveX][moveY] = true;
                            }
                        }
                    }
                    if (group.size() >= 4) {
                        for (Position bombPos : group) {
                            // System.out.println(String.format("%s %c" , bombPos.toString() ,
                            // map[bombPos.x][bombPos.y]));
                            positions.add(bombPos);
                        }
                    }
                }
            }
        }
        return positions;
    }

    public static boolean isOutOfRange(int x, int y) {
        if (x < 0 || x >= ROW || y < 0 || y >= COL) {
            return true;
        }
        return false;
    }

    public static void print() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------");
    }
}