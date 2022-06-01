import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Main {
    static int days, drewCount;
    static int[] moneys;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        days = Integer.parseInt(st.nextToken());
        drewCount = Integer.parseInt(st.nextToken());

        moneys = new int[days];

        int low = 0;
        int high = 100000 * 10000;
        for (int i = 0; i < days; i++) {
            moneys[i] = Integer.parseInt(br.readLine());
            // 사용 금액 중 최대 금액을 low에 저장
            low = Math.max(low, moneys[i]);
        }

        int result = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            // 현재 mid 돈으로 drewCount 이내에 인출 가능하다면 high를 줄인다
            if (search(mid)) {
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

    public static boolean search(int mid) {
        int count = 1;
        int remain = mid;
        for (int i = 0; i < moneys.length; i++) {
            // 인출할 돈을 빼나간다
            // 0보다 작아진다면 다시 인출한다
            if (remain - moneys[i] < 0) {
                remain = mid;
                count++;
            }
            // 인출할 돈을 계속 차감한다
            remain -= moneys[i];
        }

        return count <= drewCount;
    }
}