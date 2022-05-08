import java.io.*;
import java.util.*;

class Main {
    static Deque<Integer> pipe = new ArrayDeque<>();
    static int[] dir = new int[5];
    static int[][] stan, map;
    static int size;
    static int maxNumber = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        size = Integer.parseInt(br.readLine());
        stan = new int[size][size];
        map = new int[size][size];

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                stan[i][j] = map[i][j] = Integer.parseInt(st.nextToken());
                maxNumber = Math.max(stan[i][j], maxNumber);
            }
        }

        // 3 북
        // 1 서 0 동
        // 2 남
        // for(int i = 0 ; i < 5 ; i++){
        // dir[i] = i;
        // }
        // run();
        permutation(0);

        bw.append(String.valueOf(maxNumber));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void permutation(int index) {
        // dir배열에 들어있는 방향대로 계산한다
        if (index == dir.length) {
            run();
            mapInit();
            return;
        } else {
            for (int i = 0; i < 4; i++) {
                dir[index] = i;
                permutation(index + 1);
            }
        }
    }

    public static void run() {
        for (int index = 0; index < dir.length; index++) {
            int move = dir[index];
            // 우측으로 밀기
            if (move == 0) {
                for (int x = 0; x < size; x++) {
                    for (int y = size - 1; y >= 0; y--) {
                        if (map[x][y] != 0) {
                            pipe.offer(map[x][y]);
                            map[x][y] = 0;
                        }
                    }
                    int y = size - 1;
                    while (!pipe.isEmpty()) {
                        if (map[x][y] == 0) {
                            map[x][y] = pipe.poll();
                            continue;
                        }
                        int value = pipe.poll();
                        if (map[x][y] == value) {
                            map[x][y] += value;
                            maxNumber = Math.max(maxNumber, map[x][y]);
                            --y;
                        } else {
                            map[x][--y] = value;
                        }

                    }
                }
                // print();
            }
            // 좌측으로 밀기
            else if (move == 1) {
                for (int x = 0; x < size; x++) {
                    for (int y = 0; y < size; y++) {
                        if (map[x][y] != 0) {
                            pipe.offer(map[x][y]);
                            map[x][y] = 0;
                        }
                    }
                    int y = 0;
                    while (!pipe.isEmpty()) {
                        if (map[x][y] == 0) {
                            map[x][y] = pipe.poll();
                            continue;
                        }
                        int value = pipe.poll();
                        if (map[x][y] == value) {
                            map[x][y] += value;
                            maxNumber = Math.max(maxNumber, map[x][y]);
                            ++y;
                        } else {
                            map[x][++y] = value;
                        }

                    }
                }
                // print();
            }
            // 아래쪽으로 밀기
            else if (move == 2) {
                for (int y = 0; y < size; y++) {
                    for (int x = size - 1; x >= 0; x--) {
                        if (map[x][y] != 0) {
                            pipe.offer(map[x][y]);
                            map[x][y] = 0;
                        }
                    }
                    int x = size - 1;
                    while (!pipe.isEmpty()) {
                        if (map[x][y] == 0) {
                            map[x][y] = pipe.poll();
                            continue;
                        }
                        int value = pipe.poll();
                        if (map[x][y] == value) {
                            map[x][y] += value;
                            maxNumber = Math.max(maxNumber, map[x][y]);
                            --x;
                        } else {
                            map[--x][y] = value;
                        }
                    }
                }
                // print();
            }
            // 위쪽으로 밀기
            else if (move == 3) {
                for (int y = 0; y < size; y++) {
                    for (int x = 0; x < size; x++) {
                        if (map[x][y] != 0) {
                            pipe.offer(map[x][y]);
                            map[x][y] = 0;
                        }
                    }
                    int x = 0;
                    while (!pipe.isEmpty()) {
                        if (map[x][y] == 0) {
                            map[x][y] = pipe.poll();
                            continue;
                        }
                        int value = pipe.poll();
                        if (map[x][y] == value) {
                            map[x][y] += value;
                            maxNumber = Math.max(maxNumber, map[x][y]);
                            ++x;
                        } else {
                            map[++x][y] = value;
                        }
                    }
                }
                // print();
            }
        }
    }

    public static void mapInit() {
        for (int x = 0; x < size; x++) {
            System.arraycopy(stan[x], 0, map[x], 0, size);
        }
    }

    public static void print() {
        for (int i = 0; i < size; i++) {
            System.out.println();
            for (int j = 0; j < size; j++) {
                System.out.print(map[i][j] + "  ");
            }
        }
        System.out.println();
        System.out.println("-----------------------------");
    }
}