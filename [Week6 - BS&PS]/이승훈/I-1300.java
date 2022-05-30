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
        int k = Integer.parseInt(br.readLine());
        long min = 0L;
        long max = (long) n * n;

        while(min <= max) {
            long mid = (min + max) / 2;

            long totalNum = 0;
            for(int i=1; i<n+1; i++) {
                totalNum += Math.min(n, mid / i);
            }
            if(totalNum >= k) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        System.out.println(min);

        br.close();
    }
}