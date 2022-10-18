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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] lines = new int[k];
        for(int i=0; i<k; i++) {
            lines[i] = Integer.parseInt(br.readLine());
        }

        long left=1;
        long right = Arrays.stream(lines).max().getAsInt();

        while (left <= right) {
            long mid = (left + right) / 2;

            int totalNum = 0;
            for(int i=0; i<k; i++) {
                totalNum += lines[i] / mid;
            }

            if (totalNum >= n) {
                left = mid + 1;
            } else {
                right = mid -1;
            }
        }
        System.out.println(right);
        br.close();
    }
}