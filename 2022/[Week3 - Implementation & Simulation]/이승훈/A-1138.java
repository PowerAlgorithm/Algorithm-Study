import java.io.*;
import java.lang.reflect.Array;
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
 *
 *  - 큰 값부터 위치에 맞게 넣는다. 어차피 작은 값은 주어진 값에 영향을 미치지 않는다.
 */


class Main {
    public static int[] seq;
    public static int N;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        seq = new int[N];
        List<Integer> ans = new ArrayList<>();
        for(int i=0; i<N; i++) {
            seq[i] = (Integer.parseInt(st.nextToken()));
        }
        for(int i=N-1; i>=0; i--) {
            ans.add(seq[i], i);
        }
        ans.stream().forEach(val -> System.out.print(val + 1 + " "));

        br.close();
    }


}