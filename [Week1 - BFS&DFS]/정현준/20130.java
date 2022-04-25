import javafx.geometry.Pos;

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

class Main {
    static int[][] move4dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    static int row, col;
    static char[][] map;
    static Map<Character, List<Position>> noKeysDoor;
    static Queue<Position> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 행 , 열 사이즈
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        // 초기화
        map = new char[row][col];

        // 2차원 배열
        // 시작 위치
        int startX = 0, startY = 0;
        for (int i = 0; i < row; i++) {
            char[] chArr = br.readLine().toCharArray();
            for (int j = 0; j < col; j++) {
                char ch = chArr[j];
                if (ch == '@') {
                    startX = i;
                    startY = j;
                    map[i][j] = '*';
                } else
                    map[i][j] = ch;
            }
        }

        bw.append(bfs(startX, startY));

        bw.flush();
        bw.close();
        br.close();
    }

    public static String bfs(int x, int y) {
        StringBuilder sb = new StringBuilder();
        int visitCount = 0;
        // 열쇠 배열
        boolean[] keys = new boolean[26];
        // 방문 배열
        boolean[][] visited = new boolean[row][col];
        // 자물쇠로 잠긴 지역
        noKeysDoor = new HashMap<>();
        queue = new ArrayDeque<>();
        queue.offer(new Position(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            visitCount++;
            Position now = queue.poll();
            sb.append(now.x + 1).append(" ").append(now.y + 1).append("\n");
            if (map[now.x][now.y] == '!') {
                break;
            }
            for (int[] move : move4dirs) {
                int moveX = now.x + move[0];
                int moveY = now.y + move[1];
                if (!isOutOfRange(moveX, moveY) && !visited[moveX][moveY]) {
                    char cell = map[moveX][moveY];
                    // 이동 가능한 곳은 이동한다
                    if (cell == '*' || cell == '!') {
                        queue.offer(new Position(moveX, moveY));
                    }
                    // 자물쇠로 잠긴 문을 찾았다면
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
        return visitCount + "\n" + sb.toString();
    }

    public static void noKeysDoorGet(char key) {
        if (noKeysDoor.containsKey(key)) {
            for (Position doorPos : noKeysDoor.get(key)) {
                queue.offer(doorPos);
            }
            noKeysDoor.remove(key);
        }
    }

    public static void noKeysDoorPut(char key, Position pos) {
        if (noKeysDoor.containsKey(key)) {
            noKeysDoor.get(key).add(pos);
        } else {
            List<Position> doors = new ArrayList<>();
            doors.add(pos);
            noKeysDoor.put(key, doors);
        }
    }

    public static boolean isOutOfRange(int x, int y) {
        if (x < 0 || x >= row || y < 0 || y >= col) {
            return true;
        }
        return false;
    }
}