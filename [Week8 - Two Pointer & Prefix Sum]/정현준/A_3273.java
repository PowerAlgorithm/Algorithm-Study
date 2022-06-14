import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 9
        // 5 12 7 10 9 1 2 3 11
        // 13

        int size = Integer.parseInt(br.readLine());
        int[] numbers = new int[size];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int target = Integer.parseInt(br.readLine());

        Arrays.sort(numbers);

        int result = 0;
        int start = 0, end = size - 1;

        while (start < end) {
            int sum = numbers[start] + numbers[end];
            if (sum == target)
                result++;

            if (sum <= target)
                start++;
            else
                end--;
        }

        bw.append(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();

    }
}
