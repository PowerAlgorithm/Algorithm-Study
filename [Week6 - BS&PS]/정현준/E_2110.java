import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int homeCount, routerCount;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        homeCount = Integer.parseInt(st.nextToken());
        routerCount = Integer.parseInt(st.nextToken());

        arr = new int[homeCount];

        for (int i = 0; i < homeCount; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int result = 0;

        int low = 1;
        // 처음 집과 가장 끝 집의 거리
        int high = arr[arr.length - 1] - arr[0];
        while (low <= high) {
            int mid = (high + low) / 2;

            // 현재 mid 간격으로 공유기를 다 설치할 수 있다면 거리를 더 늘려본다
            if (isPossible(mid)) {
                low = mid + 1;
                result = Math.max(mid, result);
            } else {
                high = mid - 1;
            }
        }

        bw.append(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean isPossible(int mid) {
        int count = 1;
        int prev = arr[0];
        for (int i = 1; i < arr.length; i++) {
            // 이전 집과 현재 집의 거리를 비교 하여 mid간격으로 설치가 가능하다면
            if (arr[i] - prev >= mid) {
                count++;
                prev = arr[i];
            }
        }
        return count >= routerCount;
    }
}