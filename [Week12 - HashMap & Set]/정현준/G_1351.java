import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Map<Long, Long> map;
    static long N, P, Q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        map = new HashMap<>();
        map.put(0L, 1L);

        N = Long.parseLong(st.nextToken());
        P = Long.parseLong(st.nextToken());
        Q = Long.parseLong(st.nextToken());

        System.out.println(find(N));
    }

    public static Long find(long number) {
        if (map.containsKey(number)) {
            return map.get(number);
        }
        map.put(number, find(number / P) + find(number / Q));
        return map.get(number);
    }
}