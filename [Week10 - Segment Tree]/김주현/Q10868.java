// 10868번 최솟값 (G1)
/*
<문제 정보>
 1. N개의 수가 있을 때 a번째 ~ b번째 정수중 최솟값 찾기 (최대 100,000번)

<변수 범위>
 1. 1초 / 256MB
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

public class Q10868 {
    static int N,M;
    static int[] arr;
    static int[] tree;

    static final int INF = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        int treeSize = getTreeSize();
        tree = new int[treeSize];

        for (int i=1;i<=N;i++) arr[i] = Integer.parseInt(br.readLine());

        init(1,1,N);
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
            sb.append(query(1,1,N,a,b)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void init(int node, int start, int end) {
        if (start==end) {
            tree[node] = arr[start];
            return;
        }

        int mid = (start+end)/2;
        init(2*node, start, mid);
        init(2*node+1, mid+1, end);
        tree[node] = Math.min(tree[2*node], tree[2*node+1]);
    }

    public static int query (int node, int start, int end, int left, int right) {
        // 1. left~right 범위의 수가 없을 때
        if (left>end || right<start) return INF;

        // 2. left~right 범위의 수만 있을 때
        if (start>=left && end<=right) return tree[node];

        int mid = (start+end)/2;
        return Math.min(query(2*node, start, mid, left,right),query(2*node+1, mid+1, end, left, right));
    }

    public static int getTreeSize() {
        int h = (int) Math.ceil(Math.log(N)/Math.log(2));
        return 1<<(h+1);
    }

    public static void printTree(int treeSize) {
        for (int i=1;i<treeSize;i++) System.out.printf("%d ",tree[i]);
        System.out.println();
    }
}

















