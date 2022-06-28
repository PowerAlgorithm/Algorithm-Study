import java.io.*;
import java.util.*;

class Main {
    static int[] numbers;
    static int[] tree;
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[N + 1];
        tree = new int[N * 4];

        Arrays.fill(tree, Integer.MAX_VALUE);

        for (int i = 1; i <= N; i++)
            numbers[i] = Integer.parseInt(br.readLine());

        // 세그먼트 트리 생성
        init(1, 1, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            sb.append(query(1, 1, N, left, right)).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static int init(int node, int start, int end) {
        if (start == end) {
            tree[node] = numbers[start];
            return tree[node];
        }

        int mid = (start + end) >> 1;
        int child = node << 1;

        return tree[node] = Math.min(init(child, start, mid), init(child + 1, mid + 1, end));
    }

    public static int query(int node, int start, int end, int left, int right) {
        if (right < start || end < left) {
            return Integer.MAX_VALUE;
        }

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) >> 1;
        int child = node << 1;

        return Math.min(query(child, start, mid, left, right), query(child + 1, mid + 1, end, left, right));
    }
}