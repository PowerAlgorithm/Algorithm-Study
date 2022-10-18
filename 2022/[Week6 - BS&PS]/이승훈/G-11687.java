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
        int m = Integer.parseInt(br.readLine());
        int min = 1;
        int max = 200000000;        // 최대 5의 배수 (5곱하기 전)
        int ans = 0;
        while(min <= max) {
            int value = (min + max) / 2;    // 5의 제곱 개수
            int totalNum = value;
            int num = 2; // 제곱 표현
            while(Math.pow(5, num) <= 5*value) {
                int tmp = (int) ((5 * value) / Math.pow(5, num));
                totalNum += (tmp);
                num += 1;
            }
            if(totalNum == m) {
                ans = value;
            }
            if(totalNum >= m) {
                max = value - 1;
            } else {
                min = value + 1;
            }
        }
        if(ans == 0)
            System.out.println(-1);
        else
            System.out.println(ans * 5);

        br.close();
    }
}