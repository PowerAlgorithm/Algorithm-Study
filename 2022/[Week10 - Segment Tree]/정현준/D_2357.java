import java.io.*;
import java.util.*;

class Main {
    static int[] numbers;
    static int[] minTree;
    static int[] maxTree;
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[N + 1];
        minTree = new int[N * 4];
        maxTree = new int[N * 4];

        for (int i = 1; i <= N; i++)
            numbers[i] = Integer.parseInt(br.readLine());

        init(minTree, 1, 1, N, true);
        init(maxTree, 1, 1, N, false);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            sb.append(query(minTree, 1, 1, N, left, right, true)).append(" ");
            sb.append(query(maxTree, 1, 1, N, left, right, false)).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static int init(int[] tree, int node, int start, int end, boolean isMinTree) {
        if (start == end) {
            tree[node] = numbers[start];
            return tree[node];
        }

        int mid = (start + end) >> 1;
        int child = node << 1;

        if (isMinTree) {
            return tree[node] = Math.min(init(tree, child, start, mid, isMinTree),
                    init(tree, child + 1, mid + 1, end, isMinTree));
        }
        return tree[node] = Math.max(init(tree, child, start, mid, isMinTree),
                init(tree, child + 1, mid + 1, end, isMinTree));
    }

    public static int query(int[] tree, int node, int start, int end, int left, int right, boolean isMinTree) {
        if (right < start || end < left)
            return isMinTree ? Integer.MAX_VALUE : Integer.MIN_VALUE;

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) >> 1;
        int child = node << 1;

        if (isMinTree) {
            return Math.min(query(tree, child, start, mid, left, right, isMinTree),
                    query(tree, child + 1, mid + 1, end, left, right, isMinTree));
        }
        return Math.max(query(tree, child, start, mid, left, right, isMinTree),
                query(tree, child + 1, mid + 1, end, left, right, isMinTree));
    }
}
