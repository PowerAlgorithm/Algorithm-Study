// 2910번 수들의 합4 (G4) [해시 & 구간합]
/*
<문제 정보>
 1. 수열의 i~j 부분합이 K인 것의 개수 출력

<변수 범위>
 1. 2초 / 128MB
 2. 1<=N<=200,000
 3. K의 절댓값은 20억 이하
 4. 정수의 절댓값은 10,000 이하

<프로그램 진행>
 1. 일단 N^2 은 안됨

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.HashMap;

public class Q2015 {
    static int N, K;
    static int[] arr;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // ******************** 입력 & 초기화 ********************

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) arr[i] = Integer.parseInt(st.nextToken());

        // ******************** 메인 로직 ********************
        /*
            1. 누적합을 HashMap에 넣어준다. (1 ~ i)
            2. 다음 누적합을 구했을 때, 이미 HashMap에 들어있는 수 중 합해서 K가 되는 수의 개수를 센다.
         */

        long count = 0;
        HashMap<Integer, Integer> sumOf_1_to_i = new HashMap<>();
        sumOf_1_to_i.put(0,1); // sum(N) - sum(0) 이 가능해야 sum(N)이 나옴

        int sum = 0;
        long needForK;
        boolean isPossibleNum;
        for (int i=0;i<N;i++) {
            sum += arr[i];
            needForK = (long)sum - K;
            isPossibleNum = needForK<=Integer.MAX_VALUE && needForK>=Integer.MIN_VALUE;

            // 현재 1~i 합에서 더했을 때 K가 되는 수의 개수를 찾기
            if (isPossibleNum && sumOf_1_to_i.containsKey((int)needForK)) {
                count += sumOf_1_to_i.get((int) needForK);
            }

            // 현재 합을 HashMap에 넣어주기
            if (!sumOf_1_to_i.containsKey(sum)) sumOf_1_to_i.put(sum, 1);
            else sumOf_1_to_i.replace(sum, sumOf_1_to_i.get(sum)+1);
        }

        // ******************** 정답 출력 ********************

        sb.append(count);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
