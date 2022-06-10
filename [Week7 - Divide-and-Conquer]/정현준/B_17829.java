import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int[][] map;
    // 1 , 2
    // 3 , 4
    static int[] moveX = { 0, 0, 1, 1 };
    static int[] moveY = { 0, 1, 0, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력
        int size = Integer.parseInt(br.readLine());
        map = new int[size][size];
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 입력 받은 사이즈를 반복문을 돌며 2씩 나누고
        // 사이즈가 1이 될 때 까지 돈다
        while (size != 1) {
            search(0, 0, size);
            size /= 2;
        }

        bw.append(String.valueOf(map[0][0]));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void search(int x, int y, int size) {
        // size가 2라면 2 * 2 짜리 사각형의 정수를 오름차순으로 정렬
        if (size == 2) {
            int[] tmp = new int[4];
            for (int i = 0; i < 4; i++) {
                int mx = x + moveX[i];
                int my = y + moveY[i];
                tmp[i] = map[mx][my];
            }
            Arrays.sort(tmp);
            map[x / 2][y / 2] = tmp[2];
            return;
        }

        // 사이즈를 2씩 나누어 사각형의 영역을 절반 씩 줄여감
        // x,y 지점을 3방향으로 퍼트린다
        for (int i = 0; i < 4; i++) {
            int divideSize = size / 2;
            int nx = x + (moveX[i] * divideSize);
            int ny = y + (moveY[i] * divideSize);
            search(nx, ny, divideSize);
        }
    }
}