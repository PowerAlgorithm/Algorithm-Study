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
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class Main {
    static int[][] move4dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    static int row, col;
    static boolean[][] visited;
    static char[][] map;
    static Queue<Position> queue;
    static boolean[] keys;
    static Map<Character, List<Position>> noKeysDoor;
    static int findDocCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testcase = Integer.parseInt(br.readLine());

        for (int test = 0; test < testcase; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 행 , 열 사이즈
            row = Integer.parseInt(st.nextToken());
            col = Integer.parseInt(st.nextToken());

            // 초기화
            map = new char[row][col];

            // 2차원 배열
            for (int i = 0; i < row; i++) {
                map[i] = br.readLine().toCharArray();
            }

            // 키와 문 배열 초기화
            keys = new boolean[26];
            // 방문 배열
            visited = new boolean[row][col];
            queue = new ArrayDeque<>();
            noKeysDoor = new HashMap<>();
            // 상근이가 가지고 있는 키들을 아스키 코드를 이용하여 keys 배열을 true로 저장 한다
            char[] keysArr = br.readLine().toCharArray();
            if (keysArr[0] != '0') {
                for (char key : keysArr) {
                    int index = key - 97;
                    keys[index] = true;
                }
            }

            findDocCount = 0;

            // 가진 키로 열 수 있는 문들은 이동할 수 있는 곳 으로 변경하고
            // 가진 키로 열 수 없는 문들은 noKeysDoor에 담는다
            // 테두리의 진입점들을 담는다
            doorAndReachableInit();

            bfs();

            bw.append(findDocCount + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void doorAndReachableInit() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 0 행 , 마지막 행 , 0열 , 마지막 열
                if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                    if (Character.isLowerCase(map[i][j])) {
                        int index = map[i][j] - 97;
                        keys[index] = true;
                        map[i][j] = '.';
                        noKeysDoorGet(Character.toUpperCase(map[i][j]));
                    } else if (Character.isUpperCase(map[i][j])) {
                        int index = map[i][j] - 65;
                        if (keys[index]) {
                            map[i][j] = '.';
                        } else {
                            noKeysDoorPut(map[i][j], new Position(i, j));
                        }
                    }

                    char cell = map[i][j];
                    if (cell == '.' || cell == '$') {
                        queue.offer(new Position(i, j));
                    }
                }
            }
        }
    }

    public static void bfs() {
        // '.'는 빈 공간을 나타낸다.
        // '*'는 벽을 나타내며, 상근이는 벽을 통과할 수 없다.
        // '$'는 상근이가 훔쳐야하는 문서이다.
        // 알파벳 대문자는 문을 나타낸다.
        // 알파벳 소문자는 열쇠를 나타내며, 그 문자의 대문자인 모든 문을 열 수 있다.
        // 1. 키를 찾을 때 마다 방문 배열을 초기화 한다면 ?? -> 메모리와 시간을 너무 많이 잡아 먹을 것 같으니 2번으로 해보자
        // 2. 열지 못 하는 문의 위치를 담으며 , 새로운 키를 찾을 때 마다 그 키에 해당하는 문의 위치를 큐에 넣어준다면 ??

        while (!queue.isEmpty()) {
            Position now = queue.poll();
            if (map[now.x][now.y] == '$') {
                findDocCount++;
                map[now.x][now.y] = '.';
            }
            // 현재 위치 방문 처리
            visited[now.x][now.y] = true;
            for (int[] move : move4dirs) {
                int moveX = now.x + move[0];
                int moveY = now.y + move[1];
                if (!isOutOfRange(moveX, moveY) && !visited[moveX][moveY]) {
                    char cell = map[moveX][moveY];
                    // 이동 가능한 곳은 이동한다
                    if (cell == '.' || cell == '$') {
                        queue.offer(new Position(moveX, moveY));
                    }
                    // 새로운 문을 찾았다면
                    else if (Character.isUpperCase(cell)) {
                        int index = cell - 65;
                        // 해당하는 문의 키가 있다면 큐에 담는다
                        if (keys[index]) {
                            queue.offer(new Position(moveX, moveY));
                        }
                        // 해당하는 문의 키가 없다면 일단 noKeysDoor에 담는다
                        else {
                            noKeysDoorPut(cell, new Position(moveX, moveY));
                        }
                    }
                    // 새로운 키를 찾았다면
                    else if (Character.isLowerCase(cell)) {
                        queue.offer(new Position(moveX, moveY));
                        // 해당하는 키의 인덱스를 true
                        keys[cell - 97] = true;
                        // 새로운 키에 열리는 문이 존재한다면 문의 위치를 큐에 담는다
                        noKeysDoorGet(Character.toUpperCase(cell));
                    }
                    visited[moveX][moveY] = true;
                }
            }
        }
    }

    public static void noKeysDoorGet(char cell) {
        if (noKeysDoor.containsKey(cell)) {
            for (Position doorPos : noKeysDoor.get(cell)) {
                queue.offer(doorPos);
            }
            noKeysDoor.remove(cell);
        }
    }

    public static void noKeysDoorPut(char cell, Position pos) {
        if (noKeysDoor.containsKey(cell)) {
            noKeysDoor.get(cell).add(pos);
        } else {
            List<Position> doors = new ArrayList<>();
            doors.add(pos);
            noKeysDoor.put(cell, doors);
        }
    }

    public static boolean isOutOfRange(int x, int y) {
        if (x < 0 || x >= row || y < 0 || y >= col) {
            return true;
        }
        return false;
    }
}