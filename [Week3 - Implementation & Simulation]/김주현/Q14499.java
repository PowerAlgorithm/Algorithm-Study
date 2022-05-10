// 14499번 주사위 굴리기 (G4) [구현]
/*
<문제 정보>
 1. 주사위를 주어진 룰에 따라 이동시킬때, 이동할때마다 윗면에 쓰여진 수를 출력
    - 지도가 0이면 주사위에서 복사
    - 지도 0 아니면 주사위로 복사하고 0이 됨
    - 이동명령 : 동서북남 -> 1234
    - 바깥으로 이동시키려고 하면 무시


<변수 범위>
 1. 2초 / 256MB
 2. 세로,가로 1<=N,M<=20
 3. 좌표 0 ~ N-1 (M-1)
 4. 명령의 개수 K 1<=K<=1,000
 5. 지도에 적힌 수 0~10

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q14499 {
    static int N,M,K;
    static int[][] map;
    static int[] dice = new int[6];
    static final int EAST = 1;
    static final int WEST = 2;
    static final int NORTH = 3;
    static final int SOUTH = 4;
    static final int TOP = 0;
    static final int BOTTOM = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;
    static final int FRONT = 4;
    static final int BACK = 5;


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<M;j++) map[i][j] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int direction;
        for (int i=0;i<K;i++) {
            direction = Integer.parseInt(st.nextToken());
            switch (direction) {
                case EAST:
                    if(y+1>=M) continue;
                    y++;
                    break;
                case WEST:
                    if(y-1<0) continue;
                    y--;
                    break;
                case NORTH:
                    if(x-1<0) continue;
                    x--;
                    break;
                case SOUTH:
                    if(x+1>=N) continue;
                    x++;
            }

            RotateDice(direction);
            sb.append(dice[TOP]).append("\n");
            if (map[x][y]==0) map[x][y] = dice[BOTTOM];
            else {
                dice[BOTTOM] = map[x][y];
                map[x][y]=0;
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void RotateDice(int direction) {
        // 주사위의 표현은 {위, 아래, 왼쪽, 오른쪽, 앞, 뒤}
        // 동서로 굴릴때는 앞뒤가 바뀌지 않음
        // 남북으로 굴릴때는 왼쪽, 오른쪽이 바뀌지 않음
        int temp = dice[TOP];

        switch(direction) {
            case EAST:
                // 왼쪽 -> 위, 아래 -> 왼쪽, 오른쪽 -> 아래, 위 -> 오른쪽
                dice[TOP] = dice[LEFT];
                dice[LEFT] = dice[BOTTOM];
                dice[BOTTOM] = dice[RIGHT];
                dice[RIGHT] = temp;
                break;

            case WEST:
                // 오른쪽 -> 위, 아래 -> 오른쪽, 왼쪽 -> 아래, 위 -> 왼쪽
                dice[TOP] = dice[RIGHT];
                dice[RIGHT] = dice[BOTTOM];
                dice[BOTTOM] = dice[LEFT];
                dice[LEFT] = temp;
                break;

            case NORTH:
                // 앞 -> 위, 아래 -> 앞, 뒤 -> 아래, 위 -> 뒤
                dice[TOP] = dice[FRONT];
                dice[FRONT] = dice[BOTTOM];
                dice[BOTTOM] = dice[BACK];
                dice[BACK] = temp;
                break;

            case SOUTH:
                // 뒤 -> 위, 아래 -> 뒤, 앞 -> 아래, 위 -> 앞
                dice[TOP] = dice[BACK];
                dice[BACK] = dice[BOTTOM];
                dice[BOTTOM] = dice[FRONT];
                dice[FRONT] = temp;
        }
    }
}















