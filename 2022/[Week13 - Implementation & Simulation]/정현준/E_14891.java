import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int ROW = 4;
    static final int COL = 8;
    static final int LEFT_GEAR_INDEX = 6;
    static final int RIGHT_GEAR_INDEX = 2;
    static int[][] gears = new int[ROW][COL];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < ROW; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < COL; j++) {
                gears[i][j] = line[j] - 48;
            }
        }

        // 0
        // 7 1
        // 6 2
        // 5 3
        // 4
        int rotationCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < rotationCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int gearNumber = Integer.parseInt(st.nextToken()) - 1;
            // 1 = 시계 방향 , -1 = 반시계 방향
            boolean isClockwise = Integer.parseInt(st.nextToken()) == 1 ? true : false;
            run(gearNumber, isClockwise);
        }
        // print();

        int result = 0;
        for (int i = 0; i < ROW; i++) {
            if (gears[i][0] == 1) {
                result += 1 << (i);
            }
        }
        System.out.println(result);
    }

    public static void run(int gear, boolean isClockwise) {
        // 오른쪽으로 전파
        rightGear(gear, gear + 1, isClockwise);
        // 왼쪽으로 전파
        leftGear(gear, gear - 1, isClockwise);

        rotation(gear, isClockwise);
    }

    public static void rightGear(int gear, int nearGear, boolean isClockwise) {
        if (nearGear >= ROW) {
            return;
        }
        boolean localIsClockwise = !isClockwise;
        if (gears[gear][2] != gears[nearGear][6]) {
            rightGear(nearGear, nearGear + 1, localIsClockwise);
            rotation(nearGear, localIsClockwise);
        }
    }

    public static void leftGear(int gear, int nearGear, boolean isClockwise) {
        if (nearGear < 0) {
            return;
        }
        boolean localIsClockwise = !isClockwise;
        if (gears[gear][6] != gears[nearGear][2]) {
            leftGear(nearGear, nearGear - 1, localIsClockwise);
            rotation(nearGear, localIsClockwise);
        }
    }

    public static void rotation(int gear, boolean isClockwise) {
        if (isClockwise)
            turnRight(gear);
        else
            turnLeft(gear);
    }

    public static void turnRight(int gear) {
        int tmp = gears[gear][7];
        for (int i = COL - 1; i > 0; i--) {
            gears[gear][i] = gears[gear][i - 1];
        }
        gears[gear][0] = tmp;
    }

    public static void turnLeft(int gear) {
        int tmp = gears[gear][0];
        for (int i = 0; i < COL - 1; i++) {
            gears[gear][i] = gears[gear][i + 1];
        }
        gears[gear][7] = tmp;
    }

    public static void print() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print(gears[i][j] + " ");
            }
            System.out.println();
        }
    }
}