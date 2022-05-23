import java.util.*;
import java.io.*;

/***
 * 키워드
 *
 * 조건
 *
 * 풀이
 *
 */

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] chars = br.readLine().toCharArray();
        int temp = 1;
        Stack<Character> stack = new Stack<>();
        int ans = 0;
        for (int i=0; i<chars.length; i++) {
            char symbol = chars[i];

            if (symbol == '(') {
                stack.add(symbol);
                temp *= 2;
            }
            if (symbol == '[') {
                stack.add(symbol);
                temp *= 3;
            }
            if (symbol == ')') {
                if (stack.isEmpty() || stack.peek() != '('){
                    ans = 0;
                    break;
                }
                if (chars[i-1] != '(') {
                    temp /= 2;
                    stack.pop();
                    continue;
                }
                stack.pop();
                ans += temp;
                temp /= 2;
            }
            if (symbol == ']') {
                if (stack.isEmpty() || stack.peek() != '['){
                    ans = 0;
                    break;
                }
                if (chars[i-1] != '[') {
                    temp /= 3;
                    stack.pop();
                    continue;
                }
                stack.pop();
                ans += temp;
                temp /= 3;
            }
        }
        if(!stack.isEmpty()) ans = 0;
        System.out.println(ans);

        br.close();
    }
}