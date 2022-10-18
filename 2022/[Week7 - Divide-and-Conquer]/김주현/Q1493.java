// 1493번 박스채우기 (G3) [분할 정복]
/*
<문제 정보>
 1. 박스를 큐브로 채울때 필요한 큐브 최소 개수 출력
    - 큐브의 한변은 2의 제곱꼴
    - 채울 수 없다면 -1

<변수 범위>
 1. 2초 / 128MB
 2. 1<=박스 한변<=10^6
 3. 큐브의 종류개수 1<=n<=20
 4. 큐브 종류 0<=A_i<20
 5. 큐브 개수 1<=B_i<=10^6
 6. 큐브의 종류는 모두 다름

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q1493 {
    static int length, width, height, N;
    static int[] cubes;
    static int usedCubes=0;
    static boolean isPossible = true;

    static final int MAX_CUBES = 1142857;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        length = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(br.readLine());
        cubes = new int[20];
        int cubeUnit;
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            cubeUnit = Integer.parseInt(st.nextToken());
            cubes[cubeUnit] = Integer.parseInt(st.nextToken());
        }
        fillBox(length, width, height);
        sb.append(isPossible ? usedCubes : -1);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    /*
    1. 각 변의 길이 중 최소를 구한다.
    2. 최소인 변보다 작거나 같은 2의 거듭제곱 수를 구하고 해당 크기의 큐브를 가.능.한 채워넣는다.
        - 해당 크기의 큐브가 없다면 작은 것들로 채워 넣는다.
    3. 남은 공간을 최대 3개의 공간으로 분할해 fillBox를 진행한다.
     */
    public static void fillBox(int L, int W, int H) {
        if (L==0 || W==0 || H==0) return;

        if (L==1 || W==1 || H==1) {
            long numOfCube1 = (long) L*W*H;
            if (cubes[0]>=numOfCube1) {
        //                System.out.printf("(%d,%d,%d)에서 Unit %d 큐브 채우기\n", L,W,H,0);
                usedCubes += numOfCube1;
                cubes[0] -= numOfCube1;
        //                System.out.printf("현재 남은 개수 : %d\n", cubes[0]);
            }
            else isPossible = false;
            return;
        }

        // 넣을 수 있는 큐브중 가장 큰 큐브의 크기를 넣는다.
        int minLength = Math.min(L, Math.min(W,H));
        int cubeUnit=19;
        for (int i=0;i<20;i++) {
            if (minLength< 1<<i) {
                cubeUnit = i-1;
                break;
            }
        }

        // 해당 크기의 큐브를 최대 몇개 채울 수 있는지 확인
        int cubeLength = 1 << cubeUnit;
        int cubeOfL = L/cubeLength;
        int cubeOfW = W/cubeLength;
        int cubeOfH = H/cubeLength;
        long numOfCube = (long) cubeOfL*cubeOfW*cubeOfH;
        if (numOfCube>MAX_CUBES) {
            isPossible = false;
            return;
        }

        int removedL = cubeLength*cubeOfL;
        int removedW = cubeLength*cubeOfW;
        int removedH = cubeLength*cubeOfH;

//        System.out.printf("(%d,%d,%d)에서 Unit %d 큐브 %d개 채우기\n", L,W,H,cubeUnit,cubeOfL*cubeOfW*cubeOfH);
        fillCube(cubeUnit, (int) numOfCube);
//        System.out.printf("현재 남은 개수 : %d\n", cubes[0]);
        if (!isPossible) return;


        fillBox(L,W,H-removedH);
        if (!isPossible) return;
        if (L<W) {
            fillBox(L-removedL, W, removedH);
            if (!isPossible) return;
            fillBox(removedL, W-removedW, removedH);
        }
        else {
            fillBox(L-removedL, removedW, removedH);
            if (!isPossible) return;
            fillBox(L, W-removedW, removedH);
        }
    }

    public static void fillCube (int cubeUnit, int numOfCube) {
        // 남아있는 큐브 개수가 충분하면
        if (cubes[cubeUnit] >= numOfCube) {
            usedCubes += numOfCube;
            cubes[cubeUnit] -= numOfCube;
        }

        // 해당크기의 큐브 개수가 부족하면
        else {
            if (cubeUnit==0 || numOfCube>MAX_CUBES) {
                isPossible = false;
                return;
            }
            usedCubes += cubes[cubeUnit];
            numOfCube -= cubes[cubeUnit];
            cubes[cubeUnit] = 0;
            fillCube(cubeUnit-1, numOfCube*8);
        }
    }
}














