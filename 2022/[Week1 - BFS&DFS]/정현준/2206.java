import java.io.*;
import java.util.*;

class Move {
    int x;
    int y;
    int moveCount;
    int breakWall;

    public Move(int x, int y, int moveCount, int breakWall) {
        this.x = x;
        this.y = y;
        this.moveCount = moveCount;
        this.breakWall = breakWall;
    }
}

class Main {
    static int[][] move4dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    static int row, col;
    static char[][] map;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 행 , 열 사이즈
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        // 초기화
        map = new char[row][col];
        visited = new boolean[row][col][2];

        // 2차원 배열
        for (int i = 0; i < row; i++) {
            map[i] = br.readLine().toCharArray();
        }

        bw.append(bfs());
        bw.flush();
        bw.close();
        br.close();
    }

    public static String bfs() {
        // visited 배열에 벽을 부순 상태를 따로 저장하지 않으면 틀린다
        // 벽을 안 부수고도 현재 칸 까지 도달이 가능하지만 , 벽을 부수고 오는 것이 더 짧다고 가정해보자
        // 현재 지점에서 목표 지점까지 가려면 무조건 벽을 한 개 부숴야 한다고 해보자
        // 비록 현재 칸 까지는 벽을 부수고 오는 것이 최적이었지만 , 이 상태로는 끝에 아예 도달을 못한다
        // 현재 칸 까지는 더 멀더라도 벽을 안 부수고 와야 끝에 도달이 가능하다
        Queue<Move> queue = new ArrayDeque<>();
        queue.offer(new Move(0, 0, 1, 0));
        visited[0][0][0] = true;
        int minResult = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Move now = queue.poll();
            if (now.x == row - 1 && now.y == col - 1) {
                // 목표 위치에 도착했다면 최소 값을 갱신한다
                minResult = Math.min(now.moveCount, minResult);
                continue;
            }
            for (int[] move : move4dirs) {
                int moveX = now.x + move[0];
                int moveY = now.y + move[1];
                // 이동하려는 위치가 범위를 벗어나지 않고 방문한적이 없다면
                if (!isOutOfRange(moveX, moveY) && !visited[moveX][moveY][now.breakWall]) {
                    // 이동 가능한 길이라면 queue에 담는다
                    if ((map[moveX][moveY] == '0')) {
                        visited[moveX][moveY][now.breakWall] = true;
                        queue.offer(new Move(moveX, moveY, now.moveCount + 1, now.breakWall));
                    }
                    // 이동하려는 위치가 벽이지만 현재 벽을 부수고 오지 않았다면
                    else if (map[moveX][moveY] == '1' && now.breakWall == 0) {
                        visited[moveX][moveY][1] = true;
                        queue.offer(new Move(moveX, moveY, now.moveCount + 1, 1));
                    }
                }
            }
        }
        // minResult가 초기값과 같다면 목표에 도달하지 못한 것이므로 -1 리턴
        return minResult == Integer.MAX_VALUE ? "-1" : String.valueOf(minResult);
    }

    public static boolean isOutOfRange(int x, int y) {
        if (x < 0 || x >= row || y < 0 || y >= col) {
            return true;
        }
        return false;
    }
}