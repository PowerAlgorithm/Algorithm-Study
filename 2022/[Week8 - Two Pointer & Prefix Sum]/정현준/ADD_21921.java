import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(st.nextToken());
        int seq = Integer.parseInt(st.nextToken());

        int[] numbers = new int[size];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // 초기 세팅
        int max = 0;
        for (int i = 0; i < seq; i++) {
            max += numbers[i];
        }

        int start = 0;
        int end = seq;
        int sum = max;
        int count = 0;
        while (end <= size) {
            if (sum == max) {
                count++;
            } else if (sum > max) {
                max = sum;
                count = 1;
            }

            if (end >= size)
                break;
            sum -= numbers[start++];
            sum += numbers[end++];
        }

        bw.append(max == 0 ? "SAD" : max + "\n" + count);
        bw.flush();
        bw.close();
        br.close();
    }
}