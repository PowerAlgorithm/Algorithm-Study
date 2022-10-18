import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Time implements Comparable<Time> {
        int s;
        int e;

        public Time(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Time o) {
            if (this.e == o.e) {
                return this.s - o.s;
            }
            return this.e - o.e;
        }
    }

    static Map<Integer, Integer> count, index;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());

        List<Time> times = new ArrayList<Main.Time>();

        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            times.add(new Time(s, e));
        }

        Collections.sort(times);

        int count = 0;
        int now = 0;
        for (Time time : times) {
            if (now <= time.s) {
                count++;
                now = time.e;
            }
        }
        System.out.println(count);
    }
}