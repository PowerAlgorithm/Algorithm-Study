import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(st.nextToken());
        int lionCount = Integer.parseInt(st.nextToken());

        int[] numbers = new int[size];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // 10 3
        // 1 2 2 2 1 2 1 2 2 1

        int min = Integer.MAX_VALUE;
        int dupl = 0;
        for (int start = 0, end = 0; start < size; start++) {
            while (end < size && dupl < lionCount) {
                if (numbers[end++] == 1)
                    dupl++;
            }
            if (dupl == lionCount) {
                min = Math.min(min, end - start);
                if (numbers[start] == 1)
                    dupl--;
            }
        }
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }
}