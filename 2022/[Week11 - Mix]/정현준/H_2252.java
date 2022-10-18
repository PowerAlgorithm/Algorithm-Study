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
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nodeCount = Integer.parseInt(st.nextToken());
        int edgeCount = Integer.parseInt(st.nextToken());

        int[] degree = new int[nodeCount];
        List<List<Integer>> edges = new ArrayList<List<Integer>>();

        for (int i = 0; i < nodeCount; i++)
            edges.add(new ArrayList<Integer>());

        for (int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken()) - 1;
            int second = Integer.parseInt(st.nextToken()) - 1;
            edges.get(first).add(second);
            degree[second]++;
        }

        ArrayDeque<Integer> queue = new ArrayDeque<Integer>();

        List<Integer> nodes = getStartNodes(degree);
        for (int startNode : nodes) {
            queue.offer(startNode);
            while (!queue.isEmpty()) {
                int node = queue.poll();
                sb.append(node + 1).append(" ");
                for (int near : edges.get(node)) {
                    if (degree[near] - 1 >= 0) {
                        if (--degree[near] == 0) {
                            queue.offer(near);
                        }
                    }
                }
            }
        }

        System.out.println(sb.toString());
    }

    public static List<Integer> getStartNodes(int[] degree) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) {
                list.add(i);
            }
        }
        return list;
    }
}