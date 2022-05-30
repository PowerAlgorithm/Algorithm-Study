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
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] cookies = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            cookies[i] = Integer.parseInt(st.nextToken());
        }
        int left = 1;
        int right = Arrays.stream(cookies).max().getAsInt();
        while (left <= right) {
            int mid = (left + right) / 2;

            int totalNum = 0;
            for(int i=0; i<N; i++) {
                totalNum += (cookies[i] / mid);
            }
            if (totalNum >= M) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
//        int totalNum = 0;
//        for(int i=0; i<N; i++) {
//            totalNum += (cookies[i] / right);
//        }
//        if (totalNum == M)
            System.out.println(right);
//        else
//            System.out.println(0);
        br.close();
    }
}