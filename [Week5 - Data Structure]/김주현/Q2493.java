// 2493번 탑 (G5) [자료구조 - 스택]
/*
<문제 정보>
 1. 일직선 상에 놓여진 탑들이 각각 왼쪽으로 레이저 신호 발사
    -> 가장 먼저 만나는 탑에서 송신
 2. 각각의 탑이 발사하는 레이저신호를 송신하는 탑을 출력
    - 없다면 0 출력

<변수 범위>
 1. 1.5초 / 128MB
 2. 탑의 개수 1<=N<=500,000
 3. 높이 1이상 100,000,000 이하

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;

public class Q2493 {
    static int N;
    static int[] towers;
    static int[] signalResult;
    static int lastSignalTower;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        towers = new int[N+1];
        signalResult = new int[N+1];
        lastSignalTower = N;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) towers[i] = Integer.parseInt(st.nextToken());

        Deque<Integer> stack = new ArrayDeque<>();
        stack.add(N);
        for (int i=N-1;i>=1;i--) {
            if (towers[i] >= towers[stack.peekLast()]) {
                while (!stack.isEmpty() && towers[i]>=towers[stack.peekLast()]) {
                    signalResult[stack.pollLast()] = i;
                }
            }
            stack.add(i);
        }

        while(!stack.isEmpty()) signalResult[stack.pollLast()] = 0;

        for (int i=1;i<=N;i++) sb.append(signalResult[i]).append(" ");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

















