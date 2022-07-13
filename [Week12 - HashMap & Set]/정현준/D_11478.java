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
        Set<String> set = new HashSet<String>();

        String line = br.readLine();

        for (int i = 0; i < line.length(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < line.length(); j++) {
                sb.append(String.valueOf(line.charAt(j)));
                set.add(sb.toString());
            }
        }
        System.out.println(set.size());
    }
}