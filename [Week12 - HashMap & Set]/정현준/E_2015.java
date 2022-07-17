import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 참고 https://skdltm117.tistory.com/36
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] numbers = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        Map<Integer, Long> map = new HashMap<Integer, Long>();
        long answer = 0;

        for (int i = 1; i <= N; i++) {
            int number = Integer.parseInt(st.nextToken());
            numbers[i] = numbers[i - 1] + number;
            if (numbers[i] == K)
                answer++;

            if (map.containsKey(numbers[i] - K))
                answer += map.get(numbers[i] - K);

            if (!map.containsKey(numbers[i]))
                map.put(numbers[i], 1L);
            else
                map.put(numbers[i], map.get(numbers[i]) + 1);
        }
        System.out.println(answer);
    }
}