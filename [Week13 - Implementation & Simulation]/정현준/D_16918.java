import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

class Position {
	int x;
	int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static char[][] map, oldMap;
	static int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int row, col, time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		time = Integer.parseInt(st.nextToken());

		map = new char[row][col];
		oldMap = new char[row][col];

		for (int i = 0; i < row; i++) {
			String line = br.readLine();
			for (int j = 0; j < col; j++) {
				oldMap[i][j] = line.charAt(j);
			}
		}
		time--;
		while (time > 0) {
			fillBomb();
			explodeBomb();
		}

		System.out.println(print());
	}

	public static String print() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				sb.append(oldMap[i][j]);
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public static void fillBomb() {
		if (--time >= 0) {
			for (int i = 0; i < row; i++) {
				System.arraycopy(oldMap[i], 0, map[i], 0, col);
				Arrays.fill(oldMap[i], 'O');
			}
		}
	}

	public static void explodeBomb() {
		if (--time >= 0) {
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if (map[i][j] == 'O') {
						oldMap[i][j] = '.';
						for (int k = 0; k < 4; k++) {
							int nearX = i + dirs[k][0];
							int nearY = j + dirs[k][1];
							if (!isOutOfRange(nearX, nearY)) {
								oldMap[nearX][nearY] = '.';
							}
						}
					}
				}
			}
		}
	}

	public static boolean isOutOfRange(int x, int y) {
		if (x < 0 || x >= row || y < 0 || y >= col) {
			return true;
		}
		return false;
	}
}