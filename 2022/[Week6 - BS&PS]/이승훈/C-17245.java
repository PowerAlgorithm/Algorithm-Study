import java.util.*;
import java.io.*;

/***
 * 키워드
 *
 * 조건
 *
 * 풀이
 *
 */

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int min = 0;
        int max = 0;
        long totalNum = 0;
        int[][] board = new int[n][];
        for(int i=0; i<n; i++) {
            board[i] = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, board[i][j]);
                totalNum += board[i][j];
            }
        }

        while (min <= max) {
            int mid = (min + max) / 2;
            long num = 0;

            for(int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    num += Math.min(mid, board[i][j]);
                }
            }
            boolean success = (totalNum <= num * 2);
            if(success) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        System.out.println(min);
        br.close();
    }
}