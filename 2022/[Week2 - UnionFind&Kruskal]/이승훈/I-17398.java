import java.io.*;
import java.util.*;

/**
 * 키워드
 * - 총 Q번 통신탑 간의 연결을 제거함으로써 하나의 통신망을 여러 개의 통신망으로 분리하려고 한다.
 * - 나눠진 두 개의 통신망에 속한 통신탑들의 갯수의 곱 == 각 집합의 갯수를 알아야됨
 *
 * 조건
 *
 *
 * 풀이
 * - 분리를 반대 순서로 진행하면 연결이 된다.
 * - 각 집합을 연결하는 알고리즘 == union find
 * - 집합의 갯수를 알아야되므로 weighted union find
 */
class Main {
    static int[] parent;
    static int N;
    static int M;
    static int Q;

    public static int find(int a)
    {
        if(parent[a] < 0)
            return a;
        return parent[a] = find(parent[a]);
    }

    public static void weightedUnion(int a, int b)
    {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot)  // 같은 위치라면 (같은 root라면)
            return;
        if(parent[aRoot] <= parent[bRoot]){
            parent[aRoot] = parent[aRoot] + parent[bRoot];
            parent[bRoot] = aRoot;
        }
        else {
            parent[bRoot] = parent[aRoot] + parent[bRoot];
            parent[aRoot] = bRoot;
        }
    }


    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        initParent(N+1);
        int[][] conn = new int[M][];
        Stack<Integer> seq = new Stack<>();
        boolean[] visited = new boolean[M+1];

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            conn[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
        for(int i=0; i<Q; i++) {
            int val = Integer.parseInt(br.readLine());
            seq.add(val);
            visited[val] = true;
        }
        for(int i=0; i<M; i++) {
            int a = conn[i][0];
            int b = conn[i][1];
            if(!visited[i+1]) {
                weightedUnion(a, b);
            }
        }
//        Arrays.stream(parent).forEach(System.out::print);
//        System.out.println();
        long ans = 0;
        for(int i=0; i<Q; i++) {
            int s = seq.pop();
            s--;
            int a = conn[s][0];
            int b = conn[s][1];
            if (find(a)!=find(b)) {
//                System.out.println("a, b " + a+", "+ b);
                ans += (parent[find(a)] * parent[find(b)]);
                weightedUnion(a, b);
//                Arrays.stream(parent).forEach(System.out::print);
//                System.out.println();
//                System.out.println("ans " + ans + "\n");
            }
        }
        System.out.println(ans);

        br.close();
    }

    private static void initParent(int n)
    {
        parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = -1;
    }
}