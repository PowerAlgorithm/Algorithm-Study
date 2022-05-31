import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {
    static int size;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        size = Integer.parseInt(br.readLine());
        map = new int[size][size];

        // 컴퓨터의 수 누적
        long sum = 0;
        int max = 0;
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
                sum += map[i][j];
            }
        }

        int min = 0;
        while (min < max) {
            int mid = (min + max) / 2;

            long onCount = getOnCount(mid);

            if (((double) onCount / sum) >= 0.5) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }

        bw.append(String.valueOf(min));
        bw.flush();
        bw.close();
        br.close();
    }

    public static long getOnCount(int height) {
        long result = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] >= height)
                    result += height;
                else
                    result += map[i][j];
            }
        }
        return result;
    }
}
