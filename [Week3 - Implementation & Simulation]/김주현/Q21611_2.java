// 21611번 마법사 상어와 블리자드 (G1) [구현]
/*
<문제 정보>
 1. 1×(폭발한 1번 구슬의 개수) + 2×(폭발한 2번 구슬의 개수) + 3×(폭발한 3번 구슬의 개수)
       ① 블리자드 마법
       ② 구슬 이동
       ③ 연속 4개 이상 구슬 폭발
       ④ 구슬 이동
       ⑤ 폭발할 구슬이 없을 때까지 ③,④ 반복
       ⑥ 연속한 구슬끼리 그룹지어 그룹당 구슬 2개로 바꿈 (개수, 종류)

<변수 범위>
 1. 1초 / 1024MB
 2. 3<=N<=49
 3. 1<=M<=100

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q21611_2 {
    static int N;
    static int magic;
    static int sharkX, sharkY;
    static int marbles; // 구슬의 개수
    static int[][] map;
    static int[][] mapNumber;
    static int[] path;
    static int[] explodedMarbles = new int[4];
    static int[][] magics;

    static int[][] dir = {{0,0}, {-1,0},{1,0},{0,-1},{0,1}};
    static int[][] reverse_dir = {{0,0},{1,0},{-1,0},{0,1},{0,-1}};
    static final int EMPTY = 0;
    static final int SHARK = 9;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        magic = Integer.parseInt(st.nextToken());

        // 배열 초기화
        map = new int[N+1][N+1];
        mapNumber = new int[N+1][N+1];
        path = new int[N*N];
        magics = new int[magic][2];

        // 맵 입력
        for (int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1;j<=N;j++) map[i][j] = Integer.parseInt(st.nextToken());
        }
        // 마법 입력
        for (int i=0;i<magic;i++) {
            st = new StringTokenizer(br.readLine());
            magics[i][0] = Integer.parseInt(st.nextToken());
            magics[i][1] = Integer.parseInt(st.nextToken());
        }

        sharkX = (N+1)/2;
        sharkY = (N+1)/2;
        map[sharkX][sharkY] = SHARK;

        CreatePath(); // path[i] 는 i번째 칸으로 가기 위한 방향, 범위 : 1 ~ N*N-1
        CreateMapNumber(); // 범위 1 ~ N*N-1
        marbles = FindEnd(); // 시작시 구슬의 개수

//        useBlizzardMagic(2,2);

        for (int i=0;i<magic;i++) {
//            System.out.printf("----- %d번째 Blizzard -----\n",i+1);
            useBlizzardMagic(magics[i][0], magics[i][1]);
            /*System.out.printf("<%d번째 최종>\n",i+1);
            System.out.printf("marble : %d개\n",marbles);
            PrintMap();*/
        }

        int ans = 0;
        for (int i=1;i<=3;i++) ans+=i*explodedMarbles[i];



        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void useBlizzardMagic(int direction, int dist) {
        Blizzard(direction, dist);
        /*System.out.println("1. Blizzard 끝난후");
        System.out.printf("marble : %d개\n",marbles);
        PrintMap();*/

        MoveMarble();
        /*System.out.println("2. moveMarble 끝난후");
        System.out.printf("marble : %d개\n",marbles);
        PrintMap();*/

        ExplodeMarble();
        /*System.out.println("3. ExplodeMarble 끝난후");
        System.out.printf("marble : %d개\n",marbles);
        PrintMap();*/

        MoveMarble();
        /*System.out.println("3-1. Explode 후의 moveMarble 끝난후");
        System.out.printf("marble : %d개\n",marbles);
        PrintMap();*/

        ConvertMarble();
        /*System.out.println("4. ConvertMarble 끝난후");
        System.out.printf("marble : %d개\n",marbles);
        PrintMap();*/
    }

    // magic 의 1번째 과정
    public static void Blizzard(int direction, int dist) {
        int dx = dir[direction][0];
        int dy = dir[direction][1];

        for (int i=1;i<=dist;i++) {
            if (map[sharkX+(dx*i)][sharkY+(dy*i)] != EMPTY) marbles--;
            map[sharkX+(dx*i)][sharkY+(dy*i)] = EMPTY;
        }
    }

    // magic 의 2번째 과정
    public static void MoveMarble() {
        // 맵을 지정된 경로대로 한바퀴 돌면서
        // 숫자의 종류와 연속된 개수를 세서 입력해준다.
        // 이때 0은 건너뛴다.
        // (주의!) moveMarble 에서는 단순히 구슬들을 움직이는 것이기 때문에 총 개수가 바뀌면 안됨!

        int marbleNumber = EMPTY;
        int marbleAmount = 0;
        int x = sharkX;
        int y = sharkY;
        int resultMapNumber = 1;
        int resultX = sharkX;
        int resultY = sharkY;

//        int placedMarbles = 0;

        for (int i=1;i<=N*N-1;i++) { // 중간에 빈칸들이 포함되어 있기 때문에 구슬의 개수만큼 돌면 안됨!
            x += dir[path[i]][0];
            y += dir[path[i]][1];
            // 맵이 빈칸이면 건너뛴다.
            if (map[x][y] == EMPTY) continue;

            // 현재 칸의 구슬 종류가 저장되있는 구슬 종류와 같다면 구슬 개수 +1
            if (map[x][y] == marbleNumber) marbleAmount++;

            // 현재 칸의 구슬 종류가 저장되어있는 구슬 종류와 다르다면
            // 1. 저장되어있는 구슬을 개수만큼 result 위치에 추가한다.
            // 2. marbleNumber, marbleAmount 를 현재 칸의 정보로 초기화한다.

            if (map[x][y] != marbleNumber) {
                // 1. 저장되어있는 구슬을 개수만큼 result 위치에 추가한다.
                for (int j=1;j<=marbleAmount;j++) {
                    resultX += dir[path[resultMapNumber]][0];
                    resultY += dir[path[resultMapNumber++]][1];
                    map[resultX][resultY] = marbleNumber;
//                    placedMarbles++; // 배치시킨 구슬의 개수 +1
                }

                // 2. marbleNumber, marbleAmount 를 현재 칸의 정보로 초기화한다.
                marbleNumber = map[x][y];
                marbleAmount = 1;
            }
        }

        // 마지막에 다룬 구슬의 정보는 저장만 되고 다루어지지 않았을 수 있기 때문에 맨 뒤에 한번더 다루어주어야 함.
        if (resultMapNumber-1<marbles) {
            for (int i=1;i<=marbleAmount;i++) {
                resultX += dir[path[resultMapNumber]][0];
                resultY += dir[path[resultMapNumber++]][1];
                map[resultX][resultY] = marbleNumber;
//                placedMarbles++; // 배치시킨 구슬의 개수 +1
            }
        }

        // 구슬 개수보다 더 뒤쪽 칸에 있는 정보들은 Empty로 바꾸어줌.
        MakeEmpty();
    }

    // magic 의 3번째 과정
    public static void ExplodeMarble() {
        /*
        1. 맵 경로대로, 빈칸은 건너뛰며 한바퀴를 돌면서
        2. moveMarble 과 마찬가지로 연속되는 구슬의 종류와 개수를 저장
        3. 구슬의 종류가 바뀔때 저장된 구슬의 개수가 4이상이면 그 칸들을 EMPTY 로 바꾼다.
        4. 폭발이 일어났음을 알 수 있는 flag 저장해서 폭발이 일어나지 않았을 경우 종료한다.
         */
        int marbleNumber;
        int marbleAmount;
        int x;
        int y;

        boolean exploded;
        // 반복문 한번이 explode 과정 한번
        while(true) {
            exploded = false; // 반복문 한번 돌때 true 로 바뀌지 않는다면 탈출

            marbleNumber = EMPTY;
            marbleAmount = 0;
            x = sharkX;
            y = sharkY;

            for (int i=1;i<N*N;i++) {
                x += dir[path[i]][0];
                y += dir[path[i]][1];

                if (map[x][y] == EMPTY && i!=N*N-1) continue;
                if (map[x][y] == marbleNumber) marbleAmount++;
                if (map[x][y] != marbleNumber || i==N*N-1) {

                    if (marbleAmount>=4) {
//                        System.out.printf("start : %d,%d\n",x,y);
                        exploded = true;
                        explodedMarbles[marbleNumber] += marbleAmount;
                        marbles -= marbleAmount;

                        // 현재 칸부터 거꾸로 가면서 빈칸이 아닌 곳을 만나면 빈칸으로 만들고 marbleAmount--
                        int tempMapNumber = mapNumber[x][y];
                        int tempX = x;
                        int tempY = y;

                        while(marbleAmount>0) {
//                            System.out.printf("%d,%d\n",tempX,tempY);
                            if (map[tempX][tempY] == marbleNumber) {
                                marbleAmount--;
                                map[tempX][tempY] = EMPTY;
                            }
                            tempX += reverse_dir[path[tempMapNumber]][0];
                            tempY += reverse_dir[path[tempMapNumber--]][1];
                        }
                    }
                    marbleNumber = map[x][y];
                    marbleAmount = 1;
                }
            }

            /*for (int num : explodedMarbles) System.out.printf("%d ",num);
            System.out.println();*/
//            PrintMap();
            if(!exploded) break;
        }
    }

    // magic 의 4번째 과정
    public static void ConvertMarble() {
        // 3번과 비슷, 연속된 구슬의 개수,종류 세고
        // 저장된 정보와 다른 종류라면 변환
        // 단 새롭게 저장되는 정보가 기존 정보를 덮어 쓸수 있기 때문에 비교 대상인 기존것은 copy
        int[][] copiedMap = new int[N+1][N+1];
        CopyMap(copiedMap);
        int totalMarbles = marbles;

        int marbleNumber = EMPTY;
        int marbleAmount = 0;
        int x = sharkX;
        int y = sharkY;
        int resultMapNumber = 1;
        int resultX = sharkX;
        int resultY = sharkY;

        int convertedMarbles = 0;

        for (int i=1; i<=totalMarbles; i++) { // Move를 거친 후이기 때문에 marbles 써도 괜찮음
            x += dir[path[i]][0];
            y += dir[path[i]][1];
//            System.out.println(resultMapNumber);

            if (copiedMap[x][y] == marbleNumber) marbleAmount++;
            if (copiedMap[x][y] != marbleNumber || i==totalMarbles) {
                if (i != 1) {
                    resultX += dir[path[resultMapNumber]][0];
                    resultY += dir[path[resultMapNumber++]][1];
                    map[resultX][resultY] = marbleAmount;
                    if (resultMapNumber>=N*N) break;

                    resultX += dir[path[resultMapNumber]][0];
                    resultY += dir[path[resultMapNumber++]][1];
                    map[resultX][resultY] = marbleNumber;

                    convertedMarbles += marbleAmount;
                }
                marbleNumber = copiedMap[x][y];
                marbleAmount = 1;
                if (resultMapNumber>=N*N) break;
            }
        }

        // 마지막에 저장만 되고 변환되지 못한 구슬 처리
        if (resultMapNumber<N*N && convertedMarbles<totalMarbles) {
            resultX += dir[path[resultMapNumber]][0];
            resultY += dir[path[resultMapNumber++]][1];
            map[resultX][resultY] = marbleAmount;
            if (resultMapNumber>=N*N) {
                marbles = resultMapNumber - 1;
                return;
            }

            resultX += dir[path[resultMapNumber]][0];
            resultY += dir[path[resultMapNumber]][1];
            map[resultX][resultY] = marbleNumber;
            marbles = resultMapNumber;
            MakeEmpty();
            return;
        }
        marbles = resultMapNumber - 1;
        MakeEmpty();
//        System.out.printf("Last resultMapNumber : %d\n",resultMapNumber);

    }




    public static void MakeEmpty() {
        int x = 1;
        int y = 1;
        for (int i=N*N-1; i>marbles; i--) {
            map[x][y] = EMPTY;
            x += reverse_dir[path[i]][0];
            y += reverse_dir[path[i]][1];
        }
    }

    public static int FindEnd() {
        int x = 1;
        int y = 1;
        for (int i=N*N-1;i>=1;i--) {
            if (map[x][y]!=EMPTY) return mapNumber[x][y];
            x += reverse_dir[path[i]][0];
            y += reverse_dir[path[i]][1];
        }
        return 0;
    }

    public static void CreatePath() {
        int[] order = {3, 2, 4, 1};
        int repeat = 1;
        int repeatNum = 0;
        int repeatIncreaseElement = 0;
        int direction = 0; // order 배열의 인덱스


        for (int i=1;i<N*N-1;i++) {
            // 반복이 덜끝났다면 원래 방향 그대로
            if (repeatNum++<repeat) path[i] = order[direction];

            // 반복이 끝났다면 방향과 반복횟수 바꿔주기
            if (repeatNum>=repeat) {
                // 방향 바꾸기
                direction = (direction+1)%4;
                repeatNum = 0;
                repeatIncreaseElement++;

                // 반복 횟수는 방향을 두번바꾸면 반복횟수+1
                if (repeatIncreaseElement==2) {
                    repeat++;
                    repeatIncreaseElement = 0;
                }
            }
        }
        path[N*N-1] = path[N*N-2];
    }

    public static void CreateMapNumber() {
        int x = sharkX;
        int y = sharkY;
        for (int i=1;i<N*N;i++) {
            x+=dir[path[i]][0];
            y+=dir[path[i]][1];
            mapNumber[x][y] = i;
        }
    }

    public static void CopyMap(int[][] copy) {
        for (int i=1;i<=N;i++) {
            for (int j=1;j<=N;j++) copy[i][j] = map[i][j];
        }
    }

    public static void PrintMap() {
        for (int i=1;i<=N;i++) {
            for (int j=1;j<=N;j++) System.out.printf("%d ",map[i][j]);
            System.out.println();
        }
        System.out.println();
    }


}
