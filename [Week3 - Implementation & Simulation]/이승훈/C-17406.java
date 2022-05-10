import java.io.*;
import java.util.*;

/***
 * 키워드
 *
 * 조건
 *
 * 풀이
 *
 */

class Main {
    public static int[][] board;
    public static int[][] fixedBoard;
    public static int n, m, k;
    public static int r, c, s;
    public static int[][] rotates;
    public static List<Integer> kSeq;
    public static List<Integer> ansList;


    public static void dfs(int cnt) {
        if (cnt >= k) {
            for(int i=0; i<n; i++) {
                for(int j=0; j<m; j++) {
                    board[i][j] = fixedBoard[i][j];
                }
            }
            for(int i : kSeq) {
                r = rotates[i][0];
                c = rotates[i][1];
                s = rotates[i][2];
                while (rotate()) {
                    s -= 1;
                }
            }
            int ans = Integer.MAX_VALUE;
            for(int[] b : board) {
                int sumVal = Arrays.stream(b).sum();
                ans = ans > sumVal ? sumVal : ans;
                ansList.add(ans);
            }
            return;
        }
        for(int i=0; i<k; i++) {
            if (!kSeq.contains(i)) {
                kSeq.add(i);
                dfs(cnt + 1);
                kSeq.remove(kSeq.size() - 1);
            }
        }
    }

    public static Boolean rotate() {
        int leftY = r - s - 1;
        int leftX = c - s - 1;
        int rightY = r + s - 1;
        int rightX = c + s - 1;
        if (leftY == rightY)
            return false;

        int[][] cloneBoard = new int[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                cloneBoard[i][j] = board[i][j];
            }
        }

        for(int i=leftX; i<rightX; i++) {
            cloneBoard[leftY][i+1] = board[leftY][i];
        }
        for(int i=leftY; i<rightY; i++) {
            cloneBoard[i+1][rightX] = board[i][rightX];
        }
        for(int i=rightX; i>leftX; i--) {
            cloneBoard[rightY][i-1] = board[rightY][i];
        }
        for(int i=rightY; i>leftY; i--) {
            cloneBoard[i-1][leftX] = board[i][leftX];
        }
        board = cloneBoard;
        return true;

    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        fixedBoard = new int[n][m];
        rotates = new int[k][3];
        kSeq = new ArrayList<>();
        ansList = new ArrayList<>();

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                fixedBoard[i][j] = board[i][j];
            }
        }
        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            rotates[i][0] = Integer.parseInt(st.nextToken());
            rotates[i][1] = Integer.parseInt(st.nextToken());
            rotates[i][2] = Integer.parseInt(st.nextToken());
        }

        dfs(0);
        System.out.println(ansList.stream().min(Comparator.naturalOrder()).get());

        br.close();
    }


}