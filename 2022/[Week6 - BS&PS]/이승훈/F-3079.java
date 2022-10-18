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
    static int[] entrances;
    static int n;
    static int m;

    public static boolean isPossible(long time) {
        long totalNum = 0;
        for(int i=0; i<n; i++) {
            totalNum += (time / entrances[i]);
        }
        if(totalNum >= m) {
            return true;
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        entrances = new int[n];
        int maxVal = 0;
        for(int i=0; i<n; i++) {
            entrances[i] = Integer.parseInt(br.readLine());
            maxVal = Math.max(maxVal, entrances[i]);
        }
        long min = 1L;
        long max = 1000000000L * maxVal;
        while(min<=max) {
            long time = (min + max)/ 2;

            if (isPossible(time)) {
                max = time - 1;
            } else {
                min = time + 1;
            }
        }
        System.out.println(min);
        br.close();
    }
}
