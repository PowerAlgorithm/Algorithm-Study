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
    static int[] lines;
    static int n;
    static int k;
    static boolean printStatus;

    public static void divide(int start, int end) { // end 포함!!!
        if(start+1<end) { // 나누는 부분
            int mid = (start + end) / 2;
            divide(start, mid);
            divide(mid+1, end);
        }
        merge(start, end);

        if (n / (end - start + 1) == k) {
            for(int i=start; i<=end; i++) {
                System.out.print(lines[i] + " ");
            }
        }
    }

    public static void merge(int start, int end) {
        int mid = (start + end) / 2;
        int[] tmp = new int[end - start + 1];
        int i=start; int j=mid+1; int k=0;

        while(i<=mid || j<=end) {
            if (j>end || (i<=mid && lines[i] <= lines[j])) {
                tmp[k] = lines[i];
                i++;
                k++;
            }
            else if(i>mid || (j<=end && lines[j] <= lines[i])) {
                tmp[k] = lines[j];
                j++;
                k++;
            }
        }
        int p = 0;
        for(int pos=start; pos<end+1; pos++) {
            lines[pos] = tmp[p];
            p++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        lines = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            lines[i] = Integer.parseInt(st.nextToken());
        }
        k = Integer.parseInt(br.readLine());
        divide(0, n-1);
        br.close();
    }
}