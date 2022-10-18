// 2243번
/*
<문제 정보>
 1.
    - A : 1 사탕 꺼내기 (순위) , A : 2 사탕 넣기 (종류, 개수)

<변수 범위>
 1. 2초 / 128MB
 2. 사탕상자 건드린 횟수 n 1<=N<=100,000
 3. 사탕 종류 1,000,000 이하의 자연수 (작을수록 맛있음)
 4. 사탕 총 개수는 2,000,000,000 넘지 않음


<프로그램 진행>
 1. 사탕상자 건드린

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q2243 {
    static int N;
    static int[] candies = new int[1_000_001];
    static int[] tree;

    static final int START = 0;
    static final int END = 1_000_000;
//    static final int POP = 1;
    static final int PUSH = 2;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int treeSize = getTreeSize(1_000_000);
        tree = new int[treeSize];
        int mode;
        int order;
        int candy;
        int numOfCandy;
        while(N-- >0) {
            st = new StringTokenizer(br.readLine());
            mode = Integer.parseInt(st.nextToken());
            if (mode == PUSH) {
                candy = Integer.parseInt(st.nextToken());
                numOfCandy = Integer.parseInt(st.nextToken());
                candies[candy] += numOfCandy;
                update(1,START, END, candy);
            }
            else {
                order = Integer.parseInt(st.nextToken());
                candy = BS_LowerBound(order);
                candies[candy] -= 1;
                update(1,START, END, candy);
                sb.append(candy).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int BS_LowerBound(int key) {
        int lo = 0;
        int hi = 1_000_000;
        int mid;
        int sum;

        // key가 순위 -> sum이 순위보다 작거나 같으면
        while(lo<hi) {
            mid = (lo+hi)/2;
            sum = query(1, START, END, 1, mid);
            if (sum < key) lo = mid +1;
            else hi = mid;
        }
        return lo;
    }

    public static void init(int node, int start, int end) {
        if (start==end) {
            tree[node] = candies[start];
            return;
        }

        int mid = (start+end)/2;
        init(2*node, start, mid);
        init(2*node+1, mid+1, end);
        tree[node] = tree[2*node]+tree[2*node+1];
    }

    public static void update(int node, int start, int end, int idx) {
//        System.out.printf("start : %d, end : %d, idx : %d\n", start, end, idx);
        if (idx<start || idx>end) return;

        if (start==end) {
            tree[node] = candies[start];
            return;
        }

        int mid = (start+end)/2;
        update(2*node, start, mid, idx);
        update(2*node+1, mid+1, end, idx);
        tree[node] = tree[2*node]+tree[2*node+1];
    }

    public static int query(int node, int start, int end, int left, int right) {
        if (left>end || right<start) return 0;

        if (start>=left && end<=right) return tree[node];

        int mid = (start+end)/2;
        return query(2*node, start, mid, left, right) + query(2*node+1, mid+1, end, left, right);
    }

    public static int getTreeSize(int size) {
        int h = (int) Math.ceil(Math.log(size)/Math.log(2));
        return 1<<(h+1);
    }

    public static void printTree(int treeSize) {
        for (int i=1;i<treeSize;i++) System.out.printf("%d ",tree[i]);
        System.out.println();
    }
}
