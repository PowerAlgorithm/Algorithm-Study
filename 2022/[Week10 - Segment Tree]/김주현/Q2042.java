// 2042번 구간 합 구하기 (G1)
/*
<문제 정보>
 1. N개의 수가 주어지고 중간에 수의 변경이 빈번히 일어난다.
 2. 중간에 주어지는 부분의 합을 출력
 3. a==1 -> change bth Num to c
 4. a==2 -> get Sum of bth Num to cth Num

<변수 범위>
 1. 2초 / 256MB
 2. 1<=N<=1,000,000
 3. 수의 변경이 일어나는 횟수 1<=M<=10,000
 4. 구간의 합을 구하는 횟수 1<=K<=10,000
 5. 입력으로 주어지는 모든 수는 -2^63 이상 2^63-1 이하
 6. 정답 또한 입력과 같은 범위

<프로그램 진행>
 1. init : tree 만들기
 2. Update : 숫자 바꾸면서 tree 갱신하기
 3. Query : 구간 합 찾기

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q2042 {
    static int N, M, K;
    static long[] arr;
    static long[] tree;

    static final int CHANGE = 1;
    static final int GET_SUM = 2;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new long[N+1];
        int treeSize = getTreeSize();
        tree = new long[treeSize];

        // arr 배열의 N개의 원소 입력
        for (int i=1;i<=N;i++) arr[i] = Long.parseLong(br.readLine());

        // Segment tree initialization
        init(1,1,N);

        int mode, b;
        long c;
        for (int i=0;i<M+K;i++) {
            st = new StringTokenizer(br.readLine());
            mode = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Long.parseLong(st.nextToken());
            if (mode == CHANGE) {
                update(1,1,N,b,c-arr[b]);
                arr[b] = c;
            }
            else sb.append(query(1,1,N,b,(int)c)).append("\n");
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
        tree[node] = tree[2*node] + tree[2*node+1];
    }

    public static void update(int node, int start, int end, int keyIdx, long diff) {
        if (keyIdx<start || keyIdx>end) return;

        tree[node] += diff;
        if (start==end) return;

        int mid = (start+end)/2;
        update(2*node, start, mid, keyIdx, diff);
        update(2*node+1, mid+1, end, keyIdx, diff);
    }

    public static long query(int node, int start, int end, int left, int right) {
        // 1. left~right 범위의 수가 없는 경우
        if (left>end || right<start) return 0;

        // 2. left~right 범위의 수만 있는 경우 ( <= 재귀 탈출 조건 (최종적으로 left~right의 범위가 더 넓어짐) )
        if (start>=left && end<=right) return tree[node];

        // 3. left~right 범위의 일부 수만 있는 경우
        int mid = (start+end)/2;
        return query(2*node,start,mid,left,right) + query(2*node+1,mid+1,end,left,right);
    }

    public static int getTreeSize() {
        int height = (int) Math.ceil(Math.log(N)/Math.log(2));
        return 1<<(height+1);
    }
}
