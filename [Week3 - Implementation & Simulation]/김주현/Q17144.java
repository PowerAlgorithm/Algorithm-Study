// 17144번 미세먼지 안녕! (G4) [구현]
/*
<문제 정보>
 1. T초가 지난 후 남아있는 미세먼지의 양 출력

<변수 범위>
 1. 1초 / 512MB
 2. 행,열 6<=R,C<=50
 3. 시간 1<=T<=1,000


<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Q17144 {
    static int N, M, T;
    static int purifierPos;
    static int[][] map;
    static int[][] change;
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    static final int AIR_PURIFIER = -1;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        change = new int[N][M];
        boolean purifierDetected = false;
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (!purifierDetected && map[i][j] == AIR_PURIFIER) {
                    purifierPos = i;
                    purifierDetected = true;
                }
            }
        }
        /*System.out.printf("puri pos : %d\n",purifierPos);
        Diffuse();
        PrintMap();
        Purify();
        PrintMap();*/

        while(T-- >0) {
            Diffuse();
            Purify();
        }

        int dust=0;
        for (int i=0;i<N;i++) {
            for (int j=0;j<M;j++) dust+=map[i][j]; // 공기청정기 좌표 2개까지 모두 더함
        }
        dust-=AIR_PURIFIER*2; // 더한 공기청정기 좌표 빼줌
        sb.append(dust);



        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    public static void Diffuse() {
        int x, y;
        boolean inRange;

        // change 배열 초기화
        for (int i=0;i<N;i++) Arrays.fill(change[i], 0);

        // 먼저 각 칸에 얼마만큼의 변화가 일어날 것인지 정보를 담음
        for (int i=0;i<N;i++) {
            for (int j=0;j<M;j++) {
                if (map[i][j]==AIR_PURIFIER) continue;
                for (int[] d : dir) {
                    x = i+d[0];
                    y = j+d[1];
                    inRange = x>=0 && y>=0 && x<N && y<M;
                    if (inRange && map[x][y]!=AIR_PURIFIER) change[i][j]+=map[x][y]/5;
                }
            }
        }

        // 인접한 칸으로 나누어준 만큼 빼고, change에 담겨 있는 만큼 더함
        int diffusedQuantity;
        for (int i=0;i<N;i++) {
            for (int j=0;j<M;j++) {
                if (map[i][j]==AIR_PURIFIER) continue;
                diffusedQuantity = map[i][j]/5;
                for (int[] d : dir) {
                    x = i+d[0];
                    y = j+d[1];
                    inRange = x>=0 && y>=0 && x<N && y<M;
                    if (inRange && map[x][y]!=AIR_PURIFIER) map[i][j] -= diffusedQuantity;
                }
                map[i][j] += change[i][j];
            }
        }
    }

    public static void Purify() {
        PurifyUp();
        PurifyDown();
    }

    public static void PurifyUp() {
        int temp1, temp2;
        int startX = 0;
        int startY = 0;
        int width = M-1;
        int height = purifierPos;

        // 반시계방향 배열 돌리기 구현

        // 왼쪽 변 : 위에서 가져온다.
        temp1 = map[startX][startY];
        for (int i=1;i<height;i++) { // 마지막 height은 공기청정기로 빨려들어가는 것이므로 안해도 괜찮다.
            temp2 = map[startX+i][startY];
            map[startX+i][startY] = temp1;
            temp1 = temp2;
        }

        // 아래쪽 변 : 왼쪽에서 가져온다.
        startX += height;
        temp1 = 0;
        for (int i=1;i<=width;i++) {
            temp2 = map[startX][startY+i];
            map[startX][startY+i] = temp1;
            temp1 = temp2;
        }

        // 오른쪽 변 : 아래에서 가져온다.
        startY += width;

        for (int i=1;i<=height;i++) {
//            System.out.printf("temp1 : %d\n",temp1);
//            System.out.printf("할당될 좌표 : %d,%d\n",startX-i, startY);
            temp2 = map[startX-i][startY];
            map[startX-i][startY] = temp1;
            temp1 = temp2;
        }

        // 위쪽 변 : 오른쪽에서 가져온다.
        startX -= height;
        for (int i=1;i<=width;i++) {
            temp2 = map[startX][startY-i];
            map[startX][startY-i] = temp1;
            temp1 = temp2;
        }
    }

    public static void PurifyDown() {
        int temp1, temp2;
        int startX = purifierPos+1;
        int startY = 0;
        int width = M-1;
        int height = N-2-purifierPos; //PurifierPos+1부터 N-2까지

        // 반시계방향 배열 돌리기 구현

        // 윗 변 : 왼쪽에서 가져온다.
        temp1 = 0; // 공기청정기부터 시작
        for (int i=1;i<=width;i++) {
            temp2 = map[startX][startY+i];
            map[startX][startY+i] = temp1;
            temp1 = temp2;
        }

        // 오른쪽 변 : 위에서 가져온다.
        startY += width;
        for (int i=1;i<=height;i++) {
            temp2 = map[startX+i][startY];
            map[startX+i][startY] = temp1;
            temp1 = temp2;
        }

        // 아래쪽변 : 오른쪽에서 가져온다.
        startX += height;
        for (int i=1;i<=width;i++) {
            temp2 = map[startX][startY-i];
            map[startX][startY-i] = temp1;
            temp1 = temp2;
        }

        // 왼쪽 변 : 아래에서 가져온다.
        startY -= width;
        for (int i=1;i<height;i++) { // 마지막은 공기청정기로 들어감
            temp2 = map[startX-i][startY];
            map[startX-i][startY] = temp1;
            temp1 = temp2;
        }
    }

    public static void PrintMap() {
        for (int i=0;i<N;i++) {
            for (int j=0;j<M;j++) System.out.printf("%d ",map[i][j]);
            System.out.println();
        }
        System.out.println();
    }
}
























