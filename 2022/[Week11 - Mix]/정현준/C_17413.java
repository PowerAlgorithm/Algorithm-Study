import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] line = br.readLine().toCharArray();
        StringBuilder result = new StringBuilder();
        ArrayDeque<Character> tmp = new ArrayDeque<>();

        for (int i = 0; i < line.length; i++) {
            char ch = line[i];
            if (ch == ' ') {
                checkStack(result, tmp);
                result.append(ch);
            } else if (ch == '<') {
                checkStack(result, tmp);
                result.append(ch);
                while (ch != '>') {
                    ch = line[++i];
                    result.append(ch);
                }
            } else {
                tmp.push(ch);
            }
        }
        checkStack(result, tmp);

        System.out.println(result.toString());
    }

    public static void checkStack(StringBuilder result, ArrayDeque<Character> stack) {
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
    }
}