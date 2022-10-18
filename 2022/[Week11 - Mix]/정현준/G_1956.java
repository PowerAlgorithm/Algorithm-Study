import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int node = Integer.parseInt(st.nextToken());
        int edge = Integer.parseInt(st.nextToken());

        final int INF = 4000001;
        int[][] map = new int[node + 1][node + 1];

        for (int i = 1; i <= node; i++)
            Arrays.fill(map[i], INF);

        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            map[s][e] = cost;
        }

        int result = Integer.MAX_VALUE;
        for (int mid = 1; mid <= node; mid++) {
            for (int start = 1; start <= node; start++) {
                for (int end = 1; end <= node; end++) {
                    if (start != mid && end != mid && start != end) {
                        map[start][end] = Math.min(map[start][end], map[start][mid] + map[mid][end]);
                        result = Math.min(result, map[start][end] + map[end][start]);
                    }
                }
            }
        }

        System.out.println(result >= 4000001 ? "-1" : result);
    }
}