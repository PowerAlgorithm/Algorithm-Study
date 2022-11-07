// 2212번 센서 (G5) [정렬]
/*
<문제 정보>
 1.

<변수 범위>
 1.

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q2212 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // ******************** 입력 & 초기화 ********************
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[] sensors = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            sensors[i] = Integer.parseInt(st.nextToken());
        }

        // ******************** 메인 로직 ********************
        Arrays.sort(sensors);
        Integer[] diffs = new Integer[N-1];
        for (int i=0; i<N-1; i++) {
            diffs[i] = sensors[i+1] - sensors[i];
        }

        Arrays.sort(diffs, Collections.reverseOrder());
        int coverage = sensors[N-1] - sensors[0];
        for (int i=0; i<Math.min(K-1, N-1); i++) {
            coverage -= diffs[i];
        }

        // ******************** 정답 출력 ********************
        sb.append(coverage);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
