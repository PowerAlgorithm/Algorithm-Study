import java.io.*;
import java.util.*;

class Dust extends Position {
    int w;

    public Dust(int x, int y, int w) {
        super(x, y);
        this.w = w;
    }

    @Override
    public String toString() {
        return "Dust{" +
                "w=" + w +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}

class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Main {
    static int[][] moves = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    static int row, col, time;
    static Position[] air_cleaner = new Position[2];
    static Queue<Dust> dusts;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        time = Integer.parseInt(st.nextToken());

        dusts = new ArrayDeque<>();
        map = new int[row][col];

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                int val = map[i][j] = Integer.parseInt(st.nextToken());
                if (val == -1) {
                    // 공기청정기 위치 저장
                    if (air_cleaner[0] == null)
                        air_cleaner[0] = new Position(i, j);
                    else
                        air_cleaner[1] = new Position(i, j);
                } else if (val >= 5) {
                    // 먼지 위치 Queue에 담기
                    dusts.offer(new Dust(i, j, val));
                }
            }
        }

        while (time-- > 0) {
            // 먼지 확산
            dustDiffusion();

            // 공기청정기 작동
            cleanUp();
            cleanDown();

            // 먼지 확산 후 map에 5 이상 먼지들의 위치를 큐에 담는다
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (map[i][j] >= 5) {
                        dusts.offer(new Dust(i, j, map[i][j]));
                    }
                }
            }
        }

        int result = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j] != -1) {
                    result += map[i][j];
                }
            }
        }
        bw.append(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void cleanUp() {
        Position up = air_cleaner[0];
        // 순환 방향의 역방향으로 반복문을 돈다
        // ↑
        for (int x = up.x - 1; x > 0; x--) {
            map[x][up.y] = map[x - 1][up.y];
        }
        // →
        for (int y = up.y; y < col - 1; y++) {
            map[0][y] = map[0][y + 1];
        }
        // ↓
        for (int x = 0; x < up.x; x++) {
            map[x][col - 1] = map[x + 1][col - 1];
        }
        // ←
        for (int y = col - 1; y > 0; y--) {
            int value = map[up.x][y - 1] == -1 ? 0 : map[up.x][y - 1];
            map[up.x][y] = value;
        }
    }

    public static void cleanDown() {
        Position down = air_cleaner[1];
        // 순환 방향의 역방향으로 반복문을 돈다
        // ↓
        for (int x = down.x + 1; x < row - 1; x++) {
            map[x][down.y] = map[x + 1][down.y];
        }
        // →
        for (int y = down.y; y < col - 1; y++) {
            map[row - 1][y] = map[row - 1][y + 1];
        }
        // ↑
        for (int x = row - 1; x > down.x; x--) {
            map[x][col - 1] = map[x - 1][col - 1];
        }
        // ←
        for (int y = col - 1; y > 0; y--) {
            int value = map[down.x][y - 1] == -1 ? 0 : map[down.x][y - 1];
            map[down.x][y] = value;
        }
    }

    // 먼지 확산
    public static void dustDiffusion() {

        while (!dusts.isEmpty()) {
            Dust dust = dusts.poll();
            // 먼지가 5이상일 때만 확산
            if (dust.w / 5 >= 1) {
                // 확산양
                int spreadValue = dust.w / 5;
                for (int[] move : moves) {
                    int moveX = dust.x + move[0];
                    int moveY = dust.y + move[1];
                    // 범위를 벗어나지 않고 확산이 가능하다면
                    if (!isOutOfRange(moveX, moveY) && map[moveX][moveY] != -1) {
                        map[moveX][moveY] += spreadValue;
                        map[dust.x][dust.y] -= spreadValue;
                    }
                }
            }
        }
    }

    // 먼지의 범위가 map을 벗어나는지 확인
    public static boolean isOutOfRange(int moveX, int moveY) {
        if (moveX >= row || moveX < 0 || moveY >= col || moveY < 0)
            return true;
        return false;
    }

    public static void print() {
        for (int i = 0; i < row; i++) {
            System.out.println();
            for (int j = 0; j < col; j++) {
                System.out.print(map[i][j] + "  ");
            }
        }
        System.out.println();
        System.out.println("-------------------------");
    }
}