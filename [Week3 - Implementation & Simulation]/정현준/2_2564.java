import java.io.*;
import java.sql.Array;
import java.util.*;

class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class Main {

    public static Position getPosition(StringTokenizer st) {
        int first = Integer.parseInt(st.nextToken());
        int second = Integer.parseInt(st.nextToken());
        int x = 0, y = 0;

        // first
        // 1 북
        // 3 서 4 동
        // 2 남
        // second
        // 북 , 남 : 왼쪽 부터
        // 동 , 서 : 위쪽 부터
        switch (first) {
            case 1:
                x = 0;
                y = second;
                break;
            case 2:
                x = row;
                y = second;
                break;
            case 3:
                x = second;
                y = 0;
                break;
            default:
                x = second;
                y = col;
        }

        Position pos = new Position(x, y);
        return pos;
    }

    public static int getDistance(Position s, Position t) {
        int diff = 0;
        // 북 <-> 남
        if ((s.x == 0 && t.x == row) || (s.x == row && t.x == 0)) {
            int diff1 = s.y + t.y + row;
            int diff2 = col - s.y + col - t.y + row;
            diff = Math.min(diff1, diff2);
        }
        // 서 <-> 동
        else if ((s.y == 0 && t.y == col) || (s.y == col && t.y == 0)) {
            int diff1 = s.x + t.x + col;
            int diff2 = row - s.x + row - t.x + col;
            diff = Math.min(diff1, diff2);
        }
        // 같은 선상일 때
        else if (s.x == t.x)
            diff = Math.abs(s.y - t.y);
        else if (s.y == t.y)
            diff = Math.abs(s.x - t.x);
        else {
            diff = Math.abs(s.x - t.x) + Math.abs(s.y - t.y);
        }
        return diff;
    }

    static List<Position> positions;
    static int row, col;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());

        int storeCount = Integer.parseInt(br.readLine());
        positions = new ArrayList<>();

        // 가게의 위치를 담는다
        for (int i = 0; i < storeCount; i++) {
            positions.add(getPosition(new StringTokenizer(br.readLine())));
        }

        // 동근이의 위치를 담는다
        Position stan = getPosition(new StringTokenizer(br.readLine()));

        // 동근이의 위치에서 가게들의 최소 거리를 담는다
        int minDiff = 0;
        for (Position pos : positions) {
            minDiff += getDistance(stan, pos);
        }

        bw.append(String.valueOf(minDiff));
        bw.flush();
        bw.close();
        br.close();
    }
}