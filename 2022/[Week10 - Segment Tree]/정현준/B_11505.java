import java.io.*;
import java.util.*;

class Main {
    static long[] numbers;
    static long[] tree;
    static final long MOD = 1_000_000_007L;
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

        // 120
        // 6 20
        // 2 3 4 5
        // 1 2

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int oper = Integer.parseInt(st.nextToken());
            if (oper == 1) {
                // 변경
                int index = Integer.parseInt(st.nextToken());
                long value = Long.parseLong(st.nextToken());
                numbers[index] = value;
                update(1, 1, N, index, value);
            } else {
                // 합
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                sb.append(query(1, 1, N, Math.min(left, right), Math.max(left, right))).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    public static long init(int node, int start, int end) {
        if (start == end) {
            tree[node] = numbers[start];
            return tree[node];
        }

        int mid = (start + end) >> 1;
        int child = node << 1;

        long leftCalc = init(child, start, mid) % MOD;
        long rightCalc = init(child + 1, mid + 1, end) % MOD;

        return tree[node] = leftCalc * rightCalc % MOD;
    }

    public static long update(int node, int start, int end, int index, long value) {
        if (index < start || index > end)
            return tree[node];

        if (start == end) {
            tree[node] = value;
            return tree[node];
        }

        int mid = (start + end) >> 1;
        int child = node << 1;

        long leftCalc = update(child, start, mid, index, value) % MOD;
        long rightCalc = update(child + 1, mid + 1, end, index, value) % MOD;

        return tree[node] = leftCalc * rightCalc % MOD;
    }

    public static long query(int node, int start, int end, int left, int right) {
        if (right < start || end < left) {
            return 1L;
        }

        if (left <= start && right >= end) {
            return tree[node];
        }

        int mid = (start + end) >> 1;
        int child = node << 1;

        long leftCalc = query(child, start, mid, left, right) % MOD;
        long rightCalc = query(child + 1, mid + 1, end, left, right) % MOD;

        return leftCalc * rightCalc % MOD;
    }
}
