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
    static int[][] moves4dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    static int[][] map;
    static Queue<Position> waterPos = new ArrayDeque<>();
    static int row, col;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 행과 열 사이즈
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new int[row][col];

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 물의 위치를 전역 큐에 넣는다
                if (map[i][j] == 0) {
                    waterPos.offer(new Position(i, j));
                }
            }
        }

        // 빙산이 녹은 횟수 카운트
        int meltCount = 0;
        boolean findChunks = false;
        while (!waterPos.isEmpty()) {
            // 빙산을 녹임
            melt();

            meltCount++;
            // 빙산의 영역이 분리되었는지 확인
            if (findChunk()) {
                // 분리되었다면 break
                findChunks = true;
                break;
            }
        }

        bw.append(findChunks ? String.valueOf(meltCount) : "0");
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean findChunk() {
        int chunkCount = 0;
        // 방문 배열
        boolean[][] checked = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 빙산을 찾았다면 그 주변 빙산들 방문처리
                if (!checked[i][j] && map[i][j] > 0) {
                    checkChunk(i, j, checked);
                    chunkCount++;
                }
            }
        }
        return chunkCount > 1 ? true : false;
    }

    public static void checkChunk(int x, int y, boolean[][] checked) {
        // x , y 위치에 있는 빙산에 인접한 빙산들을 방문처리 한다
        Queue<Position> queue = new ArrayDeque<>();
        queue.offer(new Position(x, y));
        checked[x][y] = true;

        while (!queue.isEmpty()) {
            Position water = queue.poll();
            for (int[] move : moves4dir) {
                int moveX = water.x + move[0];
                int moveY = water.y + move[1];
                if (moveX >= 0 && moveX < row && moveY >= 0 && moveY < col) {
                    if (!checked[moveX][moveY] && map[moveX][moveY] > 0) {
                        checked[moveX][moveY] = true;
                        queue.offer(new Position(moveX, moveY));
                    }
                }
            }
        }
    }

    public static void melt() {
        // 메서드 실행 시 바닷물 큐에 담겨있는 사이즈만큼만 확인한다
        int waterPosSize = waterPos.size();
        for (int i = 0; i < waterPosSize; i++) {
            boolean icebergNear = false;
            Position water = waterPos.poll();
            for (int[] move : moves4dir) {
                int moveX = water.x + move[0];
                int moveY = water.y + move[1];
                if (moveX >= 0 && moveX < row && moveY >= 0 && moveY < col) {
                    // 바닷물 4방향에 빙산이 있다면
                    if (map[moveX][moveY] > 0) {
                        // 빙산을 녹인다
                        --map[moveX][moveY];
                        // 빙산이 다 녹았다면 바닷물 큐에 넣는다
                        if (map[moveX][moveY] == 0) {
                            waterPos.add(new Position(moveX, moveY));
                        }
                        // 해당 바닷물 주변에 빙산이 존재한다는 flag값
                        else
                            icebergNear = true;
                    }
                }
            }
            // flag값이 true라면 주변에 빙산이 있다는 의미이므로 바닷물 큐에 다시 넣는다
            if (icebergNear)
                waterPos.offer(water);
        }
    }
}