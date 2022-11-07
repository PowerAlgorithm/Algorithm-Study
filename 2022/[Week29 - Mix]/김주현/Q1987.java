// 1987번 알파벳 (G4) [백트래킹]
/*
<문제 정보>
 1.

<변수 범위>
 1.

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Q1987 {
    static int N;
    static int M;
    static int answer = -1;
    static char[][] map;
    static boolean[] visitedAlphabet = new boolean[26];

    static int[][] DIR = {{1,0}, {0,1}, {-1,0}, {0,-1}};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // ******************** 입력 & 초기화 ********************
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // ******************** 메인 로직 ********************

        visitedAlphabet[alphabetToIndex(map[0][0])] = true;
        dfs(1,0,0);

        // ******************** 정답 출력 ********************
        sb.append(answer);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int depth, int x, int y) {
//        System.out.printf("[IN] depth : %d, x : %d, y : %d Alphabet : %c \n", depth, x, y, map[x][y]);
        int nextX, nextY;
        boolean inRange;
        boolean hasPossibleRoad = false;
        for (int[] d : DIR) {
            nextX = x + d[0];
            nextY = y + d[1];
            inRange = nextX >= 0 && nextY >=0 && nextX < N && nextY < M;
            if (inRange && !visitedAlphabet[alphabetToIndex(map[nextX][nextY])]) {
                int alphabetIndex = alphabetToIndex(map[nextX][nextY]);
                hasPossibleRoad = true;
                visitedAlphabet[alphabetIndex] = true;
                dfs(depth+1, nextX, nextY);
                visitedAlphabet[alphabetIndex] = false;
            }
        }

        if (!hasPossibleRoad) {
            answer = Math.max(answer, depth);
//            System.out.printf("[OUT - No Path] depth : %d, x : %d, y : %d Alphabet : %c \n", depth, x, y, map[x][y]);
            return;
        }
//        System.out.printf("[OUT - All Searched] depth : %d, x : %d, y : %d Alphabet : %c \n", depth, x, y, map[x][y]);
    }

    public static int alphabetToIndex (char alphabet) {
        return alphabet - 'A';
    }
}
