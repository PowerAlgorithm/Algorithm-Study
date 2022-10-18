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
    static int size;
    static boolean[] switches;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        size = Integer.parseInt(br.readLine());
        switches = new boolean[size + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= size; i++) {
            int value = Integer.parseInt(st.nextToken());
            if (value == 1)
                switches[i] = true;
            else
                switches[i] = false;
        }

        int students = Integer.parseInt(br.readLine());
        for (int i = 0; i < students; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int index = Integer.parseInt(st.nextToken());
            if (gender == 1) {
                // 남학생은 받은 스위치의 번호의 배수인 스위치들을 반전한다
                int multiIndex = index;
                while (multiIndex <= size) {
                    toggle(multiIndex);
                    multiIndex += index;
                }
            } else {
                // 여학생은 받은 스위치의 번호의 좌우 대칭이 같은 스위치까지 반전한다
                int left = index - 1;
                int right = index + 1;
                toggle(index);
                while (left >= 1 && right <= size) {
                    if (switches[left] != switches[right]) {
                        break;
                    }
                    toggle(left);
                    toggle(right);
                    left--;
                    right++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= size; i++) {
            if (switches[i])
                sb.append("1");
            else
                sb.append("0");
            sb.append(" ");
            if (i % 20 == 0) {
                sb.append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    public static void toggle(int index) {
        switches[index] = !switches[index];
    }
}