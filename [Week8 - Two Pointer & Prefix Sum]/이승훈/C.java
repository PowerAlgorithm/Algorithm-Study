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

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] lines = new int[n+1];
        st = new StringTokenizer(br.readLine());
        lines[1] = Integer.parseInt(st.nextToken());
        for(int i=1; i<n; i++) {
            int val = Integer.parseInt(st.nextToken());
            lines[i+1] = lines[i] + val;
        }
        int start = 0;
        int end = 1;
        int ans = 100000;

        while (end < n+1 && start <= end) {
            int sumVal = lines[end] - lines[start];

            if(start==end && end < n+1) {
                end += 1;
                continue;
            }

            if(sumVal >= s) {
                ans = Math.min(ans, end - start);
                start += 1;
            } else {
                end += 1;
            }
        }
        if (ans == 100000) {
            System.out.println(0);
        } else {
            System.out.println(ans);
        }
        br.close();
    }
}






