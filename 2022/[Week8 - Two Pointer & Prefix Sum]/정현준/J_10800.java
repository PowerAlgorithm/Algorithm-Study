import java.io.*;
import java.util.*;

class Ball implements Comparable<Ball> {
    int number;
    int color;
    int size;

    public Ball(int number, int color, int size) {
        this.number = number;
        this.color = color;
        this.size = size;
    }

    @Override
    public int compareTo(Ball o) {
        // 크기 별 오름차순 정렬
        return this.size - o.size;
    }

    @Override
    public String toString() {
        return "Ball{" +
                "number=" + number +
                ", color=" + color +
                ", size=" + size +
                '}';
    }
}

/**
 * 색깔 별 , 크기 별로 정렬 후 탐색한다면 ?
 * - 색깔 별로 정렬해도 별 의미가 없다.. N * N
 * 크기 별로만 오름차순 정렬하고 누적합을 사용한다!!!
 */

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        List<Ball> balls = new ArrayList<>();
        int ea = Integer.parseInt(br.readLine());
        int[] colors = new int[ea + 1];
        int[] sums = new int[ea];

        for (int i = 0; i < ea; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            balls.add(new Ball(i, color, size));
        }

        Collections.sort(balls);

        int start = 0;
        int sum = 0;
        /*
         * colorArr 과 sum 변수를 잘 보자
         * while문 안에서
         * colorArr에 현재 공보다 작은 사이즈인 공의 사이즈를 색깔 별 계속 누적하며 sum변수는 모든 사이즈를 누적한다
         * while문이 끝난다면 , sum - colorArr[현재 공의 색깔]을 결과 배열에 담는다
         */
        for (int end = 0; end < ea; end++) {
            Ball beforeBall = balls.get(start);
            Ball ball = balls.get(end);

            // 이전 공의 크기가 현재 공보다 작을 때만
            while (beforeBall.size < ball.size) {
                sum += beforeBall.size;
                // 색깔 배열에 현재 색의 사이즈를 누적
                colors[beforeBall.color] += beforeBall.size;

                // 다음 공 갱신
                beforeBall = balls.get(++start);
            }

            sums[ball.number] = sum - colors[ball.color];
        }

        // balls.forEach(System.out::println);

        for (int val : sums) {
            bw.append(val + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}