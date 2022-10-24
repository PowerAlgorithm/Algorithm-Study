// 18808번 스티커 붙이기 (G3) [구현]
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
import java.util.ArrayList;

public class Q18808 {
    static int N, M, K;
    static int numOfFilled = 0;
    static boolean map[][];
    static ArrayList<int[][]> stickers = new ArrayList<>();
    static int[] sizeOfStickers;

    static final int EMPTY = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // ******************** 입력 & 초기화 ********************
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];
        sizeOfStickers = new int[K];
        for (int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int sizeX = Integer.parseInt(st.nextToken());
            int sizeY = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[sizeX][sizeY];
            int size = 0;

            for (int j=0; j<sizeX; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k=0; k<sizeY; k++) {
                    sticker[j][k] = Integer.parseInt(st.nextToken());
                    if (sticker[j][k] == 1) size++;

                }
            }
            stickers.add(sticker);
            sizeOfStickers[i] = size;
        }

        // ******************** 메인 로직 ********************

        for (int i=0; i<K; i++) {
//            System.out.println("\n================= New Sticker =================");
//            System.out.printf("%d번째 스티커를 붙입니다.\n", i+1);
            int[][] sticker = stickers.get(i);
//            printSticker(sticker);
            boolean isUsed = fill(sticker);
//            if (isUsed) {
//                System.out.println("스티커를 붙였습니다.");
//            }

            for (int numOfRotate = 1; numOfRotate <= 3; numOfRotate++) {
                if (isUsed) break;
//                System.out.printf("%d번 회전\n", numOfRotate);
                sticker = rotate(sticker);
//                printSticker(sticker);
                isUsed = fill(sticker);
//                if (isUsed) {
//                    System.out.println("스티커를 붙였습니다.");
//                }
            }

            if (isUsed) {
                numOfFilled += sizeOfStickers[i];
            }
//            else {
//                System.out.println("스티커를 붙이지 못했습니다..");
//            }
        }

        // ******************** 정답 출력 ********************

        sb.append(numOfFilled);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // 주어진 스티커를 채웁니다.
    public static boolean fill (int[][] sticker) {
        int lenX = sticker.length;
        int lenY = sticker[0].length;

        for (int startX=0; startX <= N - lenX; startX++) {
            for (int startY=0; startY <= M - lenY; startY++) {
                if (checkStickerSpace(sticker, startX, startY)) {
                    attachSticker(sticker, startX, startY);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkStickerSpace(int[][] sticker, int startX, int startY) {
        int lenX = sticker.length;
        int lenY = sticker[0].length;

        for (int i=0; i<lenX; i++) {
            for (int j=0; j<lenY; j++) {
                if (sticker[i][j] == EMPTY) continue;

                // 붙여야 하는 칸이라면 map이 비었는지 확인하고, 차있다면 바로 false 반환하고 종료
                if (map[startX + i][startY + j]) {
                    return false;
                }
            }
        }

        // false에 한번도 걸리지 않았으면 채울 수 있다는 것
        return true;
    }

    public static void attachSticker(int[][] sticker, int startX, int startY) {
        int lenX = sticker.length;
        int lenY = sticker[0].length;

        for (int i=0; i<lenX; i++) {
            for (int j=0; j<lenY; j++) {
                if (sticker[i][j] == EMPTY) continue;
                map[startX + i][startY + j] = true;
            }
        }
    }

    // 주어진 스티커을 90도 회전해서 반환 합니다.
    public static int[][] rotate (int[][] sticker) {
        int lenX = sticker.length;
        int lenY = sticker[0].length;
        int[][] rotatedSticker = new int[lenY][lenX];

        for (int i=0; i < lenX; i++) {
            for (int j=0; j < lenY; j++) {
                rotatedSticker[j][lenX - 1 -i] = sticker[i][j];
            }
        }

        return rotatedSticker;
    }

    private static void printSticker (int[][] sticker) {
        int lenX = sticker.length;
        int lenY = sticker[0].length;

        for (int i=0;i<lenX;i++) {
            for (int j=0; j<lenY; j++) {
                System.out.printf("%d ", sticker[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}















