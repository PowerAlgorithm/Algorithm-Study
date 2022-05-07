import java.io.*;
import java.sql.Array;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int count = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] people = new int[count];
        for (int i = 0; i < count; i++) {
            // 왼쪽에 키 큰 사람들의 수
            int left = Integer.parseInt(st.nextToken());
            for (int j = 0; j < count; j++) {
                // 왼쪽에 키 큰 사람들의 수가 0 이고 , 해당 자리에 사람이 없을 때
                if (left == 0 && people[j] == 0) {
                    people[j] = i + 1;
                    break;
                }
                // 해당 자리에 사람이 없으면 left 감소
                else if (people[j] == 0) {
                    left--;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        Arrays.stream(people).forEach(value -> {
            sb.append(value).append(" ");
        });

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}