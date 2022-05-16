import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        // 참고 https://cocoon1787.tistory.com/206
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int stuffCount = Integer.parseInt(st.nextToken());
        int maxWeight = Integer.parseInt(st.nextToken());

        // DP , 무게와 가치 배열 초기화
        int[][] DP = new int[stuffCount + 1][maxWeight + 1];
        int[] weights = new int[stuffCount + 1];
        int[] values = new int[stuffCount + 1];

        // 입력 값 저장
        for (int i = 1; i <= stuffCount; i++) {
            st = new StringTokenizer(br.readLine());
            weights[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
        }
        // 4 7
        // 6 13
        // 4 8
        // 3 6
        // 5 12
        // DP
        for (int i = 1; i <= stuffCount; i++) {
            // i = 물건
            // j = 무게
            for (int j = 1; j <= maxWeight; j++) {
                // 물건을 배낭에 담을 수 있을 때
                if (j - weights[i] >= 0) {
                    DP[i][j] = Math.max(DP[i - 1][j], DP[i - 1][j - weights[i]] + values[i]);
                }
                // 물건을 배낭에 담지 못 한다면 이전 물건의 가치를 담는다
                else
                    DP[i][j] = DP[i - 1][j];
            }
        }

        bw.append(String.valueOf(DP[stuffCount][maxWeight]));
        bw.flush();
        bw.close();
        br.close();
    }
}
