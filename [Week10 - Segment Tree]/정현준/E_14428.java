import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] numbers;
    static int[] valueTree;
    static int[] indexTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        numbers = new int[N + 1];
        valueTree = new int[N * 4];
        indexTree = new int[N * 4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        init(1, 1, N);

        M = Integer.parseInt(br.readLine());

        // print();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int oper = Integer.parseInt(st.nextToken());
            if (oper == 1) {
                int index = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                // update
                update(1, 1, N, index, value);
                // print();
                continue;
            }
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            // query
            int[] result = query(1, 1, N, start, end);
            sb.append(result[1]).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static int[] init(int node, int left, int right) {
        if (left == right) {
            valueTree[node] = numbers[left];
            indexTree[node] = left;
            return new int[] { numbers[left], left };
        }

        int mid = (left + right) >> 1;
        int child = node << 1;

        int[] result = getMinArr(init(child, left, mid), init(child + 1, mid + 1, right));
        valueTree[node] = result[0];
        indexTree[node] = result[1];

        return result;
    }

    public static int[] update(int node, int left, int right, int index, int value) {
        if (right < index || index < left)
            // return new int[] {Integer.MAX_VALUE , Integer.MAX_VALUE}; // 이 부분 때문에 고생..
            return new int[] { valueTree[node], indexTree[node] };

        if (left == right) {
            valueTree[node] = value;
            indexTree[node] = index;
            return new int[] { valueTree[node], indexTree[node] };
        }

        int mid = (left + right) >> 1;
        int child = node << 1;

        int[] result = getMinArr(update(child, left, mid, index, value),
                update(child + 1, mid + 1, right, index, value));
        valueTree[node] = result[0];
        indexTree[node] = result[1];
        return result;
    }

    public static int[] query(int node, int left, int right, int start, int end) {
        if (end < left || right < start)
            return new int[] { Integer.MAX_VALUE, Integer.MAX_VALUE };

        if (start <= left && right <= end) {
            return new int[] { valueTree[node], indexTree[node] };
        }

        int mid = (left + right) >> 1;
        int child = node << 1;

        return getMinArr(query(child, left, mid, start, end), query(child + 1, mid + 1, right, start, end));
    }

    public static int[] getMinArr(int[] leftArr, int[] rightArr) {
        // 0번 인덱스 : value
        // 1번 인덱스 : index
        // System.out.printf("node %d : %d , %d <-> %d , %d \n" , node, leftArr[0] ,
        // leftArr[1] , rightArr[0] , rightArr[1]);
        int[] result = new int[2];
        if (leftArr[0] == rightArr[0]) {
            result[0] = rightArr[0];
            if (leftArr[1] < rightArr[1]) {
                result[1] = leftArr[1];
            } else {
                result[1] = rightArr[1];
            }
        } else if (leftArr[0] > rightArr[0]) {
            result[0] = rightArr[0];
            result[1] = rightArr[1];
        } else {
            result[0] = leftArr[0];
            result[1] = leftArr[1];
        }
        return result;
    }

    public static void print() {
        for (int i = 1; i <= N * 2; i++) {
            System.out.printf("i : %d , value : %d , index : %d %n", i, valueTree[i], indexTree[i]);
        }
        System.out.println("--------------------------------");
    }
}