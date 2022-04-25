import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {
    static int[][] moves4Dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    static int m, n;
    static List<Integer> resultList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        int[][] arr = new int[m][n];

        int count = Integer.parseInt(st.nextToken());
        for (int i = 0; i < count; i++) {
            st = new StringTokenizer(br.readLine());
            int ldX = Integer.parseInt(st.nextToken()); // 왼쪽 아래 x
            int ldY = Integer.parseInt(st.nextToken()); // 왼쪽 아래 y
            int ruX = Integer.parseInt(st.nextToken()); // 오른쪽 위 x
            int ruY = Integer.parseInt(st.nextToken()); // 오른쪽 위 y
            for (int a = ldY; a < ruY; a++) {
                for (int b = ldX; b < ruX; b++) {
                    arr[a][b] = 2;
                }
            }
        }

        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (arr[x][y] == 0) {
                    bfs(x, y, arr);
                }
            }
        }

        // 영역의 넓이 배열 오름차순 정렬
        Collections.sort(resultList);

        sb.append(resultList.size()).append("\n");
        for (int value : resultList) {
            sb.append(value).append(" ");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void bfs(int x, int y, int[][] arr) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] { x, y });
        // `0`은 빈 셀
        // `1`은 빈 셀이지만 방문한 곳
        // `2`는 입력받은 사각형 영역
        arr[x][y] = 1;

        // 한 영역의 셀의 개수
        int cellCount = 1;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int[] move : moves4Dir) {
                int moveX = now[0] + move[0];
                int moveY = now[1] + move[1];
                if (!isOufOfRange(moveX, moveY) && arr[moveX][moveY] == 0) {
                    cellCount++;
                    arr[moveX][moveY] = 1;
                    queue.offer(new int[] { moveX, moveY });
                }
            }
        }
        resultList.add(cellCount);
    }

    public static boolean isOufOfRange(int x, int y) {
        if (x < 0 || y < 0 || x >= m || y >= n)
            return true;
        return false;
    }
}