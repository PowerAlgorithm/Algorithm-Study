import java.io.*;
import java.util.*;

class Main {
    static List<int[]> brackets;
    static boolean[] checked;
    static TreeSet<String> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Deque<Integer> stack = new ArrayDeque<>();
        brackets = new ArrayList<>();

        // brackets 리스트에 괄호 쌍의 인덱스를 저장한다
        char[] line = br.readLine().toCharArray();
        for (int i = 0; i < line.length; i++) {
            char ch = line[i];
            if (ch == '(') {
                stack.offerLast(i);
            } else if (ch == ')') {
                brackets.add(new int[] { stack.removeLast(), i });
            }
        }

        checked = new boolean[line.length];
        result = new TreeSet();

        combi(0, line);

        for (String str : result) {
            bw.append(str).append("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void combi(int index, char[] line) {
        if (index == brackets.size()) {
            StringBuilder sb = new StringBuilder();
            boolean flag = false;
            for (int i = 0; i < line.length; i++) {
                if (!checked[i])
                    sb.append(line[i]);
                else
                    flag = true;
            }
            if (flag)
                result.add(sb.toString());
            return;
        }
        int[] bracket = brackets.get(index);
        checked[bracket[0]] = true;
        checked[bracket[1]] = true;
        combi(index + 1, line);
        checked[bracket[0]] = false;
        checked[bracket[1]] = false;
        combi(index + 1, line);
    }
}