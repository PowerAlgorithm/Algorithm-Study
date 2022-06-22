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
    static int n;
    static int x;
    static int[] lines;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        lines = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            lines[i] = Integer.parseInt(st.nextToken());
        }
        x = Integer.parseInt(br.readLine());
        Arrays.sort(lines);
        int start = 0;
        int end = n-1;
        int ans = 0;

        while(start < end) {
            int val = lines[start] + lines[end];
            if (val == x) {
                ans += 1;
            }

            if(val < x) {
                start += 1;
            } else {
                end -= 1;
            }
        }
        System.out.println(ans);


        br.close();
    }
}