import java.io.*;
import java.util.*;

class Main {
    static int nodeCount, edgeCount;
    static int[][] map;
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        nodeCount = Integer.parseInt(st.nextToken());
        edgeCount = Integer.parseInt(st.nextToken());

        map = new int[nodeCount + 1][nodeCount + 1];

        for (int i = 1; i <= nodeCount; i++) {
            Arrays.fill(map[i], INF);
        }

        for (int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            map[start][end] = 1;
            map[end][start] = 1;
        }

        for (int mid = 1; mid <= nodeCount; mid++) {
            for (int start = 1; start <= nodeCount; start++) {
                for (int end = 1; end <= nodeCount; end++) {
                    if (start != end && start != mid && end != mid) {
                        map[start][end] = Math.min(map[start][end], map[start][mid] + map[mid][end]);
                    }
                }
            }
        }

        int first = INF;
        int second = INF;
        int result = INF;
        for (int i = 1; i <= nodeCount; i++) {
            for (int j = i + 1; j <= nodeCount; j++) {
                int sum = getSum(i, j);
                if (result > sum) {
                    first = i;
                    second = j;
                    result = sum;
                }
            }
        }

        System.out.println(String.format("%d %d %d", first, second, result * 2));
    }

    public static int getSum(int x, int y) {
        int sum = 0;
        for (int i = 1; i <= nodeCount; i++) {
            if (x != i && y != i)
                sum += Math.min(map[x][i], map[y][i]);
        }
        return sum;
    }
}
