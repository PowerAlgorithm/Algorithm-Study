import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Map<Long, Long> map;
    static long N, P, Q, X, Y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        map = new HashMap<>();
        map.put(0L, 1L);

        N = Long.parseLong(st.nextToken());
        P = Long.parseLong(st.nextToken());
        Q = Long.parseLong(st.nextToken());
        X = Long.parseLong(st.nextToken());
        Y = Long.parseLong(st.nextToken());

        System.out.println(find(N));
    }

    public static long find(long number) {
        if (map.containsKey(number)) {
            return map.get(number);
        } else if (number <= 0) {
            map.put(number, 1L);
            return 1L;
        }
        map.put(number, find(number / P - X) + find(number / Q - Y));
        return map.get(number);
    }
}