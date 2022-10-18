// 1797번 균형잡힌 줄서기 (G2) [해시 & 정렬]
/*
<문제 정보>
 1.

<변수 범위>
 1. 2초 / 256MB

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.HashMap;

public class Q1797 {
    static int N;
    static Fan[] fans;
    static int maxGroupLength = 0;

    static final boolean M = true;
    static final boolean F = false;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // ******************** 입력 & 초기화 ********************

        N = Integer.parseInt(br.readLine());
        fans = new Fan[N];
        boolean gender_in;
        int position_in;
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            gender_in = Integer.parseInt(st.nextToken()) == 0 ? M : F;
            position_in = Integer.parseInt(st.nextToken());
            fans[i] = new Fan(position_in, gender_in);
        }

        // ******************** 메인 로직 ********************

        // 1. position에 따라 정렬
        Arrays.sort(fans);

        /*
         2. 선형탐색을 하며 i번째 사람까지의 (M,F) 의 수를 센다.
          -> 그 차이를 Map의 Key로 저장
          -> Value로는 해당 Key 중 가장 작은 값을 가지고 있는 사람의 position
          -> 만약 해당 Key가 Map에 있다면 현재 pos와 Value의 pos의 차이를 maxGroupLength 와 비교
         */

        HashMap<Integer, Integer> diffs = new HashMap<>();
        int numOfM = 0;
        int numOfF = 0;
        int diff;
        for (int i=0;i<N;i++) {
            diff = numOfM - numOfF;

            if(!diffs.containsKey(diff)) diffs.put(diff, fans[i].position);
            else {
//                System.out.printf("i : %d, diff : %d\n",i, fans[i-1].position - diffs.get(diff));
                maxGroupLength = Math.max(maxGroupLength, fans[i-1].position - diffs.get(diff));
            }

            if (fans[i].gender == M) numOfM++;
            else numOfF++;
        }

        // 마지막 상태에 대한 처리
        diff = numOfM - numOfF;

        if(diffs.containsKey(diff)) {
            maxGroupLength = Math.max(maxGroupLength, fans[N-1].position - diffs.get(diff));
        }

        // ******************** 정답 출력 ********************

        sb.append(maxGroupLength);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

class Fan implements Comparable<Fan>{
    int position;
    boolean gender;

    public Fan (int position, boolean gender) {
        this.position = position;
        this.gender = gender;
    }

    @Override
    public int compareTo (Fan o) {
        return this.position - o.position;
    }
}
