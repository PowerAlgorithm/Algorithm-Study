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

        String shortStr = br.readLine();
        char[] shortArr = shortStr.toCharArray();
        char[] longArr = br.readLine().toCharArray();

        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();

        int low = 0, high = longArr.length - 1;
        boolean forward = true;
        while (low <= high) {
            // 정방향
            if (forward && low <= high) {
                left.push(longArr[low++]);
                if (left.size() >= shortArr.length && left.peek() == shortArr[shortArr.length - 1]) {
                    boolean equal = true;
                    for (int i = 0; i < shortArr.length; i++) {
                        if (shortArr[i] != left.get(left.size() - shortArr.length + i)) {
                            equal = false;
                            break;
                        }
                    }
                    if (equal) {
                        forward = false;
                        for (int j = 0; j < shortArr.length; j++) {
                            left.pop();
                        }
                    }
                }
            }
            // 역방향
            if (!forward && low <= high) {
                right.push(longArr[high--]);
                if (right.size() >= shortArr.length && right.peek() == shortArr[0]) {
                    boolean equal = true;
                    int shortIndex = 0;
                    for (int i = right.size() - 1; i >= right.size() - shortArr.length; i--) {
                        if (right.get(i) != shortArr[shortIndex++]) {
                            equal = false;
                            break;
                        }
                    }
                    if (equal) {
                        forward = true;
                        for (int j = 0; j < shortArr.length; j++) {
                            right.pop();
                        }
                    }
                }
            }
        }

        // left 와 right 스택에 있는 문자들을 한 곳에 모은다
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < left.size(); i++) {
            sb.append(left.get(i));
        }
        for (int i = right.size() - 1; i >= 0; i--) {
            sb.append(right.get(i));
        }
        // 모은 후 삭제해야 할 문자열이 있을수도 있으니 제거
        while (sb.indexOf(shortStr) >= 0) {
            sb.delete(sb.indexOf(shortStr), sb.indexOf(shortStr) + shortStr.length());
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }
}