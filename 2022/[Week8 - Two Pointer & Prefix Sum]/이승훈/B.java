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
    static int[] eaten;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int cnt = 0;
        int ans = 0;
        eaten = new int[d+1];
        lines = new int[n];
        for(int i=0; i<n; i++) {
            lines[i] = Integer.parseInt(br.readLine());
        }

        for(int i=0; i<k; i++) {
            if(eaten[lines[i]] == 0) {
                cnt += 1;
            }
            eaten[lines[i]]++;
        }
        ans = cnt;
        for(int i=k; i<n + k; i++) {
            int end = i % n;
            int start = i - k;

            if(ans <= cnt) {
                if(eaten[c] == 0) {
                    ans = cnt + 1;
                } else {
                    ans = cnt;
                }
            }
            if(eaten[lines[end]] == 0) {
                cnt += 1;
            }
            eaten[lines[end]]++;

            if(eaten[lines[start]] == 1) {
                cnt -= 1;
            }
            eaten[lines[start]]--;
        }
        System.out.println(ans);
        br.close();
    }
}
