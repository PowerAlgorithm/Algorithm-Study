// 2357번 최솟값과 최댓값 (G1)
/*
<문제 정보>
 1. a번째부터 b번째까지 최댓값 최솟값 찾기

<변수 범위>
 1. 1초 / 192MB
 2. 1<=N<=100,000
 3. M개의 쌍 1<=M<=100,000
 5. 각각의 정수들은 1,000,000,000 이하의 자연수

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q2357 {
    static int N,M;
    static int[] arr;
    static int[] tree_min;
    static int[] tree_max;

    static final int INF = Integer.MAX_VALUE;
    static final int MINUS_INF = 0;
    static final boolean MIN = true;
    static final boolean MAX = false;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        int treeSize = getTreeSize();
        tree_min = new int[treeSize];
        tree_max = new int[treeSize];

        for (int i=1;i<=N;i++) arr[i] = Integer.parseInt(br.readLine());

        init(MIN,1,1,N);
        init(MAX,1,1,N);
        /*printTree(treeSize);
        int a=2;
        int b=5;
        System.out.printf("%d번째부터 %d번째까지 최솟값 : %d\n",a, b, query(1,1,N,a,b));
*/
        int a,b;
        while(M-- >0) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            sb.append(query_min(1,1,N,a,b)).append(" ").append(query_max(1,1,N,a,b)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void init(boolean isMinMode, int node, int start, int end) {
        if (start==end) {
            if (isMinMode) tree_min[node] = arr[start];
            else tree_max[node] = arr[start];
            return;
        }

        int mid = (start+end)/2;
        if (isMinMode) {
            init(MIN, 2*node, start, mid);
            init(MIN, 2*node+1, mid+1, end);
            tree_min[node] = Math.min(tree_min[2*node], tree_min[2*node+1]);
        }
        else {
            init(MAX, 2*node, start, mid);
            init(MAX, 2*node+1, mid+1, end);
            tree_max[node] = Math.max(tree_max[2*node], tree_max[2*node+1]);
        }
    }

    public static int query_min (int node, int start, int end, int left, int right) {
        // 1. left~right 범위의 수가 없을 때
        if (left>end || right<start) return INF;

        // 2. left~right 범위의 수만 있을 때
        if (start>=left && end<=right) return tree_min[node];

        int mid = (start+end)/2;
        return Math.min(query_min(2*node, start, mid, left,right),query_min(2*node+1, mid+1, end, left, right));
    }

    public static int query_max (int node, int start, int end, int left, int right) {
        // 1. left~right 범위의 수가 없을 때
        if (left>end || right<start) return MINUS_INF;

        // 2. left~right 범위의 수만 있을 때
        if (start>=left && end<=right) return tree_max[node];

        int mid = (start+end)/2;
        return Math.max(query_max(2*node, start, mid, left,right),query_max(2*node+1, mid+1, end, left, right));
    }

    public static int getTreeSize() {
        int h = (int) Math.ceil(Math.log(N)/Math.log(2));
        return 1<<(h+1);
    }

    /*public static void printTree(int treeSize) {
        for (int i=1;i<treeSize;i++) System.out.printf("%d ",tree[i]);
        System.out.println();
    }*/
}
