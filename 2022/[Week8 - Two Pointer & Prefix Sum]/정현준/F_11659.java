import java.io.*;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(st.nextToken());
        int sumCount = Integer.parseInt(st.nextToken());

        int[] numbers = new int[size + 1];

        st = new StringTokenizer(br.readLine());
        // 입력 5 4 3 2 1일 때,
        // 배열 0 5 9 12 14 15
        for (int i = 1; i <= size; i++) {
            int number = Integer.parseInt(st.nextToken());
            numbers[i] = numbers[i - 1] + number;
        }

        while (sumCount-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = numbers[end] - numbers[start - 1];
            bw.append(value + "\n");
        }

        bw.flush();
        bw.close();
        br.close();

    }
}