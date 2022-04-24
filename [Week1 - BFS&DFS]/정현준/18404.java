import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringJoiner;
import java.util.StringTokenizer;

class Move {
    int x, y, count;

    public Move(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}

class Main {
    // 나이트의 8방향 배열 시계 방향 순서
    static int[][] moves8Dir = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 },
            { -2, -1 } };
    static int size, targetCount;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 행 열 사이즈
        size = Integer.parseInt(st.nextToken());
        targetCount = Integer.parseInt(st.nextToken());

        // 위치 배열 , 방문 배열 초기화
        map = new int[size][size];
        visited = new boolean[size][size];

        // 나이트 위치 저장
        int knight_X = 0, knight_Y = 0;

        // 나이트와 상대편 말의 위치
        for (int i = 0; i < targetCount + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            // 나이트 위치
            if (i == 0) {
                knight_X = x;
                knight_Y = y;
            }
            // 상대편 말 위치
            else {
                map[x][y] = i;
            }
        }

        bw.append(findTarget(knight_X, knight_Y));

        bw.flush();
        bw.close();
        br.close();
    }

    public static String findTarget(int x, int y) {
        int[] result = new int[targetCount];
        Queue<Move> queue = new ArrayDeque<>();
        queue.offer(new Move(x, y, 0));
        visited[x][y] = true;

        // 상대편 말 잡은 수
        int catchCount = 0;
        while (!queue.isEmpty()) {
            Move now = queue.poll();
            // 방금 큐에서 꺼낸 위치가 상대편 말의 위치 라면 catchCount 증가 후 그 위치는 공백으로 저장
            if (map[now.x][now.y] > 0) {
                catchCount++;
                result[map[now.x][now.y] - 1] = now.count;
                map[now.x][now.y] = 0;
            }
            // 상대편 말 잡은 수와 상대편 말의 수가 같다면 break
            if (catchCount == targetCount)
                break;

            // 현재 위치에서 8방향 이동
            for (int[] move : moves8Dir) {
                int moveX = move[0] + now.x;
                int moveY = move[1] + now.y;
                if (!isOutOfRange(moveX, moveY) && !visited[moveX][moveY]) {
                    visited[moveX][moveY] = true;
                    queue.offer(new Move(moveX, moveY, now.count + 1));
                }
            }
        }
        StringBuilder resultString = new StringBuilder();
        // 입력된 상대편 말의 순서에 맞게 count수 문자열 변환
        for (int value : result)
            resultString.append(value).append(" ");

        return resultString.toString();
    }

    public static boolean isOutOfRange(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size) {
            return true;
        }
        return false;
    }
}