// 2910번 빈도 정렬
/*
<문제 정보>
 1. 많이 나온 순서대로 정렬
    - 만약 횟수가 같다면 먼저 나온 순서로 정렬

<변수 범위>
 1. 1초 / 128MB
 2. 메세지 길이 1<=N<=1,000
 3. C 이하의 수 1<=C<=1,000,000,000

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Arrays;

public class Q2910 {
    static int N, C;

    static final int COUNT = 0;
    static final int ORDER = 1;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        HashMap<Integer, int[]> numbers = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        int num;
        int orderCount = 0;
        for (int i=0;i<N;i++) {
            num = Integer.parseInt(st.nextToken());
            if (!numbers.containsKey(num)) {
                numbers.put(num, new int[] {1, orderCount++});
            }
            else numbers.get(num)[COUNT]++;
        }

        int mapSize = numbers.size();
        NumberStatus[] numberStatus = new NumberStatus[mapSize];
        int idx = 0;
        for (Entry<Integer, int[]> entry : numbers.entrySet()) {
            numberStatus[idx++] = new NumberStatus(entry.getKey(), entry.getValue()[COUNT], entry.getValue()[ORDER]);
        }
//        System.out.println(numberStatus.length);
        Arrays.sort(numberStatus);
        int len;
        for (NumberStatus status : numberStatus) {
            len = status.count;
            for (int i=0;i<len;i++) sb.append(status.number).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

class NumberStatus implements Comparable<NumberStatus>{
    int number, count, order;

    public NumberStatus (int number, int count, int order) {
        this.number = number;
        this.count = count;
        this.order = order;
    }

    @Override
    public int compareTo(NumberStatus o) {
        return this.count==o.count ? this.order - o.order : o.count - this.count;
    }
}













