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
    int num;
    int pos;

    Pair(int num, int pos) {
        this.num = num;
        this.pos = pos;
    }

    @Override
    public int compareTo(Pair o) {
        return this.num - o.num;
    }
}
class Main {
    public static int[][] board;
    public static String boardToString() {
        StringBuilder a = new StringBuilder();
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                a.append(board[i][j]);
            }
        }
        return a.toString();
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};
        board = new int[3][];
        for(int i=0; i<3; i++) {
            st = new StringTokenizer(br.readLine());
            board[i] = new int[3];
            for(int j=0; j<3; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean success = false;
        Queue<String> q = new LinkedList<>();
        HashMap<String, Integer> visited = new HashMap<>();
        q.add(boardToString());
        visited.put(boardToString(), 0);
        while(!q.isEmpty()) {
            String cur = q.poll();
            int move = visited.get(cur);
            int curIdx = cur.indexOf('0');
            int curY = curIdx / 3;
            int curX = curIdx % 3;

            if(cur.equals("123456780")) {
                success = true;
                break;
            }

            for(int i=0; i<4; i++) {
                int ny = curY + dy[i];
                int nx = curX + dx[i];
                int nextIdx = ny * 3 + nx;

                if(ny<0 || 3<=ny || nx<0 || 3<=nx)
                    continue;

                char nextVal = cur.charAt(nextIdx);
                String next = cur.replace(nextVal, 't');
                next = next.replace('0', nextVal);
                next = next.replace('t', '0');

                if (!visited.containsKey(next)) {
                    q.add(next);
                    visited.put(next, move + 1);
                }
            }
        }
        if(success) {
            System.out.println(visited.get("123456780"));
        } else {
            System.out.println(-1);
        }

        br.close();
    }
}
