import java.io.*;
import java.util.*;

class Main {
    static int city, bus;
    static int[][] map;
    static final int INF = 10_000_001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        city = Integer.parseInt(br.readLine());
        bus = Integer.parseInt(br.readLine());

        map = new int[city][city];

        for (int i = 0; i < city; i++) {
            Arrays.fill(map[i], INF);
        }

        for (int i = 0; i < bus; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            map[start][end] = Math.min(map[start][end], cost);
        }

        for (int mid = 0; mid < city; mid++) {
            for (int start = 0; start < city; start++) {
                for (int end = 0; end < city; end++) {
                    if (start != end && start != mid && end != mid) {
                        map[start][end] = Math.min(map[start][end], map[start][mid] + map[mid][end]);
                    }
                }
            }
        }

        for (int i = 0; i < city; i++) {
            for (int j = 0; j < city; j++) {
                System.out.print(map[i][j] == INF ? "0 " : map[i][j] + " ");
            }
            System.out.println();
        }
    }
}