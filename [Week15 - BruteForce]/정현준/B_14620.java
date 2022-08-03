import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] nears = { { 0, 0 }, { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
    static int[][] price;
    static boolean[][] used;
    static int result, size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        size = Integer.parseInt(br.readLine());
        price = new int[size][size];
        used = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                price[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        result = Integer.MAX_VALUE;
        find(0, 0);

        System.out.println(result);
    }

    public static void find(int count, int sum) {
        if (count == 3) {
            result = Math.min(result, sum);
            return;
        }
        for (int i = 1; i < size - 1; i++) {
            for (int j = 1; j < size - 1; j++) {
                if (isPossible(i, j)) {
                    int needPrice = getNeedPrice(i, j, true);
                    find(count + 1, sum + needPrice);
                    getNeedPrice(i, j, false);
                }
            }
        }
    }

    public static boolean isPossible(int x, int y) {
        for (int[] near : nears) {
            int moveX = x + near[0];
            int moveY = y + near[1];
            if (isOutOfRange(moveX, moveY) || used[moveX][moveY]) {
                return false;
            }
        }
        return true;
    }

    public static int getNeedPrice(int x, int y, boolean check) {
        int result = 0;
        for (int[] near : nears) {
            int moveX = x + near[0];
            int moveY = y + near[1];
            used[moveX][moveY] = check;
            result += price[moveX][moveY];
        }
        return result;
    }

    public static boolean isOutOfRange(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size) {
            return true;
        }
        return false;
    }
}