// 6236번 용돈 관리 (S2) [매개변수 탐색]
/*
<문제 정보>
 1. N일 동안 K원씩 M번의 인출을 통해 각각의 날들에 필요한 금액을 쓸 때, K의 최솟값
    - M번을 맞추기위해 쓸돈이 더 많더라도 다시 넣고 K원을 인출할 수 있다.

<변수 범위>
 1. 1초 / 128MB
 2. 1<=N<=100,000
 3. 1<=M<=N
 4. 금액은 10,000 이하의 자연수

<프로그램 진행>
 1. f(K) = 인출횟수 M번이 가능?
 2. 일정 K 이상부터는 무조건 M번이 가능
 3. 최소 금액 -> BS_LowerBound

<필요 함수>
 1.

 */


import java.io.*;
import java.util.StringTokenizer;

public class Q6236 {
    static int N, M;
    static int maxExpenditure=0;
    static int[] expenditure;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        expenditure = new int[N];
        for (int i=0;i<N;i++) {
            expenditure[i] = Integer.parseInt(br.readLine());
            maxExpenditure = Math.max(maxExpenditure, expenditure[i]);
        }

        sb.append(N==M ? maxExpenditure : BS_LowerBound(M));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int BS_LowerBound(int key) {
        int lo = maxExpenditure;
        int hi = 1000000000;
        int mid;

        while(lo<hi) {
            mid = (lo+hi)/2;
            if(!is_M_Possible(mid, key)) lo = mid+1;
            else hi = mid;
        }
        return lo;
    }

    // 한번 인출할 때 K원씩 인출한다면 M번 인출해서 N일 동안 쓸 수 있니?
    // -> 인출하지 않는 날이 달성되면 바로 true 반환
    // 이 함수내에서는 N==M 인 상황은 없음 (N==M이라면 이 함수에서는 커버 불가능)
    public static boolean is_M_Possible (int K, int M) {
        int money = K-expenditure[0];
        int numOfNotWithdrawal = 0;
        for (int i=1;i<N;i++) {
            // 남은 날동안 전부 인출하지 않아도 달성이 안된다면 false 반환
            if (N-i + numOfNotWithdrawal < N-M) return false;

            if (money>=expenditure[i]) { // 인출을 하지 않은 날!
                money-=expenditure[i];
                numOfNotWithdrawal++;
                if (numOfNotWithdrawal==N-M) return true;
            }
            else money = K-expenditure[i];
        }
        // N이 1이라면 무조건 true, 아닌데 여기 도달했다면 false
        return N==1;
    }
}
