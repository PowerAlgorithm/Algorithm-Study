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
    static int m;
    static int k;
    static int[] cards;
    static int[] chulsu;

    public static int binarySearch(int target) {
        int left = 0;
        int right = m-1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(cards[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right+1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        cards = new int[m];
        chulsu = new int[m];
        Set<Integer> minsu = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<k; i++) {
            chulsu[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cards);

        for(int i=0; i<k; i++) {
            int pos = binarySearch(chulsu[i]);

            while (minsu.contains(pos)) {
                pos += 1;
            }
            minsu.add(pos);
            System.out.println(cards[pos]);
        }
        br.close();
    }
}