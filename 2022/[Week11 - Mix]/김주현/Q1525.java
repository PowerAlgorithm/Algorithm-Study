// 1525번 퍼즐 (G2) [비트마스킹 + bfs/dfs]
/*
<문제 정보>
 1.

<변수 범위>
 1. 1초 / 256MB

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.HashSet;
import java.util.Deque;
import java.util.ArrayDeque;

public class Q1525 {
    static long mask = 0;
    static long answerMask = 0;
    static HashSet<Long> status = new HashSet<>(); // visited 역할

    static int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};

    static final long FOUR_BIT_ON = 15;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // ******************** 입력 & 초기화 ********************
        answerMask = makeAnswer(answerMask);
        int idx = 0;
        int value_in;
        int zeroX = 0;
        int zeroY = 0;
        for (int i=0;i<3;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<3;j++) {
                value_in = Integer.parseInt(st.nextToken());
                if (value_in==0) {
                    zeroX = i;
                    zeroY = j;
                }
                editMask(idx++, value_in);
            }
        }

        // ******************** 메인 로직 ********************
        if (mask == answerMask) {
            System.out.println(0);
            return;
        }

        sb.append(bfs(zeroX, zeroY));

        // ******************** 정답 출력 ********************

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int  bfs(int startX, int startY) {
        Deque<Pair21> q = new ArrayDeque<>();
        q.add(new Pair21(startX, startY, mask));
        status.add(mask);

        Pair21 now;
        int nextX, nextY;
        int nowIdx ,nextIdx;
        long nextMask;
        int tmp;
        boolean inRange;
        int size;
        int move = 0;
        boolean findAns = false;

        while(!q.isEmpty()) {
            if(findAns) break;
            move++;
//            if (move>15) break;
            size = q.size();
//            System.out.printf("************ move : %d *************\n",move);

            for (int i=0;i<size;i++) {
                if(findAns) break;
                now = q.poll();

                for (int[] d : dir) {
                    nextX = now.x + d[0];
                    nextY = now.y + d[1];
                    inRange = nextX>=0 && nextY>=0 && nextX<3 && nextY<3;
                    if (inRange) {
                        nowIdx = 3*now.x + now.y;
                        nextIdx = 3*nextX + nextY;
                        tmp = getNumOfIndex(now.mask, nextIdx);
                        nextMask = editMask(now.mask, nextIdx, 0);
                        nextMask = editMask(nextMask, nowIdx, tmp);
                        if (status.contains(nextMask)) continue;

                        if (nextMask == answerMask) {
                            findAns = true;
                            break;
                        }
//                        System.out.println("-------------");
//                        printMask1(nextMask);
                        status.add(nextMask);
                        q.add(new Pair21(nextX, nextY, nextMask));
                    }
                }
            }
        }
        return findAns ? move : -1;
    }

    public static int getNumOfIndex (int index) {
        return (int) (mask >> (index<<2)) & 15;
    }

    public static int getNumOfIndex (long mask, int index) {
        return (int) (mask >> (index<<2)) & 15; // 1111 1011 1011
    }  // 8 4비트 2번 (0번, 1번 건너뛰어)

    public static void printMask(long mask) {
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                System.out.printf("%d ",getNumOfIndex(mask, 3*i+j));
            }
            System.out.println();
        }
    }

    public static void printMask1(long mask) {
        for (int i=0;i<9;i++) System.out.printf("%d ",getNumOfIndex(mask, i));
        System.out.println();
    }

    // index 위치의 값을 value로 수정하는 함수
    public static long editMask(long mask, int index, long value) {
        return mask & ~(FOUR_BIT_ON<<(index<<2)) | (value << (index<<2));
    }

    public static void editMask(int index, long value) {
        mask = mask & ~(FOUR_BIT_ON<<(index<<2)) | (value << (index<<2));
    }

    // 정답 배열 Mask를 만드는 함수
    public static long makeAnswer(long mask) {
        for (int i=1;i<=8;i++) mask = editMask(mask,i-1, i);
        return mask;
    }

}

class Pair21 {
    int x, y;
    long mask;

    public Pair21 (int x, int y, long mask) {
        this.x = x;
        this.y = y;
        this.mask = mask;
    }
}