import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        char[] chars = br.readLine().toCharArray();
        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : chars) {
            // 알파벳은 바로 담는다
            if (Character.isAlphabetic(ch)) {
                sb.append(ch);
                continue;
            }

            // 우선순위에 따른 처리
            // 넣을 기호의 우선순위 보다 스택에 들어있는 기호의 우선순위가 높다면 뽑아낸다
            // [+, *]가 들어있을 때 [-]가 들어온다면 [+, *] 둘 다 뽑는다
            // [+, *]가 들어있을 때 [/]가 들어온다면 [*]만 뽑는다
            int priority = getPriority(ch);
            switch (ch) {
                case '+':
                case '-':
                case '*':
                case '/':
                    while (!stack.isEmpty() && getPriority(stack.peekLast()) >= priority) {
                        sb.append(stack.pollLast());
                    }
                    stack.offerLast(ch);
                    break;
                case '(':
                    stack.offerLast(ch);
                    break;
                case ')':
                    while (!stack.isEmpty() && stack.peekLast() != '(') {
                        sb.append(stack.pollLast());
                    }
                    stack.removeLast();
                    break;
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int getPriority(char ch) {
        switch (ch) {
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
        }
        return -1;
    }
}