import java.io.*;
import java.util.*;

/***
 * 키워드
 *  - 자기보다 큰 사람이 왼쪽에 몇 명 있었는지만 기억한다.
 *
 *  조건
 *  - N은 10보다 작거나 같음
 *
 * 풀이
 *  - n이 10밖에 안되니 모든 경우를 구하고 맞는지 확인한다.
 */


class Main {
    public static List<Integer> comb;
    public static int[] seq;
    public static int N;

    public static void  dfs(int cnt) {
        if(cnt >= N) {
            if (check()) {
                comb.stream().forEach((Integer val) -> {
                    System.out.print(val+1 + " ");
                });
            }
            return;
        }
        for(int i=0; i<N; i++) {
            if (!comb.contains(i)) {
                comb.add(i);
                dfs(cnt + 1);
                comb.remove(comb.size()-1);
            }
        }
        return;
    }

    public static boolean check() {
        for(int i=0; i<N; i++) {
            int leftNum = seq[comb.get(i)];
            int realNum = 0;
            for(int j = 0; j< i+1; j++) {
                if (comb.get(j) > comb.get(i)) {
                    realNum += 1;
                }
            }
            if (leftNum!=realNum)
                return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        seq = new int[N];
        comb = new ArrayList<>();
        for(int i=0; i<N; i++) {
            seq[i] = (Integer.parseInt(st.nextToken()));
        }
        dfs(0);
        br.close();
    }


}