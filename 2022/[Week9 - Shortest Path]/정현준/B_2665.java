import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int node;
    int cost;

    public Edge(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}

class Move implements Comparable<Move> {
    int x;
    int y;
    int count;

    public Move(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }

    @Override
    public int compareTo(Move o) {
        return this.count - o.count;
    }
}

class Main {
    static int size;
    static int[][] fourDirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    static boolean[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());

        map = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            String line = br.readLine();
            for (int j = 0; j < size; j++) {
                if (line.charAt(j) - 48 == 1) {
                    map[i][j] = true;
                } else
                    map[i][j] = false;
            }
        }

        System.out.println(dijkstra(0, 0));
    }

    public static int dijkstra(int x, int y) {
        int[][] count = makeCountArr();
        int result = 0;
        PriorityQueue<Move> pq = new PriorityQueue<Move>();
        pq.offer(new Move(x, y, 0));

        while (!pq.isEmpty()) {
            Move now = pq.poll();
            if (now.x == size - 1 && now.y == size - 1) {
                return now.count;
            }
            for (int[] dir : fourDirs) {
                int moveX = now.x + dir[0];
                int moveY = now.y + dir[1];
                if (!isOutOfRange(moveX, moveY)) {
                    if (map[moveX][moveY] && count[moveX][moveY] > now.count) {
                        count[moveX][moveY] = now.count;
                        pq.offer(new Move(moveX, moveY, now.count));
                    } else if (count[moveX][moveY] > now.count + 1) {
                        count[moveX][moveY] = now.count + 1;
                        pq.offer(new Move(moveX, moveY, now.count + 1));
                    }
                }
            }
        }
        return result;
    }

    public static boolean isOutOfRange(int x, int y) {
        if (x >= size || x < 0 || y >= size || y < 0) {
            return true;
        }
        return false;
    }

    public static int[][] makeCountArr() {
        int[][] result = new int[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(result[i], Integer.MAX_VALUE);
        }
        return result;
    }
}