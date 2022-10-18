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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int size1 = Integer.parseInt(st.nextToken());
        int size2 = Integer.parseInt(st.nextToken());

        Set<Integer> first = new HashSet<Integer>();
        Set<Integer> second = new HashSet<Integer>();

        int result = size1 + size2;

        st = new StringTokenizer(br.readLine());
        while (size1-- > 0) {
            first.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        while (size2-- > 0) {
            second.add(Integer.parseInt(st.nextToken()));
        }
        for (int value : first) {
            if (second.contains(value)) {
                result -= 2;
            }
        }

        System.out.println(result);
    }
}