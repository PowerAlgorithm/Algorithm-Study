import java.io.*;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int length = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());

        // 바닥과 천장 배열
        int[] ceil = new int[height + 1];
        int[] floor = new int[height + 1];

        // 각 배열의 높이(인덱스)의 값을 증가
        for (int i = 0; i < length / 2; i++) {
            ceil[Integer.parseInt(br.readLine())]++;
            floor[Integer.parseInt(br.readLine())]++;
        }

        // 바닥과 천장 배열의 인덱스는 높이를 의미하며 ,
        // 역순으로 누적하는 이유는 높이가 1인 방해물은 1번 인덱스 이후 도 모두 깨야하기 때문이다
        for (int i = height - 1; i >= 0; i--) {
            ceil[i] += ceil[i + 1];
            floor[i] += floor[i + 1];
        }

        int[] count = new int[height + 1];
        int min = Integer.MAX_VALUE;

        // count배열에 i(높이)로 지나게 되면 몇 개를 파괴해야 하는지 저장한다
        for (int i = 1; i <= height; i++) {
            count[i] = ceil[i] + floor[height - i + 1];
            min = Math.min(min, count[i]);
        }

        int result = 0;
        for (int i = 1; i <= height; i++) {
            if (count[i] == min)
                result++;
        }

        bw.append(min + " " + result);
        bw.flush();
        bw.close();
        br.close();
    }
}