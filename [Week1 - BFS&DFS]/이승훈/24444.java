import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int r;
    static List<Integer>[] graph;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        for(int i=0; i<n+1; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }
        for(int i=0; i<n+1; i++) {
            graph[i].sort(Comparator.naturalOrder());
        }
        bfs();
        for(int i=1; i<visited.length; i++) {
            System.out.println(visited[i]);
        }
    }

    public static void bfs() {
        visited = new int[n + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(r);
        int cnt = 1;
        visited[r] = cnt;

        while(!q.isEmpty()) {
            int cur = q.poll();
            for (int next : graph[cur]) {
                if(visited[next]==0) {
                    cnt += 1;
                    visited[next] = cnt;
                    q.add(next);
                }
            }
        }
    }
}