// 14428번 수열과 쿼리 16 (G1)
/*
<문제 정보>
 1. query1. A_i -> v
 2. query2. 크기가 가장 작은 값의 인덱스 출력 (작은게 여러개라면 인덱스가 작은 것 출력)

<변수 범위>
 1. 2초 / 512MB
 2. 1<=N<=100,000
 3. 수열의 각 수 1<=A_i<=1,000,000,000
 4. 1<=M<=100,000

<프로그램 진행>
 1.

<필요 함수>
 1.

 */

// tree를 인덱스로 짜고 조회하기 (~like parametric Search)

import java.io.*;
import java.util.StringTokenizer;

public class Q14428 {
    static int N,M;
    static int[] arr;
    static int[] tree;

    static final int CHANGE = 1;
    static final int IMPOSSIBLE = 0;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        int treeSize = getTreeSize();
        arr = new int[N+1];
        tree = new int[treeSize];

        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) arr[i] = Integer.parseInt(st.nextToken());

        init(1,1,N);
        arr[IMPOSSIBLE] = INF;
        /*printTree(treeSize);
        int n1 = 3;
        int n2 = 7;
        System.out.printf("%d~%d 최솟값 : %d\n",n1, n2,query(1,1,N,n1,n2));*/

        M = Integer.parseInt(br.readLine());
        int mode, a, b;
        while(M-- >0) {
            st = new StringTokenizer(br.readLine());
            mode = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if (mode == CHANGE) update(1,1,N,a,b);
            else sb.append(query(1,1,N,a,b)).append("\n");
        }


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void init(int node, int start, int end) {
        if (start == end) {
            tree[node] = start;
            return;
        }

        int mid = (start+end)/2;
        init(2*node, start, mid);
        init(2*node+1, mid+1, end);
        // 배열의 값이 똑같다면 인덱스 중 작은 것을 저장
        if (arr[tree[2*node]]==arr[tree[2*node+1]]) tree[node] = Math.min(tree[2*node], tree[2*node+1]);
        // 배열의 값이 다르다면 작은 값의 인덱스를 저장
        else tree[node] = arr[tree[2*node]] < arr[tree[2*node+1]] ? tree[2*node] : tree[2*node+1];
    }

    public static void update(int node, int start, int end, int idx, int value) {
        if (idx<start || idx>end) return;

        if (start==end) {
            // 이때는 start 값이나 tree[node] 값이나 같음
            arr[tree[node]] = value;
            return;
        }

        int mid = (start+end)/2;
        update(2*node, start, mid, idx, value);
        update(2*node+1, mid+1, end, idx, value);
        // 배열의 값이 똑같다면 인덱스 중 작은 것을 저장
        if (arr[tree[2*node]]==arr[tree[2*node+1]]) tree[node] = Math.min(tree[2*node], tree[2*node+1]);
            // 배열의 값이 다르다면 작은 값의 인덱스를 저장
        else tree[node] = arr[tree[2*node]] < arr[tree[2*node+1]] ? tree[2*node] : tree[2*node+1];
    }

    public static int query(int node, int start, int end, int left, int right) {
        // IMPOSSIBLE 은 0을 전달하는데 arr[0]에는 INF 가 저장되어 있음
        if (left>end || right<start) return IMPOSSIBLE;

        if (start>=left && end<=right) return tree[node];

        int mid = (start+end)/2;
        int leftChild = query(2*node, start, mid, left, right);
        int rightChild = query(2*node+1, mid+1, end, left, right);
        if (arr[leftChild]==arr[rightChild]) return Math.min(leftChild, rightChild);
        else return arr[leftChild] < arr[rightChild] ? leftChild : rightChild;
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




















