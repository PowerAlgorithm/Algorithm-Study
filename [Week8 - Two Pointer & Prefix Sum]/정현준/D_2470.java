import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int size = Integer.parseInt(br.readLine());
        int[] numbers = new int[size];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);

        int resultLeft = 0, resultRight = 0;

        int left = 0;
        int right = size - 1;
        int diff1 = Integer.MAX_VALUE;
        while (left < right) {
            int diff = numbers[left] + numbers[right];
            int abs = Math.abs(diff);
            if (diff1 > abs) {
                diff1 = abs;
                resultLeft = left;
                resultRight = right;
                if (abs == 0)
                    break;
            }

            if (diff < 0) {
                left++;
            } else {
                right--;
            }
        }

        bw.append(numbers[resultLeft] + " " + numbers[resultRight]);
        bw.flush();
        bw.close();
        br.close();
    }
}