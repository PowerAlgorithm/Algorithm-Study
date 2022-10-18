// 12100(Easy)번 (G2) [구현]
/*
<문제 정보>
 1. NxN 보드에서 최대 5번 이동해서 만들수 있는 가장 큰 블록의 값을 출력
    - 2048 게임 룰

<변수 범위>
 1. 1초 / 512MB
 2. 1<=N<=20
 3. 0은 빈칸, 블록에 써져있는 수는 2 이상의 2의 제곱꼴

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q12100 {
    static int N;
    static int[][] originalMap;
    static int[][] map;
    static int[] pushList = new int[5];
    static int maxBlock=0;
    static final int EMPTY = 0;
    static final int UP = 1;
    static final int DOWN = 2;
    static final int LEFT = 3;
    static final int RIGHT = 4;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        originalMap = new int[N+1][N+1];
        map = new int[N+1][N+1];

        for (int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1;j<=N;j++) originalMap[i][j] = Integer.parseInt(st.nextToken());
        }

        /*PrintMap();
        for (int i=1;i<=N;i++) PushRowRight(i);
        for (int i=1;i<=N;i++) PushRowLeft(i);
        for (int i=1;i<=N;i++) PushColumnUp(i);
        for (int i=1;i<=N;i++) PushColumnDown(i);
        Push(DOWN);
        System.out.println();
        PrintMap();*/

        dfs(0);
        sb.append(maxBlock);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int depth) {
        if (depth==5) {
            CopyMap();
            for (int i=0;i<5;i++) Push(pushList[i]);
//            System.out.printf("maxBlock : %d\n",getMaxValue());
            maxBlock = Math.max(maxBlock, getMaxValue());
            return;
        }

        for (int direction=1;direction<=4;direction++) {
            pushList[depth] = direction;
            dfs(depth+1);
        }
    }

    public static int getMaxValue() {
        int max = 0;
        for (int i=1;i<=N;i++) {
            for (int j=1;j<=N;j++) max = Math.max(max, map[i][j]);
        }
        return max;
    }

    public static void Push(int direction) {
        switch(direction) {
            case UP:
                for (int i=1;i<=N;i++) PushColumnUp(i);
                break;
            case DOWN:
                for (int i=1;i<=N;i++) PushColumnDown(i);
                break;
            case LEFT:
                for (int i=1;i<=N;i++) PushRowLeft(i);
                break;
            case RIGHT:
                for (int i=1;i<=N;i++) PushRowRight(i);
        }
    }

    public static void PushRowLeft(int rowNum) {
        // 왼쪽으로 밀기를 구현
        int temp = map[rowNum][1];
        int index = 1;
        for (int i=2;i<=N;i++) {
            if (map[rowNum][i]==EMPTY) continue;
            if (temp==EMPTY) temp = map[rowNum][i];
            else if (map[rowNum][i]==temp) {
                map[rowNum][index++] = temp*2;
                temp=EMPTY;
            }
            else {
                map[rowNum][index++] = temp;
                temp = map[rowNum][i];
            }
            /*System.out.printf("i=%d 일때\n",i);
            PrintMap();
            System.out.printf("temp : %d\n",temp);
            System.out.printf("다음 index : %d\n",index);
            System.out.println();*/
        }
        map[rowNum][index++] = temp; // 저장되있던 temp는 써야함
        for (int i=index;i<=N;i++) map[rowNum][i] = EMPTY;
        /*System.out.println("push 종료");
        PrintMap();*/
    }

    public static void PushRowRight(int rowNum) {
        // 오른쪽으로 밀기를 구현
        int temp = map[rowNum][N];
        int index = N;
        for (int i=N-1;i>=1;i--) {
            if (map[rowNum][i]==EMPTY) continue;
            if (temp==EMPTY) temp = map[rowNum][i];
            else if (map[rowNum][i]==temp) {
                map[rowNum][index--] = temp*2;
                temp=EMPTY;
            }
            else {
                map[rowNum][index--] = temp;
                temp = map[rowNum][i];
            }
        }
        map[rowNum][index--] = temp; // 저장되있던 temp는 써야함
        for (int i=index;i>=1;i--) map[rowNum][i] = EMPTY;
    }

    public static void PushColumnUp(int columnNum) {
        // 위로 밀기를 구현
        int temp = map[1][columnNum];
        int index = 1;
        for (int i=2;i<=N;i++) {
            if (map[i][columnNum]==EMPTY) continue;
            if (temp==EMPTY) temp = map[i][columnNum];
            else if (map[i][columnNum]==temp) {
                map[index++][columnNum] = temp*2;
                temp=EMPTY;
            }
            else {
                map[index++][columnNum] = temp;
                temp = map[i][columnNum];
            }
        }
        map[index++][columnNum] = temp; // 저장되있던 temp는 써야함
        for (int i=index;i<=N;i++) map[i][columnNum] = EMPTY;
    }

    public static void PushColumnDown(int columnNum) {
        // 아래로 밀기를 구현
        int temp = map[N][columnNum];
        int index = N;
        for (int i=N-1;i>=1;i--) {
            if (map[i][columnNum]==EMPTY) continue;
            if (temp==EMPTY) temp = map[i][columnNum];
            else if (map[i][columnNum]==temp) {
                map[index--][columnNum] = temp*2;
                temp=EMPTY;
            }
            else {
                map[index--][columnNum] = temp;
                temp = map[i][columnNum];
            }
        }
        map[index--][columnNum] = temp; // 저장되있던 temp는 써야함
        for (int i=index;i>=1;i--) map[i][columnNum] = EMPTY;
    }

    public static void CopyMap () {
        for (int i=1;i<=N;i++) {
            for (int j=1;j<=N;j++) map[i][j] = originalMap[i][j];
        }
    }

    public static void PrintMap() {
        for (int i=1;i<=N;i++) {
            for (int j=1;j<=N;j++) System.out.printf("%d ",map[i][j]);
            System.out.println();
        }
    }
}




















