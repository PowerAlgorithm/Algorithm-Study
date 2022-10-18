import java.io.*;
import java.util.*;

class Main {
    static long[] numbers;
    static long[] tree;
    static int N, M, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        numbers = new long[N + 1];
        tree = new long[N * 4];

        for (int i = 1; i <= N; i++)
            numbers[i] = Long.parseLong(br.readLine());

        // 세그먼트 트리 생성
        init(1, 1, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int oper = Integer.parseInt(st.nextToken());
            if (oper == 1) {
                // 변경
                int index = Integer.parseInt(st.nextToken());
                long value = Long.parseLong(st.nextToken());
                long diff = value - numbers[index];
                update(1, 1, N, index, diff);
                numbers[index] = value;
            } else {
                // 합
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                sb.append(query(1, 1, N, left, right)).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    public static long init(int node, int start, int end) {
        // 말단 노드
        if (start == end) {
            tree[node] = numbers[start];
            return tree[node];
        }

        int mid = start + end >> 1;
        int child = node << 1;

        return tree[node] = init(child, start, mid) + init(child + 1, mid + 1, end);
    }

    public static void update(int node, int start, int end, int index, long diff) {
        if (start > index || index > end) {
            return;
        }

        tree[node] += diff;

        if (start != end) {
            int mid = start + end >> 1;
            int child = node << 1;
            update(child, start, mid, index, diff);
            update(child + 1, mid + 1, end, index, diff);
        }
    }

    public static long query(int node, int start, int end, int left, int right) {
        if (right < start || end < left)
            return 0;

        if (left <= start && end <= right)
            return tree[node];

        int mid = start + end >> 1;
        int child = node << 1;
        return query(child, start, mid, left, right) + query(child + 1, mid + 1, end, left, right);
    }
}
