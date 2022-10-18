import java.io.*;
import java.sql.Array;
import java.util.*;

class Main {
    static int[][] stan, map, rotation;
    static int[] permutation;
    static boolean[] visited;
    static int minResult = Integer.MAX_VALUE;
    static int row, col, rotationCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        rotationCount = Integer.parseInt(st.nextToken());

        map = new int[row][col];
        rotation = new int[rotationCount][3];
        visited = new boolean[rotationCount];

        // 입력
        stan = new int[row][col];
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                stan[i][j] = map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 회전 입력
        for (int i = 0; i < rotationCount; i++) {
            st = new StringTokenizer(br.readLine());
            rotation[i][0] = Integer.parseInt(st.nextToken()) - 1;
            rotation[i][1] = Integer.parseInt(st.nextToken()) - 1;
            rotation[i][2] = Integer.parseInt(st.nextToken());
        }

        // 회전 순서 순열
        permutation = new int[rotationCount];
        rotationPermutation(0);

        bw.append(String.valueOf(minResult));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void rotationPermutation(int index) {
        if (index == rotationCount) {
            for (int i = 0; i < permutation.length; i++) {
                int lx = rotation[permutation[i]][0] - rotation[permutation[i]][2];
                int ly = rotation[permutation[i]][1] - rotation[permutation[i]][2];
                int rx = rotation[permutation[i]][0] + rotation[permutation[i]][2];
                int ry = rotation[permutation[i]][1] + rotation[permutation[i]][2];
                rotation(lx, ly, rx, ry);
            }
            minResult = Math.min(minResult, rowSum());
            initMap();
        } else {
            for (int i = 0; i < rotationCount; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    permutation[index] = i;
                    rotationPermutation(index + 1);
                    visited[i] = false;
                }
            }
        }
    }

    public static void initMap() {
        map = new int[row][col];
        for (int i = 0; i < stan.length; i++) {
            System.arraycopy(stan[i], 0, map[i], 0, stan[i].length);
        }
    }

    public static void rotation(int lx, int ly, int rx, int ry) {
        while (lx != rx && ly != ry) {
            // 회전 시 방향이 변경될 때 잃어버릴 값을 임시 저장
            // 우 -> 하
            int rotateDown = map[lx][ry];
            // 하 -> 좌
            int rotateLeft = map[rx][ry];
            // 좌 -> 상
            int rotateUp = map[rx][ly];

            // 외곽 라인
            // 상
            for (int i = ry; i > ly; i--) {
                map[lx][i] = map[lx][i - 1];
            }
            // 우
            for (int i = rx; i > lx; i--) {
                if (i == lx + 1) {
                    map[i][ry] = rotateDown;
                } else {
                    map[i][ry] = map[i - 1][ry];
                }
            }
            // 하
            for (int i = ly; i < ry; i++) {
                if (i == ry - 1) {
                    map[rx][i] = rotateLeft;
                } else {
                    map[rx][i] = map[rx][i + 1];
                }
            }
            // 좌
            for (int i = lx; i < rx; i++) {
                if (i == rx - 1) {
                    map[i][ly] = rotateUp;
                } else {
                    map[i][ly] = map[i + 1][ly];
                }
            }
            lx++;
            ly++;
            rx--;
            ry--;
            // print();
        }
    }

    public static int rowSum() {
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {
            int sum = 0;
            for (int j = 0; j < col; j++) {
                sum += map[i][j];
            }
            result = Math.min(result, sum);
        }
        return result;
    }

    public static void print() {
        System.out.println();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }
}