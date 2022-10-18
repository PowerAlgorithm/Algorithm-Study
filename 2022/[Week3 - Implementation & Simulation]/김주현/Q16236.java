// 16236번 아기 상어 (G3) [구현]
/*
<문제 정보>
 1. 몇 초 동안 엄마 상어에게 도움 요청하지 않고 물고기를 잡아먹을 수 있는지
    - 0 : 빈칸
    - 1 ~ 6 칸에 있는 물고기 크기
    - 9 : 아기상어 위치
    - 초기 아기상어 크기 : 2

    - 아기 상어 움직임 : 자신의 크기 이하 물고기칸은 다 지날 수 있음
        -> 가장 가까운 물고기를 우선으로 먹음
        -> 같은 거리에 여러마리 있다면 위쪽, 왼쪽 에 있는 물고기를 우선순위로 먹는다.

    - 아기 상어 먹이 : 자신의 크기보다 작은 물고기만 먹을 수 있다.

<변수 범위>
 1. 2초 / 512MB
 2. 공간의 크기 2<=N<=20


<프로그램 진행>
 1. bfs 구현하면 끝

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Arrays;

public class Q16236 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static Pair13 sharkPos;
    static int sharkSize = 2;
    static int eatenPrey = 0;
    static int time = 0;
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    static final int EMPTY = 0;
    static final int BABY_SHARK = 9;
    static final boolean ONE = true;
    static final boolean TWO = false;

//    static int preyNum=0;



    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j]==BABY_SHARK) sharkPos = new Pair13(i, j);
            }
        }

        while (true) {
            if(!findPrey()) break;
        }

        sb.append(time);



        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean findPrey() {
        for (int i=0;i<N;i++) Arrays.fill(visited[i], false);

        PriorityQueue<Pair13> pq1 = new PriorityQueue<>();
        PriorityQueue<Pair13> pq2 = new PriorityQueue<>();

        pq1.add(sharkPos); // pq1 으로 시작
        visited[sharkPos.x][sharkPos.y] = true;
        Pair13 now;
        int nextX, nextY;
        boolean inRange;
        boolean findPrey = false;
        int pqSize;
        int t=0;
        boolean addToOne = TWO;

        while(!pq1.isEmpty() || !pq2.isEmpty()) {
            // 지금 저장해야할 pq와 다른 곳에서
            if(addToOne) pqSize = pq2.size();
            else pqSize = pq1.size();


            for (int i=0;i<pqSize;i++) {
                // 빼는건 저장할 pq와 다른 곳에서
                if (addToOne) now = pq2.poll();
                else now = pq1.poll();

                // 현재 칸이 먹을 수 있는 고기인지 확인
                // 먹이를 찾으면 먹이를 먹고 true 반환 -> 먹이 우선순위를 적용해야 한다.
                if(map[now.x][now.y]<sharkSize && map[now.x][now.y]!=EMPTY) {
//                    System.out.printf("%d번째 먹이좌표 : (%d, %d)\n",++preyNum,now.x, now.y);
                    eatenPrey++;
                    map[now.x][now.y] = EMPTY;
                    if (eatenPrey == sharkSize) {
//                        System.out.println("이번에 상어 몸집 커짐");
                        sharkSize++;
                        eatenPrey = 0;
                    }
                    map[sharkPos.x][sharkPos.y] = EMPTY;
                    sharkPos = new Pair13(now.x, now.y);
                    time+=t;


//                    System.out.printf("%d초 동안 움직여서 누적 %d초\n\n",t,time);

                    return true;
                }

                for (int[] d : dir) {
                    nextX = now.x + d[0];
                    nextY = now.y + d[1];
                    inRange = nextX>=0 && nextY>=0 && nextX<N && nextY<N;
                    if (inRange && !visited[nextX][nextY] && map[nextX][nextY]<=sharkSize) {



                        visited[nextX][nextY] = true;
                        if (addToOne) pq1.add(new Pair13(nextX,nextY));
                        else pq2.add(new Pair13(nextX,nextY));

                    }
                }

            }
            // 다음에 넣어야할 pq 공간을 바꾸어 줌
            addToOne = addToOne ? TWO : ONE;
            t++;
        }
        // 여기까지 온다면 먹이를 못찾은 것 -> 그 전에 찾은 먹이 시간이 답이 된다. -> 따라서 여기서 시간 정보는 추가X
        return false;
    }
}

class Pair13 implements Comparable<Pair13>{
    int x, y;

    public Pair13 (int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Pair13 o) {
        return this.x == o.x ? this.y-o.y : this.x-o.x;
    }
}


















