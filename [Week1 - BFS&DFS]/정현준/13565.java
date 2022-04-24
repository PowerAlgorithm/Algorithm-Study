import java.io.*;
import java.util.*;

class Main {
    static int[][] move4dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    static int row, col;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 행 , 열
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            char[] chArr = br.readLine().toCharArray();
            for (int j = 0; j < col; j++) {
                // 이동이 가능하다면 true , 불가능하다면 false
                if (chArr[j] == '0') {
                    map[i][j] = true;
                } else {
                    map[i][j] = false;
                }
            }
        }

        bw.append(bfs(map));

        bw.flush();
        bw.close();
        br.close();
    }

    public static String bfs(boolean[][] map) {
        // 0행 열마다 전류가 흐를 수 있는지 확인
        for (int y = 0; y < col; y++) {
            if (map[0][y]) {
                // 전류가 흐를 수 있다면 해당 셀 부터 4방향으로 BFS 시작
                Queue<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[] { 0, y });
                map[0][y] = false;

                while (!queue.isEmpty()) {
                    int[] now = queue.poll();
                    if (now[0] == row - 1) {
                        return "YES";
                    }
                    for (int[] move : move4dirs) {
                        int moveX = now[0] + move[0];
                        int moveY = now[1] + move[1];
                        // 확인하는 4방향이 범위를 벗어나지 않고 전류가 통할 수 있다면 queue에 담고 방문 체크 한다
                        if (!isOufOfRange(moveX, moveY) && map[moveX][moveY]) {
                            map[moveX][moveY] = false;
                            queue.offer(new int[] { moveX, moveY });
                        }
                    }
                }
            }
        }
        return "NO";
    }

    public static boolean isOufOfRange(int x, int y) {
        if (x < 0 || x >= row || y < 0 || y >= col) {
            return true;
        }
        return false;
    }

}