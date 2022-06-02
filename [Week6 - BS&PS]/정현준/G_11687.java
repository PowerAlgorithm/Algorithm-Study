import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());

        int low = 1;
        // 최대 수인 100_000_000 * 5
        int high = 5 * 100_000_000;
        int result = -1;

        while (low <= high) {
            int count = 0;
            int mid = (low + high) / 2;
            // 소인수분해 했을 때 5의 개수를 누적
            for (int i = 5; i <= mid; i *= 5) {
                count += mid / i;
            }

            if (count >= num) {
                // 주어진 0의 개수와 5의 개수가 같을 때 result를 갱신
                if (count == num) {
                    result = mid;
                }
                high = mid - 1;
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
