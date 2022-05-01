import java.io.*;
import java.util.*;

/**
 * 키워드
 * - 위원회를 구성 -> 그룹 구성 -> union find?
 * - 가장 적은 사람을 거치는 경로로 의견 전달 -> 최단 경로
 *
 *
 * 조건
 * - 서로 아는 사람은 같은 위원회에 속함
 * - 위원회 수는 최대가 되야함
 *
 * 풀이
 *
 */
class Main {
    static int[] parent;
    static int N;
    static int[][] relationship;
    static int[] commDepth;

    public static int find(int a)
    {
        if(a == parent[a])
            return a;
        return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b)
    {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot < bRoot)
            parent[bRoot] = aRoot;
        else
            parent[aRoot] = bRoot;
    }

    public static void bfs(int start, int parent)
    {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start, 1});     // 위치, cnt
        boolean[] visited = new boolean[N+1];
        visited[start] = true;
        int depth = 0;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            depth = Math.max(depth, cur[1]);
            for(int next=0; next<N+1; next++) {
                if(relationship[cur[0]][next] == 1 && !visited[next]) {
                    visited[next] = true;
                    q.add(new int[]{next, cur[1]+1});
                }
            }
        }
        if (commDepth[parent] == 0)
            commDepth[parent] = depth;
        else
            commDepth[parent] = Math.min(commDepth[parent], depth);
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine().strip());
        int M = Integer.parseInt(br.readLine().strip());

        initParent(N);
        relationship = new int[N+1][N+1];
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            relationship[a][b] = 1;
            relationship[b][a] = 1;
        }
        for(int i=0; i<N+1; i++) {
            for(int j=0; j<N+1; j++) {
                if (relationship[i][j]==1 && find(i)!=find(j)) {
                    union(i, j);
                }
            }
        }

        commDepth = new int[N+1];
        int[] realAns = new int[N + 1];
        for(int i=1; i<N+1; i++) {
            int before = commDepth[find(i)];
            bfs(i, find(i));
            int after = commDepth[find(i)];

            if(before != after) {
                realAns[find(i)] = i;
            }
        }
        int[] ints = Arrays.stream(realAns)
                .filter(value -> value != 0)
                .sorted().toArray();
        System.out.println(ints.length);
        Arrays.stream(ints).forEach(System.out::println);

        br.close();
    }

    private static void initParent(int n)
    {
        parent = new int[n + 1];
        for (int i = 0; i < n + 1; i++)
            parent[i] = i;
    }
}