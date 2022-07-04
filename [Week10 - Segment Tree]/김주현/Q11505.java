// 11505번 구간 곱 구하기 (G1)
/*
<문제 정보>
 1. 수의 변경이 빈번히 일어날 때 구간 곱 구하기
 2. 구간의 곱을 1,000,000,007 로 나눈 나머지를 출력

<변수 범위>
 1. 1초 / 256MB
 2. 1<=N<=1,000,000
 3. 수의 변경이 일어나는 횟수 1<=M<=10,000
 4. 구간의 합을 구하는 횟수 1<=K<=10,000
 5. 입력으로 주어지는 모든 수는 1,000,000 음이 아닌 정수


<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q11505 {
    static int N, M, K;
    static int[] arr;
    static long[] tree;

    static final int CHANGE = 1;
    static final int DIV = 1_000_000_007;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        int treeSize = getTreeSize();
        tree = new long[treeSize];

        // arr 배열의 N개의 원소 입력
        for (int i=1;i<=N;i++) arr[i] = Integer.parseInt(br.readLine());

        // Segment tree initialization
        init(1,1,N);
        /*for (int i=1;i<treeSize;i++) System.out.printf("%d ",tree[i]);
        System.out.println();

        update(1,1,N,3,7);
        for (int i=1;i<treeSize;i++) System.out.printf("%d ",tree[i]);
        System.out.println();

        System.out.printf("구간 곱 : %d\n",query(1,1,N,2,4));*/

        int mode, b, c;
        for (int i=0;i<M+K;i++) {
            st = new StringTokenizer(br.readLine());
            mode = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if (mode == CHANGE) {
                update(1,1,N,b,c);
                arr[b] = c;
            }
            else sb.append(query(1,1,N,b,c)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void init(int node, int start, int end) {
//        System.out.printf("node : %d, start : %d, end : %d\n",node, start,end);
        if (start == end) {
            tree[node] = arr[start];
            return;
        }
        int mid = (start+end)/2;
        init(2*node, start, mid);
        init(2*node+1, mid+1, end);
        tree[node] = (tree[2*node] * tree[2*node+1])%DIV;
    }

    public static void update(int node, int start, int end, int keyIdx, int value) {
        if (keyIdx<start || keyIdx>end) return;

        if (start==end) {
            tree[node] = value;
            return;
        }
        int mid = (start+end)/2;
        update(2*node, start, mid, keyIdx, value);
        update(2*node+1, mid+1, end, keyIdx, value);
        tree[node] = (tree[2*node] * tree[2*node+1])%DIV;
    }

    public static long query(int node, int start, int end, int left, int right) {
        // 1. left~right 범위의 수가 없는 경우
        if (left>end || right<start) return 1;

        // 2. left~right 범위의 수만 있는 경우 ( <= 재귀 탈출 조건 (최종적으로 left~right의 범위가 더 넓어짐) )
        if (start>=left && end<=right) return tree[node];

        // 3. left~right 범위의 일부 수만 있는 경우
        int mid = (start+end)/2;
        return (query(2*node,start,mid,left,right)*query(2*node+1,mid+1,end,left,right))%DIV;
    }

    public static int getTreeSize() {
        int height = (int) Math.ceil(Math.log(N)/Math.log(2));
        return 1<<(height+1);
    }
}













