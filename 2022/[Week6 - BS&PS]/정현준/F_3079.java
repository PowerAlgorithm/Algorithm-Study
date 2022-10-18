import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        // 참고 https://maivve.tistory.com/145
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        long max = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(arr[i], max);
        }

        long result = Long.MAX_VALUE;

        long low = 1;
        long high = (long) (1e9 * 1e9);
        while (low <= high) {
            long mid = (low + high) / 2;
            long people = 0;
            // mid를 심사대의 시간으로 나누며 처리할 수 있는 최대의 사람을 누적한다
            for (int time : arr) {
                people += mid / time;
                if (people >= M)
                    break;
            }

            // 사람 수가 현재 인원보다 많다면 갱신한다
            if (people >= M) {
                high = mid - 1;
                result = mid;
            } else {
                low = mid + 1;
            }
        }

        bw.append(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}