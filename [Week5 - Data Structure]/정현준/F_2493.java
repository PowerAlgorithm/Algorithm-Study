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
        StringBuilder sb = new StringBuilder();

        int towerCount = Integer.parseInt(br.readLine());

        Deque<int[]> towerHeight = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int index = 1; index <= towerCount; index++) {
            // 현재 타워의 높이
            int tower = (Integer.parseInt(st.nextToken()));
            while (!towerHeight.isEmpty()) {
                int[] peek = towerHeight.peek();
                // 이전의 타워의 높이가 더 크면 이전 타워의 인덱스를 넣는다
                if (peek[1] >= tower) {
                    sb.append(peek[0]).append(" ");
                    break;
                }
                // 위의 조건에 걸리지 않는다면 버린다
                towerHeight.pop();
            }
            // 비어있다면 비교할 이전 타워가 없으니 0 추가
            if (towerHeight.isEmpty()) {
                sb.append("0 ");
            }
            towerHeight.push(new int[] { index, tower });
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}