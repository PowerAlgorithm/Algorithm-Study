import java.io.*;
import java.util.*;

class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Position obj) {
        return this.x == obj.x && this.y == obj.y;
    }
}

class Main {
    static int size;
    static int[] moveXpos = { 0, 1, 0, -1 };
    static int[] moveYpos = { 1, 0, -1, 0 };
    static int[][] map;
    static Map<Integer, Integer> dir;
    static Queue<Position> snake;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        size = Integer.parseInt(br.readLine());
        int appleCount = Integer.parseInt(br.readLine());

        map = new int[size][size];

        StringTokenizer st;
        // 사과 위치 입력
        for (int i = 0; i < appleCount; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            // 사과의 위치는 1로 저장
            map[x][y] = 1;
        }

        int dirCount = Integer.parseInt(br.readLine());
        // 2차원 방향 배열 초기화
        dir = new HashMap<>();
        for (int i = 0; i < dirCount; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            // L -> -1 , D -> 1
            dir.put(time, st.nextToken().equals("L") ? -1 : 1);
        }
        // 뱀 초기화
        snake = new ArrayDeque<>();

        bw.append(String.valueOf(findApple()));
        bw.flush();
        bw.close();
        br.close();
    }

    public static int findApple() {
        int time = 0;
        int currX = 0;
        int currY = 0;
        int currDir = 0;
        // 뱀의 몸통은 -1로 표현
        map[currX][currY] = -1;
        // 뱀의 시작 지점 queue에 담기
        snake.offer(new Position(0, 0));

        while (true) {
            time++;
            int moveX = currX + moveXpos[currDir];
            int moveY = currY + moveYpos[currDir];

            // 범위를 벗어나거나 머리와 몸통이 닿았다면 break
            if (isOutOfRange(moveX, moveY))
                break;
            if (map[moveX][moveY] == -1)
                break;

            // 이동 위치에 사과가 없다면 꼬리의 위치를 이동
            if (map[moveX][moveY] != 1) {
                Position tail = snake.poll();
                map[tail.x][tail.y] = 0;
            }
            // 머리를 이동 시키고 위치는 -1로 표현
            snake.offer(new Position(moveX, moveY));
            map[moveX][moveY] = -1;

            currX = moveX;
            currY = moveY;

            // 방향 전환 시간이라면
            if (dir.containsKey(time)) {
                int nextDir = (currDir + dir.get(time)) % 4;
                if (nextDir == -1)
                    nextDir = 3;
                currDir = nextDir;
            }
        }

        return time;
    }

    public static boolean isOutOfRange(int x, int y) {
        if (x < 0 || y < 0 || x >= size || y >= size) {
            return true;
        }
        return false;
    }
}