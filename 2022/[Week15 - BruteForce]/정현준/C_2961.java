import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int[] plus, multi;
    static int minDiff = Integer.MAX_VALUE, size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        plus = new int[size];
        multi = new int[size];

        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            multi[i] = Integer.parseInt(st.nextToken());
            plus[i] = Integer.parseInt(st.nextToken());
            minDiff = Math.min(Math.abs(plus[i] - multi[i]), minDiff);
        }

        if (size != 1) {
            combi(0, 1, 0);
        }
        System.out.println(minDiff);
    }

    public static void combi(int index, int multiValue, int sumValue) {
        if (index == size) {
            if (multiValue != 1 && sumValue != 0)
                minDiff = Math.min(Math.abs(multiValue - sumValue), minDiff);
            return;
        }
        combi(index + 1, multiValue * multi[index], sumValue + plus[index]);
        combi(index + 1, multiValue, sumValue);
    }
}