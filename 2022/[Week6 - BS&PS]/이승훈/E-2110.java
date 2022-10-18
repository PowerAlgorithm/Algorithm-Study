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
    static int[] pos;
    static int n;
    static int c;

    public static boolean isPossible(int dist) {
        int num = 0;
        int curPos = pos[0];
        for(int i=0; i<n; i++) {
            if(curPos <= pos[i]) {
                num += 1;
                curPos = pos[i] + dist;
            }
        }
        if(num >= c) {
            return true;
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        pos = new int[n];
        for(int i=0; i<n; i++) {
            pos[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(pos);
        int min = 1;
        int max = 1000000000;
        while(min <= max) {
            int dist = (min + max) / 2;

            if(isPossible(dist)) {
                min = dist + 1;
            } else {
                max = dist - 1;
            }
        }
        System.out.println(max);

        br.close();
    }
}