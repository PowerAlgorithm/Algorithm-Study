import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] map;
    static int row, col, block;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        block = Integer.parseInt(st.nextToken());

        map = new int[row][col];

        int minHeight = Integer.MAX_VALUE;
        int maxHeight = 0;
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                minHeight = Math.min(minHeight, map[i][j]);
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        int resultTime = Integer.MAX_VALUE;
        int resultHeight = 0;
        for (int height = minHeight; height <= maxHeight; height++) {
            int time = 0;
            int myBlock = block;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    int diff = map[i][j] - height;
                    if (diff > 0) {
                        time += Math.abs(diff) * 2;
                        myBlock += Math.abs(diff);
                    } else {
                        time += Math.abs(diff);
                        myBlock -= Math.abs(diff);
                    }
                }
            }
            if (myBlock < 0) {
                continue;
            }
            if (resultTime >= time) {
                resultTime = time;
                resultHeight = height;
            }
        }
        System.out.println(resultTime + " " + resultHeight);
    }

}