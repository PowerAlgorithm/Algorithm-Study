import java.io.*;
import java.util.*;

class Fish implements Comparable<Fish> {
    int x;
    int y;
    int size;
    int eatCount;
    int dist;

    public Fish(int x, int y, int size, int eatCount, int dist) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.eatCount = eatCount;
        this.dist = dist;
    }

    @Override
    public int compareTo(Fish o) {
        // 1. 가장 거리가 가까운 먹이
        // 2. 거리가 같은 경우 위에 있는 먹이
        // 3. 같은 행에 있으면 가장 왼쪽에 있는 먹이
        if (this.dist == o.dist) {
            if (this.x == o.x)
                return this.y - o.y;
            else
                return this.x - o.x;
        }
        return this.dist - o.dist;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "x=" + x +
                ", y=" + y +
                ", size=" + size +
                ", eatCount=" + eatCount +
                ", dist=" + dist +
                '}';
    }
}

class Main {
    static int[][] map;
    static int[][] moves = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    static Fish shark;
    static int size;

    public static void main(String[] args) throws IOException {
        // 참고 https://codingnojam.tistory.com/79
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        size = Integer.parseInt(br.readLine());

        map = new int[size][size];

        boolean feedExist = false;
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                int value = Integer.parseInt(st.nextToken());
                if (value == 9) {
                    shark = new Fish(i, j, 2, 0, 0);
                    value = 0;
                } else if (value == 1) {
                    feedExist = true;
                }
                map[i][j] = value;
            }
        }
        // System.out.println(shark);
        // 최초에 먹을 수 있는 먹이가 없다면
        if (!feedExist) {
            bw.append("0");
        } else {
            bw.append(bfs());
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static String bfs() {
        PriorityQueue<Fish> feedQ = new PriorityQueue<>();
        Queue<Fish> q = new ArrayDeque<>();
        q.offer(shark);
        int result = 0;

        // 먹이들을 먹을 수 없거나 다 먹을 때 까지
        while (true) {
            // 방문 체크
            boolean[][] visited = new boolean[size][size];

            // q에 들어있는 위치 기준으로 갈 수 있는 곳은 다 방문
            while (!q.isEmpty()) {
                Fish fish = q.poll();
                visited[fish.x][fish.y] = true;
                for (int[] move : moves) {
                    int moveX = fish.x + move[0];
                    int moveY = fish.y + move[1];
                    if (isOutOfRange(moveX, moveY) || visited[moveX][moveY] || map[moveX][moveY] > fish.size)
                        continue;

                    // 방문할 곳에 먹이가 있고 현재 사이즈보다 작다면 feedQ에 넣는다
                    if (map[moveX][moveY] < fish.size && map[moveX][moveY] != 0) {
                        feedQ.offer(new Fish(moveX, moveY, fish.size, fish.eatCount + 1, fish.dist + 1));
                    }
                    // 이동할 수 있다면 거리를 증가시켜 q에 넣는다
                    q.offer(new Fish(moveX, moveY, fish.size, fish.eatCount, fish.dist + 1));
                    visited[moveX][moveY] = true;

                }
            }

            // feedQ가 비었다면 끝난다
            if (feedQ.isEmpty()) {
                return String.valueOf(result);
            }

            // 먹이를 꺼내 상어가 먹은 먹이의 수를 확인한다
            // 여러 개의 먹이 중 우선순위 조건에 맞는 먹이를 꺼낸다
            // 위의 먹이 위치 기준으로 상어를 이동시키며 feedQ를 초기화 한다
            Fish fish = feedQ.poll();
            // System.out.println(fish + " " + map[fish.x][fish.y]);
            if (fish.size == fish.eatCount) {
                fish.size++;
                fish.eatCount = 0;
            }
            map[fish.x][fish.y] = 0;
            result += fish.dist;
            fish.dist = 0;
            q.offer(fish);
            feedQ.clear();
        }
    }

    public static boolean isOutOfRange(int x, int y) {
        if (x >= size || y >= size || x < 0 || y < 0)
            return true;
        return false;
    }
}
