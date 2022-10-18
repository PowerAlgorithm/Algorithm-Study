import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N, row, col, result;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        // 참고 https://mygumi.tistory.com/284
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        result = 0;

        search(0, 0, (1 << N));

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void search(int x, int y, int size) {
        if (x == row && y == col) {
            sb.append(result);
            return;
        }

        // x,y가 현재 사분면에 존재한다면
        if (x + size > row && x <= row && y + size > col && y <= col) {
            // 1사분면 탐색
            search(x, y, size / 2);
            // 2사분면 탐색
            search(x, y + size / 2, size / 2);
            // 3사분면 탐색
            search(x + size / 2, y, size / 2);
            // 4사분면 탐색
            search(x + size / 2, y + size / 2, size / 2);
        } else {
            result += size * size;
        }
    }
}