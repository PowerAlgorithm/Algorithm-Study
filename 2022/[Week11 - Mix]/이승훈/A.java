import java.io.*;
import java.util.*;

/***
 * 키워드
 *
 * 조건
 *
 * 풀이
 *
 */

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int ans = 0;
        int sum = 0;
        for(int i=0; i<n; i++) {
            sum += arr[i];
            ans += sum;
        }
        System.out.println(ans);

        br.close();
    }
}
