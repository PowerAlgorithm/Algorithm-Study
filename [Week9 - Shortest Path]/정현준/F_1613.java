import java.io.*;
import java.util.*;

class Main {
    static int[][] map;
    static final int INF = 10_000_001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        int relation = Integer.parseInt(st.nextToken());

        map = new int[size][size];

        for (int i = 0; i < relation; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            map[start][end] = -1;
            map[end][start] = 1;
        }

        for (int mid = 0; mid < size; mid++) {
            for (int start = 0; start < size; start++) {
                for (int end = 0; end < size; end++) {
                    if (start != end && map[start][end] == 0) {
                        if (map[start][mid] == -1 && map[mid][end] == -1) {
                            map[start][end] = -1;
                        } else if (map[start][mid] == 1 && map[mid][end] == 1) {
                            map[start][end] = 1;
                        }
                    }
                }
            }
        }
        // print(map , size , size);
        int test = Integer.parseInt(br.readLine());

        while (test-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            System.out.println(map[start][end]);
        }

    }

    public static void print(int[][] map, int x, int y) {
        for (int i = 0; i < x; i++) {
            System.out.println();
            for (int j = 0; j < y; j++) {
                System.out.print(map[i][j] + " ");
            }
        }
        System.out.println();
    }
}