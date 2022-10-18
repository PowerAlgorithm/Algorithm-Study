// 15685번 드래곤 커브 (G4) [구현]
/*
<문제 정보>
 1. 100x100 격자 위에 N개의 드래곤 커브가 있을 때 1x1 정사각형 네 꼭짓점이
 모두 드래곤 커브의 일부인 정사각형의 개수를 구하는 프로그램
    - 드래곤 커브 겹칠 수 있음
    - 동북서남 -> 0123

<변수 범위>
 1. 1초 / 512MB
 2. 시작 좌표 0<=x,y<=100
 3. 시작뱡향 0<=d<=3
 4. 세대 0<=g<=10

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q15685 {
    static int N;
    static boolean[][] visited = new boolean[101][101];
    static int[][] dragonCurve = new int[11][1024];
    static int[] dragonCurveLength = new int[11];
    static int[][] dir = {{1,0},{0,-1},{-1,0},{0,1}}; // →(0) ↑(1) ←(2) ↓(3)
    /*static final int RIGHT = 0;
    static final int UP = 1;
    static final int LEFT = 2;
    static final int DOWN = 3;*/

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        // 입력 받으면서 바로 프로그램 진행
        CreateDragonCurveLength();
        CreateDragonCurveSequence();

//        for (int i=0;i<=10;i++) System.out.printf("%d ",dragonCurveLength[i]);
        System.out.println();
        for (int i=0;i<=10;i++) {
            for (int j=0;j<dragonCurveLength[i];j++) System.out.printf("%d ",dragonCurve[i][j]);
            System.out.println();
        }

        int startX, startY, direction, generation;
        for (int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            startX = Integer.parseInt(st.nextToken());
            startY = Integer.parseInt(st.nextToken());
            direction = Integer.parseInt(st.nextToken());
            generation = Integer.parseInt(st.nextToken());
            VisitDragonCurve(startX, startY, direction, generation);
        }

        sb.append(CountSquares());


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void CreateDragonCurveLength() {
        for (int i=0;i<=10;i++) dragonCurveLength[i] = (int) Math.pow(2,i);
    }

    public static void CreateDragonCurveSequence() {
        dragonCurve[0][0] = 0;
        int preLength;
        for (int i=1;i<=10;i++) {
            preLength = dragonCurveLength[i-1];
            for (int j=0;j<preLength;j++) dragonCurve[i][j] = dragonCurve[i-1][j];
            for (int j=0;j<preLength;j++) dragonCurve[i][preLength+j] = (dragonCurve[i-1][preLength-1-j]+1)%4;
        }
    }

    public static void VisitDragonCurve (int x, int y, int startDirection, int generation) {
        visited[x][y] = true;
        int direction;

        for (int i=0;i<dragonCurveLength[generation];i++) {
            direction = (dragonCurve[generation][i] + startDirection)%4;
            x+=dir[direction][0];
            y+=dir[direction][1];
            visited[x][y] = true;
        }
    }

    public static int CountSquares() {
        // 오른쪽 아래 꼭짓점 기준으로 카운트
        boolean twoVertexCounted;
        int squares=0;
        for (int i=0;i<100;i++) {
            twoVertexCounted = false;
            for (int j=0;j<=100;j++) {
                if (visited[i][j] && visited[i+1][j]) {
                    if (!twoVertexCounted) twoVertexCounted = true;
                    else squares++;
                }
                else {
                    if (twoVertexCounted) twoVertexCounted = false;
                }
            }
        }
        return squares;
    }
}















