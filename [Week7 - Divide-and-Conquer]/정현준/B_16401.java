import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 아이 수
        int M = Integer.parseInt(st.nextToken());
        // 과자 수
        int N = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        int max = 0;
        st = new StringTokenizer(br.readLine());
        // 입력
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        int min = 0;
        // 균등하게 과자를 놔눠줄 수 없다면 0을 반환해야 하기 때문에 0으로 초기화
        int result = 0;
        while (min <= max) {
            int mid = (min + max) / 2;
            // 나눌 과자의 길이가 0이라면 break
            if (mid == 0)
                break;
            int count = 0;
            for (int i = 0; i < N; i++) {
                count += arr[i] / mid;
            }

            // 만들어진 과자의 수가 아이의 수보다 크거나 같다면 갱신
            if (count >= M) {
                result = Math.max(mid, result);
            }
            if (count < M) {
                max = mid - 1;
            } else if (count >= M) {
                min = mid + 1;
            }
        }

        bw.append(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}
