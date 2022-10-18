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

class Member implements Comparable<Member> {
    int gender;
    int pos;

    public Member(int gender, int pos) {
        this.gender = gender;
        this.pos = pos;
    }

    @Override
    public int compareTo(Member o) {
        return this.pos - o.pos;
    }

    @Override
    public String toString() {
        return "Member [gender=" + gender + ", pos=" + pos + "]";
    }
}

public class Main {
    static int[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());

        List<Member> members = new ArrayList<Member>();
        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int pos = Integer.parseInt(st.nextToken());
            // 남자를 -1로 저장
            members.add(new Member(gender == 0 ? -1 : gender, pos));
        }

        Collections.sort(members);

        if (size == 2) {
            System.out.println(members.get(1).pos - members.get(0).pos);
            return;
        }

        sum = new int[size];
        sum[0] = members.get(0).gender;

        // 성별 누적합
        for (int i = 1; i < size; i++) {
            sum[i] = sum[i - 1] + members.get(i).gender;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < size; i++) {
            if (!map.containsKey(sum[i])) {
                map.put(sum[i], new ArrayList<>());
            }
            map.get(sum[i]).add(i);
        }

        // 누적합이 같은 인덱스들을 맵에 몰아 넣고 첫번째 위치와 마지막 위치를 최대값으로 갱신
        int result = 0;
        for (int key : map.keySet()) {
            List<Integer> values = map.get(key);
            if (values.size() > 1) {
                int start = values.get(0) + 1;
                int end = values.get(values.size() - 1);
                result = Math.max(result, members.get(end).pos - members.get(start).pos);
            }
        }

        System.out.println(result);

    }
}