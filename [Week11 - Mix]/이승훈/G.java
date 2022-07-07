import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/***
 * 키워드
 *
 * 조건
 *
 * 풀이
 *
 */
class Pair implements Comparable<Pair>{
    int start;
    int end;

    @Override
    public String toString() {
        return "Pair{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    Pair(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Pair o) {
        if(this.end == o.end) {
            return this.start - o.start;
        }
        return this.end - o.end;
    }
}
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int[][] board = new int[v+1][];
        for(int i=0; i<v+1; i++) {
            board[i] = new int[v + 1];
            for(int j=0; j<v+1; j++) {
                board[i][j] = 4000001;
            }
        }
        for(int i=0; i<e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            board[a][b] = c;
        }

        for(int k=0; k<v+1; k++) {
            for(int i=0; i<v+1; i++) {
                for(int j=0; j<v+1; j++) {
                    board[i][j] = Math.min(board[i][j], board[i][k] + board[k][j]);
                }
            }
        }
        int ans = 4000001;
        for(int i=0; i<v+1; i++) {
            for(int j=0; j<v+1;j++) {
                int val = board[i][j] + board[j][i];
                if(i == j) {
                    val = board[i][j];
                }
                if(val < 4000001) {
                    ans = Math.min(ans, val);
                }
            }
        }
        System.out.println(ans >= 4000001 ? -1 : ans);
        br.close();
    }
}
