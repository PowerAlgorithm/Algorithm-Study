import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Ballon {
    int index;
    int move;

    public Ballon(int index, int move) {
        this.index = index;
        this.move = move;
    }
}

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        Deque<Ballon> ballons = new ArrayDeque<>();

        int count = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 풍선 인덱스와 이동 값 입력
        for (int i = 1; i <= count; i++) {
            ballons.add(new Ballon(i, Integer.parseInt(st.nextToken())));
        }

        boolean isPositive = true;
        while (!ballons.isEmpty()) {
            Ballon now;
            if (isPositive)
                now = ballons.pollFirst();
            else
                now = ballons.pollLast();
            // 현재 풍선의 인덱스
            sb.append(now.index).append(" ");
            if (ballons.isEmpty())
                break;

            if (now.move > 0) {
                isPositive = true;
                // 양수라면 앞에서 부터 꺼내 뒤로 넣는다
                for (int i = 0; i < (now.move - 1) % ballons.size(); i++) {
                    ballons.addLast(ballons.pollFirst());
                }
            } else {
                isPositive = false;
                // 음수라면 뒤에서 부터 꺼내 앞으로 넣는다
                for (int i = 0; i < (Math.abs(now.move) - 1) % ballons.size(); i++) {
                    ballons.addFirst(ballons.pollLast());
                }
            }
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}