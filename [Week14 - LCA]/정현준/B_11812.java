import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static long nodeCount;
    static int childSize, findCount;
    static final int ROOT = 1;

    public static void main(String[] args) throws IOException {
        // 11812 K진 트리 문제 공식의 수학적 증명 https://www.acmicpc.net/blog/view/93
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        nodeCount = Long.parseLong(st.nextToken());
        childSize = Integer.parseInt(st.nextToken());
        findCount = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        while (findCount-- > 0) {
            st = new StringTokenizer(br.readLine());
            long node1 = Long.parseLong(st.nextToken());
            long node2 = Long.parseLong(st.nextToken());
            if (childSize == 1) {
                sb.append(Math.abs(node1 - node2));
            } else {
                sb.append(find(node1, node2));
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static long find(long node1, long node2) {
        long dist = 0;
        while (node1 != node2) {
            dist++;
            long max = Math.max(node1, node2);
            node1 = Math.min(node1, node2);
            node2 = getParent(max);
        }

        return dist;
    }

    public static long getDepth(long node) {
        if (node == 1)
            return 0;

        long nodeCount = 1;
        long depth = 1;
        while (true) {
            nodeCount += (long) Math.pow(childSize, depth++);
            if (node <= nodeCount)
                break;
        }
        return depth;
    }

    public static long getParent(long node) {
        return (node - 2) / childSize + 1;
    }
}