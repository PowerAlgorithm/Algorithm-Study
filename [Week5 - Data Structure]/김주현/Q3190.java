// 3190번 뱀 (G5) [자료구조 - 큐,해시맵]
/*
<문제 정보>
 1. 뱀이 기어다니며 먹이를 먹으면 몸길이가 늘어나고, 자기 자신에 부딪히거나 벽을 넘어가면 게임 끝
    -> 게임이 몇 초에 끝나는지 출력
 2. 뱀은 1,1 위치에서 오른쪽을 향한 상태로 시작

<변수 범위>
 1. 1초 / 128MB
 2. 게임 보드 크기 2<=N<=100
 3. 사과 개수 0<=K<=100
 4. 방향변환 횟수 1<=L<=100
 5. 게임 시작 후 X초 후 1<=X<=10,000

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.HashMap;

public class Q3190 {
    static int N, K, L;
    static boolean[][] snakeInMap;
    static boolean[][] apple;
    static HashMap<Integer, Character> directionChanges = new HashMap<>();

    static int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}}; // 시계방향

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        apple = new boolean[N+1][N+1];
        int appleX, appleY;
        for (int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            appleX = Integer.parseInt(st.nextToken());
            appleY = Integer.parseInt(st.nextToken());
            apple[appleX][appleY] = true;
        }
        L = Integer.parseInt(br.readLine());
        int timeAfter;
        char direction;
        for (int i=0;i<L;i++) {
            st = new StringTokenizer(br.readLine());
            timeAfter = Integer.parseInt(st.nextToken());
            direction = st.nextToken().charAt(0);
            directionChanges.put(timeAfter, direction);
        }
        snakeInMap = new boolean[N+1][N+1];
        snakeInMap[1][1] = true;
        Deque<Pair16> snakeQueue = new ArrayDeque<>();
        snakeQueue.add(new Pair16(1, 1));

        int time = 0;
        int directionIndex = 0; // 오른쪽 회전 : +1, 왼쪽 회전 : -1 (OOB 처리 필수!)
        int nextX, nextY;
        boolean inRange;
        Pair16 removed;
        while (true) {
            // 현재 시간대가 끝난 후 방향을 바꿔야 한다면 바꾸기
            if (directionChanges.containsKey(time)) {
                if (directionChanges.get(time) == 'D') directionIndex = directionIndex==3 ? 0 : directionIndex+1;
                else directionIndex = directionIndex==0 ? 3 : directionIndex-1;
            }
            time++;
            nextX = snakeQueue.peekLast().x + dir[directionIndex][0];
            nextY = snakeQueue.peekLast().y + dir[directionIndex][1];
            inRange = nextX>=1 && nextY>=1 && nextX<=N && nextY<=N;
            if (!inRange || snakeInMap[nextX][nextY]) break;
            snakeQueue.add(new Pair16(nextX, nextY));
            snakeInMap[nextX][nextY] = true;
            if (!apple[nextX][nextY]) {
                removed = snakeQueue.poll(); // 사과가 없다면 꼬리를 하나 없앤다.
                snakeInMap[removed.x][removed.y] = false;
            }
            else apple[nextX][nextY] = false; // 사과가 있다면 먹고 비운다.
        }

        sb.append(time);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

class Pair16 {
    int x, y;

    public Pair16 (int x, int y) {
        this.x = x;
        this.y = y;
    }
}
























