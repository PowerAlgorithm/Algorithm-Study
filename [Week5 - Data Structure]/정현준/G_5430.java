import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0) {
            StringJoiner sj = new StringJoiner(",");
            boolean isForward = true;
            char[] commands = br.readLine().toCharArray(); // 명령어
            int size = Integer.parseInt(br.readLine());
            Deque<Integer> deque = initDeque(br.readLine());
            try {
                for (char command : commands) {
                    if (command == 'R') {
                        isForward = isForward ? false : true;
                    } else {
                        if (isForward)
                            deque.removeFirst();
                        else
                            deque.removeLast();
                    }
                }
            } catch (NoSuchElementException e) {
                bw.append("error\n");
                continue;
            }
            bw.append("[");
            if (isForward) {
                for (int number : deque) {
                    sj.add(String.valueOf(number));
                }
            } else {
                while (!deque.isEmpty()) {
                    sj.add(String.valueOf(deque.removeLast()));
                }
            }
            bw.append(sj.toString()).append("]\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static Deque<Integer> initDeque(String line) {
        Deque<Integer> result = new LinkedList<>();
        String[] numbers = line.substring(1, line.length() - 1).split(",");
        for (String number : numbers) {
            if (number.equals(""))
                break;
            result.addLast(Integer.parseInt(number));
        }
        return result;
    }
}