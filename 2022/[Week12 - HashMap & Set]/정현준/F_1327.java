import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static long number, asc;
    static ArrayDeque<Long> queue;
    static Set<Long> visit;

    public static void main(String[] args) throws IOException {
        // 참고 https://am003507.tistory.com/106
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        queue = new ArrayDeque<>();
        visit = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        // number [3,2,1] 비트마스킹
        // 11
        // 11_0010
        // 11_0010_0001
        for (int i = 0; i < N; i++) {
            number <<= 4;
            number += Long.parseLong(st.nextToken());
            // System.out.println(Long.toBinaryString(number));
        }

        // 소트 게임은 1부터 N까지 정수로 이루어진 N자리의 순열을 이용
        // 따라서 1부터 N까지 오름차순을 저장해둔다
        for (int i = 1; i <= N; i++) {
            asc <<= 4;
            asc += i;
            // System.out.println(Long.toBinaryString(asc));
        }

        System.out.println(number == asc ? 0 : BFS());
    }

    public static int BFS() {
        queue.offer(number);
        visit.add(number);
        int count = 0;

        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                long current = queue.poll();
                for (int j = 0; j <= N - K; j++) {
                    long value = current;
                    long result = 0, temp = 0;
                    for (int t = 0; t < K; t++) {
                        temp = value & (15L << (4 * (t + j)));
                        value -= temp;
                        temp >>= (4 * t);
                        result <<= 4;
                        result += temp;
                    }
                    value += result;
                    if (value == asc)
                        return count;
                    if (!visit.contains(value)) {
                        visit.add(value);
                        queue.offer(value);
                    }
                }
            }
        }

        return -1;
    }
}