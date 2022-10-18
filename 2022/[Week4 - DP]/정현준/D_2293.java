import java.io.*;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int coins = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        int[] values = new int[coins + 1];
        int[] DP = new int[target + 1];

        // 동전 종류
        for (int i = 1; i <= coins; i++) {
            values[i] = Integer.parseInt(br.readLine());
        }

        DP[0] = 1;
        for (int i = 1; i <= coins; i++) {
            for (int j = 1; j <= target; j++) {
                if (j >= values[i]) {
                    // "j원을 만드는 방법 중 하나는 j - values[i] 원에서 가치가 values[i]인 동전을 더하면 된다."
                    DP[j] += DP[j - values[i]];
                }
            }
        }

        bw.append(String.valueOf(DP[target]));
        bw.flush();
        bw.close();
        br.close();
    }
}