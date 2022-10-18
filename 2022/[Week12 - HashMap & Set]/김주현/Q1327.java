// 1327번 소트 게임 (G5) [해시를 이용한 bfs]
/*
<문제 정보>
 1. N자리 수열에서 어떤 수를 선택하면 그 수부터 오른쪽으로 K개의 수를 뒤집는다.
    - 들어온 수열을 오름차순으로 만들기 위해 최소 몇번의 숫자를 선택해야 하는지 출력

<변수 범위>
 1. 2초 / 128MB
 2. 2<=K<=N<=8

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

public class Q1327 {
    // 숫자가 최대 8개, 1~8 -> 한개당 4비트 => 총 32비트 => 4바이트 int 사용
    static int N, K, numOfReversible;
    static int mask = 0;
    static int answerMask = 0;
    static HashSet<Integer> status = new HashSet<>();

    static final int FOUR_BIT_ON = 15;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // ******************** 입력 & 초기화 ********************

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        numOfReversible = N-K+1;

        st = new StringTokenizer(br.readLine());
        int num_in;
        for (int i=0;i<N;i++) {
            num_in = Integer.parseInt(st.nextToken());
            mask = editMask(mask, i, num_in);
        }

        makeAnswerMask(); // 오름차순인 정답 mask 만들기

        // ******************** 메인 로직 ********************

        sb.append(bfs(mask));

        // ******************** 정답 출력 ********************

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // ----------------- bfs START ----------------- //

    public static int bfs (int mask) {
        if (mask == answerMask) return 0;

        Deque<Integer> q = new ArrayDeque<>();
        q.add(mask);
        status.add(mask);

        int nowMask;
        int nextMask;
        int size;
        int count = 0;
        boolean findAns = false;

        while(!q.isEmpty()) {
            if (findAns) break;
            count++;
            size = q.size();

            for (int s=0;s<size;s++) {
                if (findAns) break;
                nowMask = q.poll();
//                System.out.printf("count:%d - arr : ",count-1);
//                printMask(nowMask);

                for (int i=0;i<numOfReversible;i++) {
                    nextMask = reverseMask(nowMask, i, K);
//                    printMask(nextMask);

                    if (nextMask == answerMask) {
                        findAns = true;
                        break;
                    }

                    if (status.contains(nextMask)) continue;

                    q.add(nextMask);
                    status.add(nextMask);
                }
            }
        }
        return findAns ? count : -1;
    }

    // ----------------- bfs END ----------------- //

    // ----------------- dfs START ----------------- //
/* dfs 를 쓰면 몇번 바꿨는지 count 변수가 필요해 Map을 써야함. 그러면 경우가 굉장히 많아짐 8! * count ....
    public static void dfs(int mask, int count) {

        if (mask == answerMask) {
            minCount = Math.min(minCount, count);
            return;
        }

        int tmp_mask;
        for (int i=0;i<numOfReversible;i++) {
            tmp_mask = reverseMask(mask, i, K);
            if (status.containsKey(tmp_mask)) {
                // count+1 횟수보다 저장되어 있는 횟수가 작거나 같으면 continue
                if (status.get(tmp_mask) <= count+1) continue;
                else status.replace(tmp_mask, count+1);
            }
            // reverse 시킨 mask를 포함하고 있지 않다면 추가
            else status.put(tmp_mask, count+1);

            dfs(tmp_mask, count+1);
        }
    }
 */

    // ----------------- dfs END ----------------- //


    // ----------------- 뒤집기 함수 START ----------------- //

    public static int reverseMask(int mask, int startIdx, int len) {
        int endIdx = startIdx+len-1;
        for (int i=0;i<len/2;i++) {
            mask = swap(mask, startIdx+i, endIdx-i);
        }
        return mask;
    }

    public static int swap(int mask, int idx1, int idx2) {
        int temp = getNthNumOfMask(mask, idx1);
        mask = editMask(mask, idx1, getNthNumOfMask(mask, idx2));
        mask = editMask(mask, idx2, temp);
        return mask;
    }

    // ----------------- 뒤집기 함수 END ----------------- //

    // answerMask (오름차순) 만드는 함수
    public static void makeAnswerMask() {
        for (int i=0;i<N;i++) {
            answerMask = editMask(answerMask, i, i+1);
        }
    }

    // ----------------- 기본적인 비트마스크 연산 START ----------------- //

    // index에 해당하는 숫자 가져오기
    public static int getNthNumOfMask(int mask, int index) {
        return (mask >> (index<<2)) & FOUR_BIT_ON;
    }

    // index의 숫자를 value로 바꾸기
    public static int editMask(int mask, int index, int value) {
        return mask & ~(FOUR_BIT_ON << (index<<2)) | (value << (index<<2));
    }

    // 배열 출력하기
    public static void printMask(int mask) {
        for (int i=0;i<N;i++) System.out.printf("%d ",getNthNumOfMask(mask, i));
        System.out.println();
    }

    // ----------------- 기본적인 비트마스크 연산 END ----------------- //
}
