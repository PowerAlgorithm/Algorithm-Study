// 1941번 소문난 칠공주 (G3) [백트래킹]
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
import java.util.StringTokenizer;
import java.util.HashSet;

public class Q1941 {
    static int answer = 0;
    static int[][] map = new int[5][5];
    static HashSet<Integer> visited = new HashSet<>();

    static final int SIZE = 5;
    static final int RED = 'S';
    static final int BLUE = 'Y';

    static final int[][] DIR = {{1,0}, {0,1}, {-1,0}, {0,-1}};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // ******************** 입력 & 초기화 ********************
        for (int i=0; i<SIZE; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j=0; j<SIZE; j++) {
                map[i][j] = input[j];
            }
        }

        // ******************** 메인 로직 ********************

        for (int i=0; i<SIZE; i++) {
            for (int j=0; j<SIZE; j++) {
                int initStatus = add(0, i, j);
                HashSet<Integer> initList = new HashSet<>();
                initList.add(coorToIdx(i, j));
                dfs(1, initStatus, initList, map[i][j] == RED ? 1 : 0);
            }
        }

        // ******************** 정답 출력 ********************
        sb.append(answer);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs (int depth, int status, HashSet<Integer> list, int numOfReds) {
        if (depth == 7) {
            if (numOfReds >= 4) {
                answer++;
//                System.out.printf("%d번 결과\n", answer);
//                printStatus(status);
            }

            return;
        }

        // 인접한 부분에 팀원 추가
        HashSet<Integer> listCopy = new HashSet<>(list);
        for (int elem : list) {
            int x = elem / SIZE;
            int y = elem % SIZE;
            boolean inRange;

            for (int[] d : DIR) {
                int nextX = x + d[0];
                int nextY = y + d[1];
                int nextIdx = coorToIdx(nextX, nextY);

                inRange = nextX >= 0 && nextY >= 0 && nextX < SIZE && nextY < SIZE;

                if (inRange && !list.contains(nextIdx)) {
                    int addedStatus = add(status, nextX, nextY);
                    if (visited.contains(addedStatus)) continue;

                    visited.add(addedStatus);
                    listCopy.add(nextIdx);
                    dfs(depth + 1, addedStatus, listCopy, map[nextX][nextY] == RED ? numOfReds+1 : numOfReds);
                    listCopy.remove(nextIdx);
                }
            }

        }
    }

    private static int add (int status, int x, int y) {
        int index = x*SIZE + y;
        return status | (1 << index);
    }

    private static int delete (int status, int x, int y) {
        int index = x*SIZE + y;
        return status & ~(1 << index);
    }

    private static int get (int status, int x, int y) {
        int index = coorToIdx(x, y);
        return (status >> index) & 1;
    }

    private static int coorToIdx (int x, int y) {
        return x*SIZE + y;
    }

    private static void printStatus (int status) {
        for (int i=0; i<SIZE; i++) {
            for (int j=0; j<SIZE; j++) {
                System.out.printf("%d ", get(status, i, j));
            }
            System.out.println();
        }
        System.out.println();
    }
}
