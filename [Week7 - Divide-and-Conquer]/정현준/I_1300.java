import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        // https://st-lab.tistory.com/281
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        long low = 1;
        long high = K;

        while (low < high) {
            long count = 0;
            long mid = low + (high - low) / 2;

            for (int i = 1; i <= N; i++) {
                count += Math.min(mid / i, N);
            }

            if (K <= count) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        bw.append(String.valueOf(low));
        bw.flush();
        bw.close();
        br.close();
    }
}