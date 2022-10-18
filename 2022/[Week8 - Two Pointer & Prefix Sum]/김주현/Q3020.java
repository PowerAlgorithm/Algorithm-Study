// 3020번 개똥벌레 (G5) [누적합] [이분탐색(풀이 아직)]
/*
<문제 정보>
 1. 석순과 종유석이 있는 동굴에서 처음부터 끝까지 한 높이로 갈때 파괴하는 최소 갯수와 그러한 높이의 수 출력
    - 항상 석순부터 시작, 번갈아가며 나옴

<변수 범위>
 1. 1초 / 128MB
 2. 동굴 길이 2<=N<=200,000
 3. 동굴 높이 2<=H<=500,000

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q3020 {
    static int N, H;
    static int[] stalagmite; // 석순
    static int[] stalactite; // 종유석
    static int minObstacles = 200_001;
    static int numOfHeight = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        stalagmite = new int[H+1];
        stalactite = new int[H+1];
        int h;
        for (int i=1;i<=N;i++) {
            h = Integer.parseInt(br.readLine());
            if (i%2==1) stalagmite[h] += 1;
            else stalactite[h] += 1;
        }
        for (int i=1;i<=H;i++) {
            stalagmite[i] += stalagmite[i-1];
            stalactite[i] += stalactite[i-1];
        }
//        printStatus();
        getMinObstacles();
        sb.append(minObstacles).append(" ").append(numOfHeight);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void getMinObstacles() {
        int reverseH;
        int obstacles;
        for (int i=1;i<=H;i++) {
            obstacles = 0;
            reverseH = H+1 - i;
            obstacles += stalagmite[H]-stalagmite[i-1];
            obstacles += stalactite[H]-stalactite[reverseH-1];
            if (obstacles == minObstacles) numOfHeight++;
            else if (obstacles < minObstacles) {
                minObstacles = obstacles;
                numOfHeight = 1;
            }
        }
    }

    public static void printStatus() {
        for (int n : stalagmite) System.out.printf("%d ",n);
        System.out.println();
        for (int n : stalactite) System.out.printf("%d ",n);
        System.out.println();
    }
}



















