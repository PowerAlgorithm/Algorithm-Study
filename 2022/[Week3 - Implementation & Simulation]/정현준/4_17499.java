import java.io.*;
import java.util.*;

class Main {
    static int[] dice = new int[6];
    static int[][] map;
    static int row, col, dice_x, dice_y, command;
    static int[][] move = { { 0, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        dice_x = Integer.parseInt(st.nextToken());
        dice_y = Integer.parseInt(st.nextToken());
        command = Integer.parseInt(st.nextToken());

        map = new int[row][col];

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        // 3 북
        // 2 서 1 동
        // 4 남
        while (st.hasMoreTokens()) {
            int dir = Integer.parseInt(st.nextToken());
            sb.append(roll(dir));
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static String roll(int dir) {
        int move_x = dice_x + move[dir][0];
        int move_y = dice_y + move[dir][1];

        if (move_x >= row || move_x < 0 || move_y >= col || move_y < 0) {
            return "";
        }
        // dice 배열 인덱스
        // 1
        // 3 0 2
        // 4
        // 5
        // 굴린 후의 주사위 면을 계산
        setDicePos(dir);

        if (map[move_x][move_y] == 0) {
            map[move_x][move_y] = dice[5];
        } else {
            dice[5] = map[move_x][move_y];
            map[move_x][move_y] = 0;
        }

        dice_x = move_x;
        dice_y = move_y;

        return dice[0] + "\n";
    }

    public static void setDicePos(int dir) {
        int[] tmp = new int[6];
        System.arraycopy(dice, 0, tmp, 0, dice.length);

        // 동
        if (dir == 1) {
            dice[0] = tmp[3];
            dice[1] = tmp[1];
            dice[2] = tmp[0];
            dice[3] = tmp[5];
            dice[4] = tmp[4];
            dice[5] = tmp[2];

        }
        // 서
        else if (dir == 2) {
            dice[0] = tmp[2];
            dice[1] = tmp[1];
            dice[2] = tmp[5];
            dice[3] = tmp[0];
            dice[4] = tmp[4];
            dice[5] = tmp[3];
        }

        // 남
        else if (dir == 3) {
            dice[0] = tmp[1];
            dice[1] = tmp[5];
            dice[2] = tmp[2];
            dice[3] = tmp[3];
            dice[4] = tmp[0];
            dice[5] = tmp[4];
        }
        // 북
        else if (dir == 4) {
            dice[0] = tmp[4];
            dice[1] = tmp[0];
            dice[2] = tmp[2];
            dice[3] = tmp[3];
            dice[4] = tmp[5];
            dice[5] = tmp[1];
        }

    }
}