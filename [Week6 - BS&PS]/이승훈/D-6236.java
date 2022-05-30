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
        int m = Integer.parseInt(st.nextToken());
        int[] prices = new int[n];
        for(int i=0; i<n; i++) {
            prices[i] = Integer.parseInt(br.readLine());
        }
        int min = 1;
        int max = 10000 * 100000;
        while(min <= max) {
            int income = (min + max) / 2;

            int wallet = 0;
            int num = 0;

            for(int i=0; i<n; i++) {
                if(income < prices[i]) {    // 사용 비용보도 입금이 적을 때
                    num = m + 1;
                    break;
                }
                if(wallet < prices[i]) {
                    wallet = income;
                    num += 1;
                }
                wallet -= prices[i];
            }
            if(num > m) {
                min = income + 1;
            } else {
                max = income - 1;
            }
        }
        System.out.println(min);

        br.close();
    }
}
