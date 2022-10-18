import java.io.*;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        int[] numbers = new int[size];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int result = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int sum = 0;
        while (start <= end) {
            if (sum >= target) {
                result = Math.min(end - start, result);
                sum -= numbers[start++];
            } 
            else if (end == size)
                break;
            else
                sum += numbers[end++];
        }

        bw.append(String.valueOf(result == Integer.MAX_VALUE ? 0 : result));
        bw.flush();
        bw.close();
        br.close();

    }
}