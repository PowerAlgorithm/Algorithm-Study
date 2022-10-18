// 7578번 공장 (P5)
/*
<문제 정보>
 1.

<변수 범위>
 1. 1초 / 256MB
 2. 공장 기계 1<=N<=500,000
 3. 식별번호 1,000,000 이하의 음이 아닌 정수

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Q7578 {
    static int N;
    static int[] firstRow;
    static HashMap<Integer, Integer> secondRow = new HashMap<>();
    static boolean[] visited;
    // 방문된 곳의 개수를 세는 tree
    static int[] tree;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        firstRow = new int[N];
        visited = new boolean[N];
        int treeSize = getTreeSize();
        tree = new int[treeSize];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) firstRow[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) secondRow.put(Integer.parseInt(st.nextToken()),i);

        long count = 0;
        init(1,0,N-1);

        int secondRowIdx;
        for (int cable : firstRow) {
            secondRowIdx = secondRow.get(cable);
            visited[secondRowIdx] = true;
            update(1,0,N-1,secondRowIdx);
            count += query(1,0,N-1,secondRowIdx+1,N-1);
        }
        sb.append(count);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void init(int node, int start, int end) {
        if (start==end) {
            tree[node] = visited[start] ? 1 : 0;
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
            tree[node] = 1;
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

    public static int getTreeSize() {
        int h = (int) Math.ceil(Math.log(N)/Math.log(2));
        return 1<<(h+1);
    }

    public static void printTree(int treeSize) {
        for (int i=1;i<treeSize;i++) System.out.printf("%d ",tree[i]);
        System.out.println();
    }
}
