import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(st.nextToken());
        int dupl = Integer.parseInt(st.nextToken());

        int[] numbers = new int[size + 1];
        int[] count = new int[100_001];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= size; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // 투포인터로 풀이
        // start와 end사이에 겹치는 수가 dupl개 이하일 때는 end증가
        // dupl개 초과일 때는 start를 늘려가며 겹치는 수가 빠질때까지 begin증가
        int max = 0;
        for (int start = 1, end = 1; start < size; start++) {
            while (end <= size && count[numbers[end]] < dupl) {
                count[numbers[end]]++;
                end++;
            }
            max = Math.max(end - start, max);
            count[numbers[start]]--;
        }
        System.out.println(max);
    }
}