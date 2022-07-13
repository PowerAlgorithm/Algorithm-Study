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

        int setSize = Integer.parseInt(st.nextToken());
        int testSize = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<String>();
        while (setSize-- > 0) {
            set.add(br.readLine());
        }

        int result = 0;
        while (testSize-- > 0) {
            if (set.contains(br.readLine())) {
                result++;
            }
        }

        System.out.println(result);
    }
}