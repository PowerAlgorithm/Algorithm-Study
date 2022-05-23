import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] chars = br.readLine().toCharArray();

        Deque<Character> stack = new ArrayDeque<>();

        int sum = 0;
        int count = 1;
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch == '(') {
                stack.push(ch);
                count *= 2;
            } else if (ch == '[') {
                stack.push(ch);
                count *= 3;
            } else {
                if (ch == ')') {
                    if (stack.isEmpty() || stack.peek() != '(') {
                        sum = 0;
                        break;
                    }
                    if (chars[i - 1] == '(') {
                        sum += count;
                    }
                    stack.pop();
                    count /= 2;
                } else if (ch == ']') {
                    if (stack.isEmpty() || stack.peek() != '[') {
                        sum = 0;
                        break;
                    }
                    if (chars[i - 1] == '[') {
                        sum += count;
                    }
                    stack.pop();
                    count /= 3;
                } else {
                    sum = 0;
                    break;
                }
            }
        }

        if (!stack.isEmpty())
            sum = 0;

        bw.append(String.valueOf(sum));
        bw.flush();
        bw.close();
        br.close();
    }
}
