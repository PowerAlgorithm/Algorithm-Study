import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int m;
    static int n;
    static int k;
    static int[][] board;
    static int[][] squares;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static int[][] visited;
    static ArrayList<Integer> ans;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[m][n];
        for(int i=0; i<k; i++) {                        // 직사각형을 표시
            st = new StringTokenizer(br.readLine());
            int x0 = Integer.parseInt(st.nextToken());
            int y0 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            for(int y=y0; y<y1; y++) {
                for (int x = x0; x < x1; x++) {
                    board[y][x] = 1;
                }
            }
        }
        visited = new int[m][n];
        ans = new ArrayList<>();
        for(int i=0; i<m; i++) {                       
            for(int j=0; j<n; j++) {
                if (board[i][j]!=1 && visited[i][j]==0) {   // 방문 하지 않았고 빈 공간이면 bfs 실행
                    visited[i][j] = 1;
                    bfs(i, j);
                }
            }
        }
        ans.sort(Comparator.naturalOrder());
        System.out.println(ans.size());
        for(int a : ans) {
            System.out.print(a + " ");
        }
    }
    public static void bfs(int y, int x) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y, x});

        int cnt = 1;

        while(!q.isEmpty()) {
            int[] val = q.poll();
            int curY = val[0];
            int curX = val[1];

            for(int i=0; i<4; i++) {
                int ny = curY + dy[i];
                int nx = curX + dx[i];
                if (0 <= ny && ny < m && 0 <= nx && nx < n && board[ny][nx] != 1 && visited[ny][nx]==0) {
                    q.add(new int[]{ny, nx});
                    visited[ny][nx] = 1;
                    cnt += 1;
                }
            }
        }
        ans.add(cnt);
    }
}