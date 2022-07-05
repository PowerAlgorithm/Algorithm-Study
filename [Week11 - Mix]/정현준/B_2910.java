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

    static class Data implements Comparable<Data> {
        int number;

        public Data(int number) {
            this.number = number;
        }

        @Override
        public int compareTo(Data o) {
            if (count.get(o.number) == count.get(this.number)) {
                return index.get(this.number) - index.get(o.number);
            }
            return count.get(o.number) - count.get(this.number);
        }
    }

    static Map<Integer, Integer> count, index;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        int max = Integer.parseInt(st.nextToken());

        count = new HashMap<Integer, Integer>();
        index = new HashMap<Integer, Integer>();

        List<Data> datas = new ArrayList<Main.Data>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            int number = Integer.parseInt(st.nextToken());
            count.put(number, count.getOrDefault(number, 0) + 1);
            index.put(number, Math.min(index.getOrDefault(number, Integer.MAX_VALUE), i));
            datas.add(new Data(number));
        }

        Collections.sort(datas);

        StringBuilder sb = new StringBuilder();
        for (Data data : datas) {
            sb.append(data.number).append(" ");
        }
        System.out.println(sb.toString());
    }
}