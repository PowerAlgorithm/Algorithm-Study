import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 1. 일반 링크드리스트로 해결 시 "시간초과"
 * - 삽입 , 삭제는 해당 인덱스만큼 접근하기 때문
 * 2. ListIterator 인터페이스 [해결]
 * - ListIterator 인터페이스는 양방향으로 탐색하면서 추가/삭제/검색을 위한 기능을 제공
 * 3. Stack 사용 [해결]
 *
 */

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());

        // '-' 백스페이스 : 앞에 글자가 있다면 삭제
        // '<' , '>' 커서 이동 : 위치를 움직일 수 있다면 왼쪽 또는 오른쪽으로 1만큼 이동
        // 커서의 위치가 줄의 마지막이 아니라면 , 커서 및 커서 오른쪽에 있는 모든 문자는 오른쪽으로 한 칸 이동
        while (testCase-- > 0) {
            char[] chars = br.readLine().toCharArray();

            // bw.append(useListIterator(chars)).append("\n");
            bw.append(useStack(chars)).append("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static String useListIterator(char[] chars) {
        List<Character> list = new LinkedList<Character>();
        ListIterator<Character> line = list.listIterator();

        for (char ch : chars) {
            if (ch == '-') {
                if (line.hasPrevious()) {
                    line.previous();
                    line.remove();
                }
            } else if (ch == '<') {
                if (line.hasPrevious()) {
                    line.previous();
                }
            } else if (ch == '>') {
                if (line.hasNext()) {
                    line.next();
                }
            } else {
                line.add(ch);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char ch : list) {
            sb.append(String.valueOf(ch));
        }
        return sb.toString();
    }

    public static String useStack(char[] chars) {
        Deque<Character> pre = new ArrayDeque<Character>();
        Deque<Character> post = new ArrayDeque<Character>();

        for (char ch : chars) {
            if (ch == '-') {
                if (!pre.isEmpty())
                    pre.pop();
            } else if (ch == '<') {
                if (!pre.isEmpty())
                    post.push(pre.pop());
            } else if (ch == '>') {
                if (!post.isEmpty())
                    pre.push(post.pop());
            } else {
                pre.push(ch);
            }
        }

        while (!post.isEmpty()) {
            pre.push(post.pop());
        }

        StringBuilder sb = new StringBuilder();
        while (!pre.isEmpty()) {
            sb.append(String.valueOf(pre.pop()));
        }
        return sb.reverse().toString();
    }
}
