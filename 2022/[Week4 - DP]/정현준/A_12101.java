import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {
    static int target, resultIndex;

    public static void main(String[] args) throws IOException {
        // 참고
        // https://velog.io/@jkh9615/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EB%B0%B1%EC%A4%80-12101-1-2-3-%EB%8D%94%ED%95%98%EA%B8%B0-2-Java
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        target = Integer.parseInt(st.nextToken());
        resultIndex = Integer.parseInt(st.nextToken());

        List<String>[] list = new ArrayList[target + 3];

        for (int i = 0; i < target + 3; i++)
            list[i] = new ArrayList<>();

        // target = 1, 2, 3일 때에
        list[1].add("1");
        list[2].add("1+1");
        list[2].add("2");
        list[3].add("1+2");
        list[3].add("1+1+1");
        list[3].add("2+1");
        list[3].add("3");

        for (int i = 4; i <= target; i++) {
            /*
             * list[i-1]에 있는 수식에 +1을,
             * list[i-2]에 있는 수식에 +2을,
             * list[i-3]에 있는 수식에 +3을 붙여준다.
             */
            for (int j = 1; j <= 3; j++) {
                for (String op : list[i - j]) {
                    list[i].add(op + "+" + j);
                }
            }
        }

        /*
         * list[n]에 있는 갯수가 resultIndex보다 작다면 -1을 출력,
         * 아니라면, list[n]을 정렬하고, resultIndex - 1 번째 item을 출력한다.
         */
        if (list[target].size() < resultIndex) {
            bw.append("-1");
        } else {
            Collections.sort(list[target]);
            bw.append(list[target].get(resultIndex - 1));
        }

        bw.flush();
        bw.close();
        br.close();
    }

}