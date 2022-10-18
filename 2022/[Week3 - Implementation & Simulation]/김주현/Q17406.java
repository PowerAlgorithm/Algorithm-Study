// 17406번 배열 돌리기4 (G4) [구현]
/*
<문제 정보>
 1. 배열 A와 사용 가능한 회전 연산이 주어질 때 배열 A의 최솟값
    - 회전 연산은 한번씩만 사용
    - 배열 A의 값은 각 행의 합 중 최솟값
    - (r, c, s) -> (r-s, c-s), (r+s, c+s) 꼭짓점 정사각형 시계방향 한번 회전

<변수 범위>
 1. 1초 / 512MB
 2. 배열 크기 3<=N,M<=50
 3. 회전 연산 개수 K 1<=K<=6
 4. 배열의 요소 100 이하 자연수
 5. s>=1

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q17406 {
    static int N, M, K;
    static int[][] arr;
    static int[][] rotates;
    static boolean[] isUsed;
    static int minValue = Integer.MAX_VALUE;
    static final boolean CLOCKWISE = true;
    static final boolean COUNTER_CLOCKWISE = false;


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N+1][M+1];
        rotates = new int[K][3];
        isUsed = new boolean[K];
        for (int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1;j<=M;j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }
        for (int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<3;j++) rotates[i][j] = Integer.parseInt(st.nextToken());
        }

        /*for (int i=1;i<=N;i++) {
            for (int j=1;j<=M;j++) System.out.printf("%d ",arr[i][j]);
            System.out.println();
        }*/
        /*for (int i=0;i<K;i++) {
            for (int j=0;j<3;j++) System.out.printf("%d ",rotates[i][j]);
            System.out.println();
        }*/

        dfs(0);
        sb.append(minValue);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs (int depth) {
        if (depth==K) {
//            System.out.printf("arrValue : %d\n",getArrValue());
            minValue = Math.min(minValue, getArrValue());
            return;
        }

        for (int i=0;i<K;i++) {
            if (isUsed[i]) continue;
            Rotate(rotates[i][0], rotates[i][1], rotates[i][2], CLOCKWISE);
            isUsed[i] = true;
            dfs(depth+1);
            Rotate(rotates[i][0], rotates[i][1], rotates[i][2], COUNTER_CLOCKWISE);
            isUsed[i] = false;
        }
    }

    public static int getArrValue() {
        int min = Integer.MAX_VALUE;
        int sum;
        for (int i=1;i<=N;i++) {
            sum = 0;
            for (int j=1;j<=M;j++) sum+=arr[i][j];
            min = Math.min(min, sum);
        }
        return min;
    }

    public static void Rotate (int centerX, int centerY, int size, boolean direction) {
        if(direction) {
            for (int i=1;i<=size;i++) singleClockwiseRotate(centerX, centerY, i);
        }
        else for (int i=1;i<=size;i++) singleCounterClockwiseRotate(centerX, centerY, i);
    }

    public static void singleClockwiseRotate (int centerX, int centerY, int radius) {
        int startX = centerX - radius;
        int startY = centerY - radius;
        int length = radius*2;
        int temp1, temp2;

        // radius*2 만큼 한번에서 연산진행
        // 변마다 가져오는 방향이 다름

        // 윗변 : 왼쪽에서 가져옴
        temp1 = arr[startX][startY];
        for (int i=1;i<=length;i++) {
            temp2 = arr[startX][startY+i];
            arr[startX][startY+i] = temp1;
            temp1 = temp2;
        }

        // 오른쪽 변 : 위에서 가져옴
        startY += length;
//        temp1 = arr[startX][startY]; 위에서 저장된 temp1 그대로 쓰면 됨!
        for (int i=1;i<=length;i++) {
            temp2 = arr[startX+i][startY];
            arr[startX+i][startY] = temp1;
            temp1 = temp2;
        }

        // 아래쪽 변 : 오른쪽에서 가져옴
        startX+=length;
        for (int i=1;i<=length;i++) {
            temp2 = arr[startX][startY-i];
            arr[startX][startY-i] = temp1;
            temp1 = temp2;
        }

        // 왼쪽변 : 아래에서 가져옴
        startY-=length;
        for (int i=1;i<=length;i++) {
            temp2 = arr[startX-i][startY];
            arr[startX-i][startY] = temp1;
            temp1 = temp2;
        }
    }

    public static void singleCounterClockwiseRotate (int centerX, int centerY, int radius) {
        int startX = centerX - radius;
        int startY = centerY - radius;
        int length = radius*2;
        int temp1, temp2;

        // 왼쪽변 : 위에서 가져옴
        temp1 = arr[startX][startY];
        for (int i=1;i<=length;i++) {
            temp2 = arr[startX+i][startY];
            arr[startX+i][startY] = temp1;
            temp1 = temp2;
        }

        // 아래쪽변 : 왼쪽에서 가져옴
        startX += length;
        for (int i=1;i<=length;i++) {
            temp2 = arr[startX][startY+i];
            arr[startX][startY+i] = temp1;
            temp1 = temp2;
        }

        // 오른쪽 변: 아래에서 가져옴
        startY+=length;
        for (int i=1;i<=length;i++) {
            temp2 = arr[startX-i][startY];
            arr[startX-i][startY] = temp1;
            temp1 = temp2;
        }

        // 위쪽변 : 오른쪽에서 가져옴
        startX-=length;
        for (int i=1;i<=length;i++) {
            temp2 = arr[startX][startY-i];
            arr[startX][startY-i] = temp1;
            temp1 = temp2;
        }
    }
}























