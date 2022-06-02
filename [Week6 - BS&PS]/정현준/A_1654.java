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

        int size = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        int[] arr = new int[size];

        // 최대 길이
        long max = 0;
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }

        // 자르는 전선의 길이가 존재해야 하므로 1로 초기화
        long min = 1;
        long result = 0;
        while (min <= max) {
            // 중간 값
            long mid = (min + max) / 2;

            long count = 0;

            // 케이블의 배열을 순회하며 자를 수 있는 개수 누적
            for (int i = 0; i < arr.length; i++) {
                count += (arr[i] / mid);
            }

            if (count < target) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }

            // 자른 케이블의 수가 목표 개수 보다 크거나 같으면 갱신
            if (count >= target) {
                result = mid;
            }
        }

        bw.append(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}
